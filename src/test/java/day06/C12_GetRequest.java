package day06;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static utilities.ApiCalls.*;
import static utilities.BaseUrl.herokuappBookingID;
import static utilities.TestData.getRequestBookingData;

public class C12_GetRequest {

    @Test
    public void bookingTest(){
        //Serialization ==> convert data from java to json
        JSONObject expectedData = getRequestBookingData();
        System.out.println(expectedData);
        //Response
        Response response = given().when().get(herokuappBookingID(43));
        // Convert response body to json
        JSONObject actualData = new JSONObject(response.getBody().asString());
        System.out.println(actualData);
        //Verify
        Assert.assertEquals(expectedData.getString("firstname"),actualData.getString("firstname"));
        Assert.assertEquals(expectedData.getString("lastname"),actualData.getString("lastname"));
        Assert.assertEquals(expectedData.getInt("totalprice"),actualData.getInt("totalprice"));
        Assert.assertEquals(expectedData.getBoolean("depositpaid"),actualData.getBoolean("depositpaid"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkin"),actualData.getJSONObject("bookingdates").getString("checkin"));
        Assert.assertEquals(expectedData.getJSONObject("bookingdates").getString("checkout"),actualData.getJSONObject("bookingdates").getString("checkout"));

   //     JSONObject expectedBookingDates = expectedData.getJSONObject("bookingdates");
   //     JSONObject actualBookingDates = actualData.getJSONObject("bookingdates");

    //    Assert.assertEquals(expectedBookingDates.getJSONObject("bookingdates").getString("checkin"),actualBookingDates.getJSONObject("bookingdates").getString("checkin"));
    //    Assert.assertEquals(expectedBookingDates.getJSONObject("bookingdates").getString("checkout"),actualBookingDates.getJSONObject("bookingdates").getString("checkout"));

    }

    @Test
    public void bookingTestSerialization(){
        serializationMethodForHerokuapBooking(43, 200,"Josh", "Allen",111.0, true,
                "2018-01-01", "2019-01-01");
    }

    @Test
    public void bookingTestSerialization01(){
        serializationMethodForHerokuapBooking(44, 200,"Josh", "Allen",111.0, true,
                "2018-01-01", "2019-01-01");
    }

}