package utilities;

import org.json.JSONObject;

import java.util.HashMap;

public class TestData {

    // we will create test data, it means expected data in this class
    // we will use hash map method and jsonpath method


    //Hashmap method
    public static HashMap<String,Object> getBookingData(){

        HashMap<String,Object> bookingdates = new HashMap<>() ;
        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checkout","2019-01-01");

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "John") ;
        expectedData.put("lastname", "Smith") ;
        expectedData.put("totalprice", 111.0) ;
        expectedData.put("depositpaid", true) ;
        expectedData.put("additionalneeds", "midnight snack") ;
        expectedData.put("bookingdates", bookingdates) ;
        return expectedData;
    }


    // JsonObject method
    public static JSONObject getRequestBookingData(){
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checkout","2019-01-01");

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname", "Josh") ;
        expectedData.put("lastname", "Allen") ;
        expectedData.put("totalprice", 111) ;
        expectedData.put("depositpaid", true) ;
        expectedData.put("additionalneeds", "midnight snack") ;
        expectedData.put("bookingdates", bookingdates) ;
        return expectedData;
    }

    public static JSONObject createBookingData(){
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2023-07-10");
        bookingdates.put("checkout","2023-07-12");

        JSONObject expectedData = new JSONObject();
        expectedData.put("firstname", "ErvaNaz") ;
        expectedData.put("lastname", "Sezgin") ;
        expectedData.put("totalprice", 125) ;
        expectedData.put("depositpaid", false) ;
        expectedData.put("additionalneeds", "breakfast") ;
        expectedData.put("bookingdates", bookingdates) ;
        return expectedData;
    }


    // we create this method for the expected body after the delete method
    public static JSONObject deleteBody(){
        JSONObject expectedData = new JSONObject();
        expectedData.put("status","success");
        expectedData.put("data","4850");
        expectedData.put("message","Successfully! Record has been deleted");
        return expectedData;
    }


}