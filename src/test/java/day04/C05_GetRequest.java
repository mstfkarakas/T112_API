package day04;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C05_GetRequest {

    static String url = "https://reqres.in/api/users/";

    // using Matchers Class
    // TC06
    @Test
    public void reqresId1IsExist() {
        int id = 1;
        Response response = given().when().get(url+id);

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; Charset=utf-8")
                .body("data.id",Matchers.equalTo(1),
                        "data.email", Matchers.equalTo("george.bluth@reqres.in"),
                        "data.first_name",Matchers.equalTo("George"),
                        "data.last_name",Matchers.equalTo("Bluth"),
                        "data.avatar",Matchers.equalTo("https://reqres.in/img/faces/1-image.jpg"),
                        "support.url",Matchers.equalTo("https://reqres.in/#support-heading"),
                        "support.text",Matchers.equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }


}
