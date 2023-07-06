package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlSpec {

    protected RequestSpecification reqresIn ;
    protected RequestSpecification bookingId ;


    // Here we implemented uries (urls) in the RequestSpecification, we will call endpoinst in the test methods

    @Before
    public void setUp(){
        reqresIn = new RequestSpecBuilder().setBaseUri("https://reqres.in/").build();
        bookingId = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/").build();
    }



}
