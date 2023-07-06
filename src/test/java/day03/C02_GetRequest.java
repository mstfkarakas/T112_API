package day03;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_GetRequest {

    static String url = "https://restful-booker.herokuapp.com/booking/10";
    static Response response = given().when().get(url);

    @Test
    public void printInfoRestfulBooking() {
        System.out.println("Status Code is :" + response.statusCode());
        System.out.println("The Content type is :" + response.contentType());
        System.out.println("The Value of the Header is :" + response.header("Server"));
        System.out.println("The Status Line is :" + response.statusLine());
        System.out.println("The Time of the Response is :" + response.getTime());

    }

    // TC02
    @Test
    public void bookingTestWithAssertion() {
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());
        Assert.assertEquals("Cowboy",response.getHeader("Server"));
        Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());
    }

    @Test
    public void bookingHeaderTestWithResponse(){
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK") ;
    }


}
