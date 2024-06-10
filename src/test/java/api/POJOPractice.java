package api;

import antities.CustomResponse;
import antities.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import utilities.ApiRunner;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

import static org.codehaus.groovy.ast.tools.GeneralUtils.params;

public class POJOPractice {

    @Test
    public void createCategory() {
        String url = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/categories";
        String token = CashWiseAuthorization.getToken();

        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("Transportation");
        requestBody.setDescription("Truck");
        requestBody.setFlag(true);

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
        int status = response.statusCode();
        Assert.assertEquals(201, status);

    }

    @Test
    public void testCustom() throws JsonProcessingException {

        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/categories";
        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("Edu");
        requestBody.setCategory_description("coding");
        requestBody.setFlag(true);
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
        int status = response.statusCode();
        Assert.assertEquals(201, status);
        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        String expectedTitle = customResponse.getCategory_title();
        String expectedDescription = customResponse.getCategory_description();
        Assert.assertEquals(expectedTitle, "Edu");
        Assert.assertEquals(expectedDescription, "coding");


    }

    @Test
    public void createGetSeller() throws JsonProcessingException {
        Faker faker = new Faker();
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/sellers";
        RequestBody requestBody = new RequestBody();
        String name = faker.name().firstName();
        requestBody.setSeller_name(name);
        requestBody.setCompany_name("Tesla");
        requestBody.setPhone_number("12222");
        requestBody.setAddress("23fsdf");
        requestBody.setEmail(faker.internet().emailAddress());
        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
        int status = response.statusCode();
        Assert.assertEquals(201, status);

        ObjectMapper objectMapper = new ObjectMapper();
        CustomResponse customResponse = objectMapper.readValue(response.asString(), CustomResponse.class);

        int id = customResponse.getSeller_id();
        String url1 = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/sellers/" + id;
        Response response1 = RestAssured.given().auth().oauth2(token).get(url1);
        int status2 = response1.statusCode();
        Assert.assertEquals(200, status2);
        String expectedSellerName = customResponse.getSeller_name();
        Assert.assertEquals(name, expectedSellerName);
        String expectedCompanyName = customResponse.getCompany_name();
        Assert.assertEquals("Tesla", expectedCompanyName);


    }

    @Test
    public void getSingleSeller() throws JsonProcessingException {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/sellers/" + 4203;
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        ObjectMapper objectMapper = new ObjectMapper();
        CustomResponse customResponse = objectMapper.readValue(response.asString(), CustomResponse.class);

        String email = customResponse.getEmail();
        Assert.assertFalse(email.isEmpty());

    }

    @Test
    public void createSeller() throws JsonProcessingException {
    String token = CashWiseAuthorization.getToken();
    String url = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/sellers";
    RequestBody requestBody = new RequestBody();
    requestBody.setCompany_name("Cashwise");
    requestBody.setSeller_name("Nur");
    requestBody.setEmail("da@gmail.com");
    requestBody.setPhone_number("12345677");
    requestBody.setAddress("2230 nmskfjjf");
    Response response=RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
    ObjectMapper mapper=new ObjectMapper();
    CustomResponse customResponse=mapper.readValue(response.asString(), CustomResponse.class);
    int id=customResponse.getSeller_id();
    String url1=Config.getProperties("cashWiseAPIUrl") + "api/myaccount/sellers";
    RestAssured.given().auth().oauth2(token).get(url1+id);
    int statusCode= response.statusCode();
    Assert.assertEquals(201, statusCode);

    String expectedCompanyName= customResponse.getCompany_name();
    Assert.assertEquals("Cashwise", expectedCompanyName);
}

@Test
    public void getAllSeller() throws JsonProcessingException {
        String token=CashWiseAuthorization.getToken();
        String url=Config.getProperties("cashWiseAPIUrl") + "api/myaccount/sellers";
        Map<String,Object> params=new HashMap<>();
        params.put("isArchived", false);
        params.put("page",1);
        params.put("size", 60);
        Response response=RestAssured.given().auth().oauth2(token).params(params).get(url);
        int statusCode= response.statusCode();
        Assert.assertEquals(200, statusCode);
        response.prettyPrint();
        ObjectMapper mapper=new ObjectMapper();
        CustomResponse customResponse=mapper.readValue(response.asString(), CustomResponse.class);
        int size= customResponse.getResponses().size();
        for (int i=0; i<size; i++){
            String email=customResponse.getResponses().get(i).getEmail();
            Assert.assertFalse(email.isEmpty());
        }

}
@Test
    public void listOfProducts() throws JsonProcessingException {
        String url=Config.getProperties("cashWiseAPIUrl") + "api/myaccount/products";
        String token=CashWiseAuthorization.getToken();
        Map<String, Object>params=new HashMap<>();
        params.put("page", 1);
        params.put("size", 20);
        Response response=RestAssured.given().auth().oauth2(token).params(params).get(url);
        response.prettyPrint();
        ObjectMapper mapper=new ObjectMapper();
        CustomResponse customResponse=mapper.readValue(response.asString(), CustomResponse.class);
        int size=customResponse.getResponses().size();
        System.out.println(size);
        for(int i=0; i<size; i++){
//            String productTitle=customResponse.getResponses().get(i).getProduct_title();
            String id=customResponse.getResponses().get(i).getProduct_id();
            System.out.println(id);
            Assert.assertFalse(id.isEmpty());
        }
}
@Test
    public void TestGet(){
    ApiRunner.runGet("api/myaccount/sellers/"+4203);
    String companyName=ApiRunner.getCustomResponse().getCompany_name();
    System.out.println(companyName);
    Assert.assertEquals("Wilderman, Mohr and Ullrich", companyName);
}
@Test
    public void TestAllSellers() {
    Map<String, Object> params = new HashMap<>();
    String url = ("api/myaccount/sellers/");
    params.put("isArchived", false);
    params.put("page", 1);
    params.put("size", 20);
    ApiRunner.runGet(url, params);
    int size = ApiRunner.getCustomResponse().getResponses().size();
    System.out.println(size);

}
@Test
    public void TestPost(){
        RequestBody requestBody=new RequestBody();
        requestBody.setCompany_name("Utilities");
        requestBody.setSeller_name("Nur");
        requestBody.setEmail("safdasdf@gmail.com");
        requestBody.setPhone_number("1224235435");
        requestBody.setAddress("5325 dfsdg");
        ApiRunner.runPost("api/myaccount/sellers/", requestBody);


}
@Test
    public void TestDelete(){
        int id=1936;
        String endPoint="api/myaccount/products/";
        ApiRunner.runDelete(endPoint+id);
}
@Test
    public void TestPut(){
        String endPoint=("api/myaccount/sellers/"+4357);
        
        RequestBody requestBody=new RequestBody();
        requestBody.setSeller_name("me");
        requestBody.setCompany_name("table");
        requestBody.setEmail("mm@gmail.com");
        requestBody.setPhone_number("5453487985");
        requestBody.setAddress("whatever");

        ApiRunner.runPut(endPoint, requestBody);

}


}
