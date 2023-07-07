package day05;

import org.junit.Test;

import static utilities.ApiCalls.checkBookingIsExist;

public class C10_GetRequest {

    @Test
    public void bookingId433IsExist(){
        checkBookingIsExist(433,200,"John","Smith",111,true,
                "2018-01-01","2019-01-01");
    }

    @Test
    public void bookingId43IsExist(){
        checkBookingIsExist(43,200,"Jane","Smith",111,true,
                "2018-01-01","2019-01-01");
    }





}
