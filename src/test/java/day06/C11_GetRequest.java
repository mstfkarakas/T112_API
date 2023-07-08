package day06;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.ApiCalls;
import utilities.BaseUrl;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static utilities.TestData.getBookingData;

public class C11_GetRequest {
/*
{
    "firstname": "Jim",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
 */
    @Test
    public void testFromHashMap(){
        // This is expected data using Hashmap method from the TestData class

        HashMap<String, Object> expData = getBookingData();
        System.out.println("expData = " + expData);

        Response response = given().when().get(BaseUrl.herokuappBookingID(43));  // response returns Json format, not Java.
        response.prettyPrint();
        System.out.println("This Body is Json Body Coming From Response ");
        response.prettyPrint();
        System.out.println("*********************************");
        System.out.println("This is hash map expected data created in the TestData class");
        System.out.println(expData);
        // Convert data from json to Java ===>> De-Serialization
        // Convert data from Java to json ===>> Serialization
        System.out.println("**********************************");
        /*
        Note: response returns us json body but hash map is a java method
        we can not compare them thats why we need to convert each others
         */
        // we should create hashmap and implement response in the created map to convert json body to java map body

        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData (response converted to HashMap) = " + actualData);

        //Verify
        Assert.assertEquals(expData.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expData.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expData.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expData.get("depositpaid"), actualData.get("depositpaid"));
        Assert.assertEquals(expData.get("checkin"), actualData.get("checkin"));
        Assert.assertEquals(expData.get("checkout"), actualData.get("checkout"));

    }

    @Test
    public void bookingTest() {

        ApiCalls.deSerializationMethodForHerokuapBooking(43, 200, "John",
                "Smith", 111.0, true, "2018-01-01",
                "2019-01-01");
    }
      /*  { "firstname": "John",
            "lastname": "Smith",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01" },
            "additionalneeds": "Breakfast"} */

}