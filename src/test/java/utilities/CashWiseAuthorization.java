package utilities;

import antities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CashWiseAuthorization {
    public static String getToken(){
        String url=Config.getProperties("cashWiseAPIUrl")+"api/myaccount/auth/login";
        RequestBody requestBody =new RequestBody();
        requestBody.setEmail("ab.nurzada@gmail.com");
        requestBody.setPassword("Aijamal2724!");
        Response response= RestAssured.given().contentType(ContentType.JSON).body(requestBody).post(url);
        return response.jsonPath().getString("jwt_token");
    }
}
