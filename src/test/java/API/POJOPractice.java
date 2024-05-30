package API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

public class POJOPractice {

    @Test
    public void createCategory(){
        String url= Config.getProperties("cashWiseAPIUrl")+"api/myaccount/categories";
        String token= CashWiseAuthorization.getToken();

        RequestBody requestBody=new RequestBody();
        requestBody.setCategory_title("Transportation");
        requestBody.setDescription("Truck");
        requestBody.setFlag(true);

        Response response=RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
        int status=response.statusCode();
        Assert.assertEquals(201,status);

    }

    @Test
    public void testCustom() throws JsonProcessingException {

        String token=CashWiseAuthorization.getToken();
        String url=Config.getProperties("cashWiseAPIUrl")+"api/myaccount/categories";
        RequestBody requestBody=new RequestBody();
        requestBody.setCategory_title("Edu");
        requestBody.setCategory_description("coding");
        requestBody.setFlag(true);
        Response response=RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
        int status= response.statusCode();
        Assert.assertEquals(201, status);
        response.prettyPrint();

        ObjectMapper mapper=new ObjectMapper();
        CustomResponse customResponse=mapper.readValue(response.asString(),CustomResponse.class);
        String expectedTitle=customResponse.getCategory_title();
        String expectedDescription= customResponse.getCategory_description();
        Assert.assertEquals(expectedTitle,"Edu");
        Assert.assertEquals(expectedDescription,"coding");







    }
    @Test
    public void createGetSeller() throws JsonProcessingException {
        Faker faker=new Faker();
        String token=CashWiseAuthorization.getToken();
        String url=Config.getProperties("cashWiseAPIUrl")+"api/myaccount/sellers";
        RequestBody requestBody=new RequestBody();
        String name=faker.name().firstName();
        requestBody.setSeller_name(name);
        requestBody.setCompany_name("Tesla");
        requestBody.setPhone_number("12222");
        requestBody.setAddress("23fsdf");
        requestBody.setEmail(faker.internet().emailAddress());
        Response response= RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
        int status= response.statusCode();
        Assert.assertEquals(201,status);

        ObjectMapper objectMapper=new ObjectMapper();
        CustomResponse customResponse=objectMapper.readValue(response.asString(), CustomResponse.class);

        int id=customResponse.getSeller_id();
        String url1=Config.getProperties("cashWiseAPIUrl")+"api/myaccount/sellers/"+id;
        Response response1=RestAssured.given().auth().oauth2(token).get(url1);
        int status2= response1.statusCode();
        Assert.assertEquals(200,status2);
        String expectedSellerName=customResponse.getSeller_name();
        Assert.assertEquals(name, expectedSellerName);
        String expectedCompanyName=customResponse.getCompany_name();
        Assert.assertEquals("Tesla", expectedCompanyName);


    }




}
