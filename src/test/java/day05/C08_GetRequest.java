package day05;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.BaseUrl;

import static io.restassured.RestAssured.given;

public class C08_GetRequest {
    // we can call static methods with or without class name, so BaseUrl.reqresInUsers() or reqresInUsers()
    static Response response = given().when().get(BaseUrl.reqresInUsers());
    static JsonPath jsonPath = response.jsonPath();

    //TC09
    @Test
    public void nameIsExistReqresUsersData() {
        // Header Test
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");

        System.out.println(jsonPath.getList("data.first_name"));
        Assert.assertTrue(jsonPath.getList("data.first_name").contains("George"));
    }
    //TC10
    @Test
    public void emailIsExistReqresUsersData() {
        // Header Test
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json");

        System.out.println(jsonPath.getList("data.email"));
        Assert.assertTrue(jsonPath.getList("data.email").contains("charles.morris@reqres.in"));
    }

    @Test
    public void specificDataPrint(){
        System.out.println(jsonPath.getString("data[2].email"));
        System.out.println(jsonPath.getString("data.email[2]"));
        // let's print the first 5 emails
        System.out.println(jsonPath.getString("data.email[0,1,2,3,4]"));
        // last email
        System.out.println(jsonPath.getString("data.email[-1]"));
    }

}
