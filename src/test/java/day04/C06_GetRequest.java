package day04;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import utilities.BaseUrlSpec;

import static io.restassured.RestAssured.given;

public class C06_GetRequest extends BaseUrlSpec {

    //TC07
    @Test
    public void userId6ReqresInExist() {
        // reqresIn brings us this uri https://reqres.in/
        // we will implement endpoints for this uri with pathPrams method
        // endpoints == > api/users/6

        reqresIn.pathParams("1st endpoint", "api", "2nd endpoint", "users", "3rd endpoint", 6);
        Response response = given()
                .spec(reqresIn)
                .when()
                .get("{1st endpoint}/{2nd endpoint}/{3rd endpoint}");

        response.then()
                .statusCode(200)
                .contentType("application/json; Charset=utf-8")
                .body("data.id", Matchers.equalTo(6),
                        "data.email", Matchers.equalTo("tracey.ramos@reqres.in"),
                        "data.first_name", Matchers.equalTo("Tracey"),
                        "data.last_name", Matchers.equalTo("Ramos"),
                        "data.avatar", Matchers.equalTo("https://reqres.in/img/faces/6-image.jpg"));
    }

    // TC08
    @Test
    public void bookingId433IsExist(){
       // bookingId ==>> https://restful-booker.herokuapp.com/
        bookingId.pathParams("1st","booking","2nd",433); // booking/433
        Response response = given()
                .spec(bookingId)
                .when()
                .get("{1st}/{2nd}");

        // response.prettyPrint();

        response.then()
                .statusCode(200)
                .contentType("application/json; Charset=utf-8")
                .body("firstname", Matchers.equalTo("Josh"),
                        "lastname", Matchers.equalTo("Allen"),
                        "totalprice", Matchers.equalTo(111),
                        "depositpaid", Matchers.equalTo(true),
                        "bookingdates.checkin", Matchers.equalTo("2018-01-01"),
                        "bookingdates.checkout", Matchers.equalTo("2019-01-01"),
                        "additionalneeds", Matchers.equalTo("super bowls"));

    }


    //TC07 (we will test with index not with id)
    @Test
    public void userId6ReqresIsExist() {
        // reqresIn brings us this uri https://reqres.in/
        // we will implement endpoints for this uri with pathPrams method
        // endpoints == > api/users/6
        reqresIn.pathParams("1st", "api", "2nd", "users");
        Response response = given()
                .spec(reqresIn)
                .when()
                .get("{1st}/{2nd}");

        response.then()
                .statusCode(200)
                .contentType("application/json; Charset=utf-8")
                .body("data[5].id", Matchers.equalTo(6),
                        "data[5].email", Matchers.equalTo("tracey.ramos@reqres.in"),
                        "data[5].first_name", Matchers.equalTo("Tracey"),
                        "data[5].last_name", Matchers.equalTo("Ramos"),
                        "data[5].avatar", Matchers.equalTo("https://reqres.in/img/faces/6-image.jpg"));
    }


}
