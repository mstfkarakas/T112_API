package day06;

import io.restassured.http.ContentType;
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
    public void createBookingTest() {

        /*
                {
                "bookingid": 3916,
                "booking": {
                    "firstname": "Milan",
                    "lastname": "Karakas",
                    "totalprice": 500,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2021-06-01",
                        "checkout": "2021-06-10"
                    },
                    "additionalneeds": "wi-fi"
                }
                }
         */

        // Expected Data
        JSONObject expectedData = createBookingData();
        //Request response
        Response response = given().contentType(ContentType.JSON)
                .auth().basic("admin", "password123").body(expectedData.toString()) // if we are using JSONObject we should add .toString()
                .when().post(createBooking());

        response.then().assertThat().statusCode(200);

        // Verify the created data
        JSONObject actualData = new JSONObject(response.getBody().asString());

        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expectedData.getString("firstname"), actualData.getString("firstname"));
        Assert.assertEquals(expectedData.getString("lastname"), actualData.getString("lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"), actualData.getInt("totalprice"));
        Assert.assertEquals(expectedData.getBoolean("depositpaid"), actualData.getBoolean("depositpaid"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkin"), actualData.getJSONObject("bookingdates").getString("checkin"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkout"), actualData.getJSONObject("bookingdates").getString("checkout"));


    }
}