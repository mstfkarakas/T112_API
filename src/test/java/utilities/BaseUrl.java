package utilities;

public class BaseUrl {


    public static String reqresInUsers() {
        String url = "https://reqres.in/api/users";
        return url;
    }

    public static String reqresInUserID(int id) {
        String url = "https://reqres.in/api/users/" + id;
        return url;
    }

    public static String herokuappBookingID(int id) {
        String url = "https://restful-booker.herokuapp.com/booking/" + id;
        return url;
    }
    public static String createBooking() {
        String url = "https://restful-booker.herokuapp.com/booking";
        return url;
    }

}
