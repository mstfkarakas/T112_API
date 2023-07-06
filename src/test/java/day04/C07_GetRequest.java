package day04;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.BaseUrlSpec;

import static io.restassured.RestAssured.given;

public class C07_GetRequest extends BaseUrlSpec {

   // TC08
    @Test
    public void usersReqresIn(){
        // 1st step create endpoints
        reqresIn.pathParams("1st", "api", "2nd", "users");

        // 2nd step create Response object
        Response response = given()
                .spec(reqresIn)
                .when().get("{1st}/{2nd}");

        // 3rd header test
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; Charset=utf-8");

        // 4th step is JsonPath
        /*
          We have learned Matchers Class to test body
          JsonPath is also provide us testing body
         difference between Matchers Class and JsonPath;
         We can not call,print same data with Matchers Class but
         we can call,print same data with JsonPath
         We can not put data in a list with Machers Class
         but we can put data in a list with JsonPath
         */
        // JsonPath is a class

        JsonPath jsonPath = response.jsonPath() ;
        // we printed all email and put in the list
        System.out.println(jsonPath.getList("data.email"));
        // we printed all first name and put in the list
        System.out.println(jsonPath.getList("data.first_name"));
        // we printed all last name and put in the list
        System.out.println(jsonPath.getList("data.last_name"));

        Assert.assertEquals(1,jsonPath.getInt("data[0].id"));
        Assert.assertEquals("george.bluth@reqres.in",jsonPath.getString("data[0].email"));
        Assert.assertEquals("George",jsonPath.getString("data[0].first_name"));
        Assert.assertEquals("Bluth",jsonPath.getString("data[0].last_name"));
        Assert.assertEquals("https://reqres.in/img/faces/1-image.jpg",jsonPath.getString("data[0].avatar"));

    }

    // TC08

    @Test
    public void userId1ReqresIn(){
        // 1st step
        reqresIn.pathParams("1st", "api", "2nd", "users","3rd",1);
        // 2nd step
        Response response = given()
                .spec(reqresIn)
                .when().get("{1st}/{2nd}/{3rd}");

        // 3rd header test
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; Charset=utf-8");

        // JsonPath is a class
        JsonPath jsonPath = response.jsonPath() ;
        Assert.assertEquals(1,jsonPath.getInt("data.id"));
        Assert.assertEquals("george.bluth@reqres.in",jsonPath.getString("data.email"));
        Assert.assertEquals("George",jsonPath.getString("data.first_name"));
        Assert.assertEquals("Bluth",jsonPath.getString("data.last_name"));
        Assert.assertEquals("https://reqres.in/img/faces/1-image.jpg",jsonPath.getString("data.avatar"));

    }

}
