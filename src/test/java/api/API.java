package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class API {
    @Test
    public void email(){
       Response response =RestAssured.get("https://reqres.in/api/users/2");
       response.prettyPrint();
       String email=response.jsonPath().getString("data.email");
        System.out.println(email);
        Assert.assertTrue(email.endsWith("@reqres.in"));
        int statusCode= response.statusCode();
        Assert.assertEquals(statusCode, 200);

    }
    @Test
    public void name(){
        Response response =RestAssured.get("https://reqres.in/api/users/2");
        response.prettyPrint();
        String first_name=response.jsonPath().getString("data.first_name");
        System.out.println(first_name);
        Assert.assertEquals(first_name, "Janet");
        int statusCode= response.statusCode();
        Assert.assertEquals(statusCode, 200);

    }
@Test
    public void text(){
       Response response= RestAssured.get("https://reqres.in/api/users/2");
       String text=response.jsonPath().getString("support.text");
    System.out.println(text);
}



}
