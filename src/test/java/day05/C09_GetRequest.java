package day05;

import org.junit.Test;
import utilities.ApiCalls;

import static utilities.ApiCalls.*;

public class C09_GetRequest {

//  ApiCalls apiCalls = new ApiCalls();  // ApiCalls class'indaki checkUserExistWithID method'unu static yapmak yerine boyle de cagirabiliriz.

    @Test
    public void id5IsExistInReqres() {
        checkUserExistWithID(5, 200, "charles.morris@reqres.in", "Charles", "Morris");
    }

    @Test
    public void id6IsExistInReqres() {
        checkUserExistWithID(6, 200, "charles.morris@reqres.in", "Charles", "Morris");
    }

    @Test
    public void id7IsExistInReqres() {
        checkUserExistWithID(7, 200, "charles.morris@reqres.in", "Charles", "Morris");
    }

    @Test
    public void id8IsExistInReqres() {
        checkUserExistWithID(8, 200, "charles.morris@reqres.in", "Charles", "Morris");
    }

    @Test
    public void id5IsExistInReqreswithJsonPath() {
        checkUserExistWithIDJsonPath(5, 200, "charles.morris@reqres.in", "Charles", "Morris");
    }

    @Test
    public void nameExistReqresIn() {
        allNameListReqresIn(200, "Janet");
    }

}
