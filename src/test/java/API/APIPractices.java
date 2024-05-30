package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashWiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

public class APIPractices {
    @Test
    public void TestEmail() {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println(response.prettyPrint());
        String email = response.jsonPath().getString("responses[0].email");
        System.out.println(email);
        String email2 = response.jsonPath().getString("responses[1].email");
        String email3 = response.jsonPath().getString("response[2].email");


        int status = response.getStatusCode();
        Assert.assertEquals(200, status);
        Assert.assertFalse(email.isEmpty());
        Assert.assertEquals(email2, email3);
    }

    @Test
    public void improveAllSellers() {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/sellers";
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 100);
        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        int size = response.jsonPath().getList("responses").size();
        System.out.println(size);

        int status = response.statusCode();
        Assert.assertEquals(200, status);
        for (int i = 0; i < size; i++) {
            String email = response.jsonPath().getString("responses[" + i + "].email");
            Assert.assertFalse(email.isEmpty());


        }
    }

    @Test
    public void bankAccount() {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/bankaccount";
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        int status = response.statusCode();

        Assert.assertEquals(200, status);
        response.prettyPrint();
        int size = response.jsonPath().getList("JSON").size();
        System.out.println(size);

    }

    @Test
    public void createBankAccount() {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/bankaccount";
        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name("Ban");
        requestBody.setDescription("mnbvvg");
        requestBody.setBalance(1000000);

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
        int status = response.statusCode();

        response.prettyPrint();
        Assert.assertEquals(201, status);
    }

    @Test
    public void asserting() {
        String token = CashWiseAuthorization.getToken();
        String url = Config.getProperties("cashWiseAPIUrl") + "api/myaccount/bankaccount";
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        String expectedName = "Bank";
        boolean isPresent = false;
        int size = response.jsonPath().getList("JSON").size();
        for (int i = 0; i < size; i++) {
            String createdName = response.jsonPath().getString("[" + i + "].bank_account_name");
            if (createdName.equalsIgnoreCase(expectedName)) {
                isPresent = true;
            }

        }
        Assert.assertTrue(isPresent);
    }
}
