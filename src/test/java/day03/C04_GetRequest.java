package day03;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C04_GetRequest {


    static String url = "https://restful-booker.herokuapp.com/booking/";

    // We will use Matcher Class to verify expected data with response
    //TC04
    @Test
    public void bookingID125IsExist() {
        int id = 125;
        Response response = given().when().get(url + id);
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .contentType("application/json; Charset=utf-8")
                // in the body we are writing json body as a key value
                .body("firstname", Matchers.equalTo("John"),
                        "lastname", Matchers.equalTo("Smith"),
                        "totalprice", Matchers.equalTo(111),
                        "depositpaid", Matchers.equalTo(true),
                        "bookingdates.checkin", Matchers.equalTo("2018-01-01"),
                        "bookingdates.checkout", Matchers.equalTo("2019-01-01"));
    }

    //TC05
    @Test
    public void bookingID525IsExist() {
        int id = 525;
        Response response = given().when().get(url + id);
        response.then()
                .statusCode(200)
                .contentType("application/json; Charset=utf-8")
                // in the body we are writing json body as a key value
                .body("firstname", Matchers.equalTo("Josh"),
                        "lastname", Matchers.equalTo("Allen"),
                        "totalprice", Matchers.equalTo(111),
                        "depositpaid", Matchers.equalTo(true),
                        "bookingdates.checkin", Matchers.equalTo("2018-01-01"),
                        "bookingdates.checkout", Matchers.equalTo("2019-01-01"),
                        "additionalneeds", Matchers.equalTo("super bowls"));
    }


}
