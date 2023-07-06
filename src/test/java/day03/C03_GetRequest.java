package day03;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C03_GetRequest  {


    static String url = "https://reqres.in/api/users/2";
    //TC03
    @Test
    public void headerTestReqres(){
        Response response = given().when().get(url) ;
       // response.prettyPeek();
        response.then()
                // we can test only headers with assert
                .assertThat()
                .header("Server","cloudflare")
                .contentType("application/json; charset=utf-8")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");

    }





}
