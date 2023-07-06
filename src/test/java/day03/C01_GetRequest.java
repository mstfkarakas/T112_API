package day03;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_GetRequest {

  /*
  TC01: Print the Response that returns when we send a GET request
  to https://reqres.in/api/users/
   */

   /*
   Note: To make API queries in the Intellij the io.RestAssured library is used
   and we need to create object from Response class
   Response response = given().when().get(url) ;
   given(): it refers to the initial values given to us when starting test
   when() : it refers to the operations when we performed in our test
   then() : it refers to the actions taken to evaluate the response values
   and()  : represents interconnected operations
    */

    @Test
    public void getUsersReqresIn() {

        String url = "https://reqres.in/api/users/";
        Response response = given().when().get(url);

        //Print the response
        // response.print(); // not useful
        // response.prettyPrint(); // it brings only data with json body
        //  response.prettyPeek(); // it brings all data and informations of header
        response.then().log().all(); // this command is like response.prettyPeek();
    }


}
