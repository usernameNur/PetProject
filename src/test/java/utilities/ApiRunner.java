package utilities;

import antities.CustomResponse;
import antities.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.Map;

public class ApiRunner {
    @Getter
    private static CustomResponse customResponse;
    //this is a get method, get without any parameters
    public static void runGet(String path)  {
        String url=Config.getProperties("cashWiseAPIUrl")+path;
        String token=CashWiseAuthorization.getToken();
        Response response= RestAssured.given().auth().oauth2(token).get(url);
        System.out.println(response.statusCode());
        ObjectMapper mapper=new ObjectMapper();

        try {
            customResponse=mapper.readValue(response.asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
    //this is a get method that take parameters
    public static void runGet(String path, Map<String, Object> params){
        String url=Config.getProperties("cashWiseAPIUrl")+path;
        String token=CashWiseAuthorization.getToken();
        Response response= RestAssured.given().auth().oauth2(token).params(params).get(url);
        System.out.println(response.statusCode());
        ObjectMapper mapper=new ObjectMapper();

        try {
            customResponse=mapper.readValue(response.asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
}
// this is the method to post with request body
public static void runPost(String path, RequestBody requestBody){

    String url=Config.getProperties("cashWiseAPIUrl")+path;
    String token=CashWiseAuthorization.getToken();
    Response response= RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).post(url);
    System.out.println(response.statusCode());
    ObjectMapper mapper=new ObjectMapper();

    try {
        customResponse=mapper.readValue(response.asString(), CustomResponse.class);
    } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
    }}
    //this is the post method that takes parameters
    public static void runPost(String path, Map<String, Object>params){

        String url=Config.getProperties("cashWiseAPIUrl")+path;
        String token=CashWiseAuthorization.getToken();
        Response response= RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).params(params).post(url);
        System.out.println(response.statusCode());
        ObjectMapper mapper=new ObjectMapper();

        try {
            customResponse=mapper.readValue(response.asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

}
//this is delete method that only takes token and url

    public static void runDelete(String path)  {
        String url=Config.getProperties("cashWiseAPIUrl")+path;
        String token=CashWiseAuthorization.getToken();
        Response response= RestAssured.given().auth().oauth2(token).delete(url);
        System.out.println(response.statusCode());

    }

    //this is put method that takes request body
    public static void runPut(String path, RequestBody requestBody){

        String url=Config.getProperties("cashWiseAPIUrl")+path;
        String token=CashWiseAuthorization.getToken();
        Response response= RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON).body(requestBody).put(url);
        System.out.println(response.statusCode());
        ObjectMapper mapper=new ObjectMapper();

        try {
            customResponse=mapper.readValue(response.asString(), CustomResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }}



}


