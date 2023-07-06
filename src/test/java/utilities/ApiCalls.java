package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiCalls {


     // we will create a dynamic method for Response
    // This method will return response, and we use with Matchers Class
    // This method for  https://reqres.in/api/users
    public static Response checkUserExistWithID(int id,int statuscode,String email
    ,String first_name, String last_name){

        Response response = given().when().get(BaseUrl.reqresInUserID(id));
        response.then()
                .assertThat()
                .statusCode(statuscode)
                .contentType("application/json; Charset=utf-8")
                .body("data.email", equalTo(email),
                        "data.first_name",equalTo(first_name),
                        "data.last_name",equalTo(last_name));
        return response;
    }


    // This method for Herokuapp Booking
    public static Response checkBookingIsExist(int id, int statuscode,String firstname,
                                               String lastname,int totalprice, boolean depositpaid,
                                               String checkin, String checkout){
        Response response = given().when().get(BaseUrl.herokuappBookingID(id));
        response.then()
                .assertThat()
                .statusCode(statuscode)
                .contentType("application/json; Charset=utf-8")
                .body("firstname",equalTo(firstname),"lastname",equalTo(lastname),
                        "totalprice",equalTo(totalprice),"depositpaid",equalTo(depositpaid),
                        "bookingdates.checkin",equalTo(checkin),"bookingdates.checkout",equalTo(checkout));
        return response;
    }


    public static Response checkUserExistWithIDJsonPath(int id,int statuscode,String email
            ,String first_name, String last_name){

        Response response = given().when().get(BaseUrl.reqresInUserID(id));
        response.then()
                .assertThat()
                .statusCode(statuscode)
                .contentType("application/json; Charset=utf-8");
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(email,jsonPath.getString("data.email"));
        Assert.assertEquals(first_name,jsonPath.getString("data.first_name"));
        Assert.assertEquals(last_name,jsonPath.getString("data.last_name"));

        return response;
    }


    public static Response allNameListReqresIn(int statuscode, String name){
        Response response = given().when().get(BaseUrl.reqresInUsers());
        response.then()
                .statusCode(statuscode)
                .contentType("application/json; Charset=utf-8");

        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(jsonPath.getList("data.first_name").contains(name));
        return response ;
    }





}
