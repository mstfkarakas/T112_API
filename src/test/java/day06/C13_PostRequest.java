package day06;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static utilities.BaseUrl.createBooking;
import static utilities.TestData.createBookingData;

public class C13_PostRequest {




    @Test
    public void createBookingTest(){
        //Expected Data
        JSONObject expectedData = createBookingData();
        //Request response
        Response response = given()
                .contentType("application/json; Charset=utf-8")
                .auth()
                .basic("admin","password123")
                .body(expectedData.toString())// if we are using JSONObject we should add .toString()
                .when()
                .post(createBooking());
        response.then()
                .assertThat()
                .statusCode(200);
        response.prettyPrint();
        // Verify the created data
        JsonPath actualData = response.jsonPath();
        System.out.println(expectedData);
        System.out.println(actualData);
        Assert.assertEquals(expectedData.getString("firstname"),actualData.getString("booking.firstname"));
        Assert.assertEquals(expectedData.getString("lastname"),actualData.getString("booking.lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"),actualData.getInt("booking.totalprice"));
        Assert.assertEquals(expectedData.getBoolean("depositpaid"),actualData.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkin"),actualData.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkout"),actualData.getString("booking.bookingdates.checkout"));

    }



}