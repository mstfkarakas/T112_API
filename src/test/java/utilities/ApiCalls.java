package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.Authentication.generateTokenBooking;
import static utilities.BaseUrl.createBooking;
import static utilities.BaseUrl.herokuappBookingID;

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

    public static Response deSerializationMethodForHerokuapBooking(int id, int statuscode,String firstname,
                                                                   String lastname,double totalprice, boolean depositpaid,
                                                                   String checkin, String checkout){

        // we create a dynamic map
        HashMap<String,Object> bookingdates = new HashMap<>() ;
        bookingdates.put("checkin",checkin);
        bookingdates.put("checkout",checkout);
        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname",firstname ) ;
        expectedData.put("lastname",lastname ) ;
        expectedData.put("totalprice",totalprice ) ;
        expectedData.put("depositpaid",depositpaid) ;
        expectedData.put("bookingdates", bookingdates) ;
        Response response = given().when().get(herokuappBookingID(id));
        response.then()
                .statusCode(statuscode)
                .contentType("application/json; Charset=utf-8");
        // De-Serialization
        HashMap<String, Object>actualData = response.as(HashMap.class);
        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        Assert.assertEquals(expectedData.get("checkin"),actualData.get("checkin"));
        Assert.assertEquals(expectedData.get("checkout"),actualData.get("checkout"));
        return response;
    }

    public static Response serializationMethodForHerokuapBooking(int id, int statuscode,String firstname,
                                                                 String lastname,double totalprice, boolean depositpaid,
                                                                 String checkin, String checkout) {

        // we create a dynamic map
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", checkin);
        bookingdates.put("checkout", checkout);
        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);
        Response response = given().when().get(herokuappBookingID(id));
        response.then()
                .statusCode(statuscode)
                .contentType("application/json; Charset=utf-8");
        JSONObject actualData = new JSONObject(response.getBody().asString());
        //Verify
        Assert.assertEquals(expectedData.getString("firstname"),actualData.getString("firstname"));
        Assert.assertEquals(expectedData.getString("lastname"),actualData.getString("lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"),actualData.getInt("totalprice"));
        Assert.assertEquals(expectedData.getBoolean("depositpaid"),actualData.getBoolean("depositpaid"));
        JSONObject expectedBookingDates = expectedData.getJSONObject("bookingdates");
        JSONObject actualBookingDates = actualData.getJSONObject("bookingdates");
        Assert.assertEquals(expectedBookingDates.getString("checkin"),actualBookingDates.getString("checkin"));
        Assert.assertEquals(expectedBookingDates.getString("checkout"),actualBookingDates.getString("checkout"));

        return response;
    }

    //*********************************************************************************************
        /*
    	{
        "name":"Mehmet",
        "salary":"2500",
        "age":"38"
    }


    https://dummy.restapiexample.com/api/v1/create
    45ec4201525f6e6
     */


    public static Response createBookingData(int statuscode,String firstname,
                                             String lastname,double totalprice, boolean depositpaid,String additionaneeds,
                                             String checkin, String checkout){

        // we create a dynamic map
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin",checkin);
        bookingdates.put("checkout",checkout);

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname", firstname) ;
        expectedData.put("lastname", lastname) ;
        expectedData.put("totalprice",totalprice) ;
        expectedData.put("depositpaid", depositpaid) ;
        expectedData.put("additionalneeds",additionaneeds ) ;
        expectedData.put("bookingdates", bookingdates) ;
        // We used username and password
        Response response = given()
                .contentType("application/json; Charset=utf-8")
                .auth()
                .basic("admin","password123")
                .body(expectedData.toString())// if we are using JSONObject we should add .toString()
                .when()
                .post(createBooking());
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(statuscode);

        // Verify the created data
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(expectedData.getString("firstname"),actualData.getString("booking.firstname"));
        Assert.assertEquals(expectedData.getString("lastname"),actualData.getString("booking.lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"),actualData.getInt("booking.totalprice"));
        Assert.assertEquals(expectedData.getBoolean("depositpaid"),actualData.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkin"),actualData.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkout"),actualData.getString("booking.bookingdates.checkout"));
        return response;
    }


    public static Response createBookingDataUsingAuth(int statuscode,String firstname,
                                                      String lastname,double totalprice, boolean depositpaid,String additionaneeds,
                                                      String checkin, String checkout){

        // we create a dynamic map
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin",checkin);
        bookingdates.put("checkout",checkout);

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname", firstname) ;
        expectedData.put("lastname", lastname) ;
        expectedData.put("totalprice",totalprice) ;
        expectedData.put("depositpaid", depositpaid) ;
        expectedData.put("additionalneeds",additionaneeds ) ;
        expectedData.put("bookingdates", bookingdates) ;
        // we used Authorization and method to get token automatically
        Response response = given()
                .contentType("application/json; Charset=utf-8")
                .header("Authorization","Bearer" + generateTokenBooking())
                .body(expectedData.toString())// if we are using JSONObject we should add .toString()
                .when()
                .post(createBooking());
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(statuscode);

        // Verify the created data
        JsonPath actualData = response.jsonPath();
        Assert.assertEquals(expectedData.getString("firstname"),actualData.getString("booking.firstname"));
        Assert.assertEquals(expectedData.getString("lastname"),actualData.getString("booking.lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"),actualData.getInt("booking.totalprice"));
        Assert.assertEquals(expectedData.getBoolean("depositpaid"),actualData.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkin"),actualData.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkout"),actualData.getString("booking.bookingdates.checkout"));
        return response;
    }







}