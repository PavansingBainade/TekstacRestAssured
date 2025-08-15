package utilities;
import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import POJO.addBooking;
import Payloads.RequestBody;
import baseclassUtility.BaseTest;
import io.restassured.response.Response;

public class Bookingutilities {
	RequestBody request = new RequestBody();
	private int Id=1001;  // store bookingId from POST
    private String passengerName;


    public String postrequest() throws FileNotFoundException {

        addBooking bookingData = request.postbody();

        Response response = given()
                .spec(BaseTest.setup())
                .contentType("application/x-www-form-urlencoded")
                .formParam("bookingId", bookingData.getBookingId())
                .formParam("bookingDate", bookingData.getBookingDate())
                .formParam("departCity", bookingData.getDepartCity())
                .formParam("arrivalCity", bookingData.getArrivalCity())
                .formParam("passengerCount", bookingData.getPassengerCount())
                .formParam("trainName", bookingData.getTrainName())
                .formParam("passengerName", bookingData.getPassengerName())
                .formParam("ticketType", bookingData.getTicketType())
                .formParam("trainClass", bookingData.getTrainClass())
        .when()
                .post(Resource.POST_ADD_BOOKING.getResource())
        .then()
                .statusCode(200).log().all()
                .extract().response();
        System.out.println("Response Body: " + response.asString());
        passengerName=response.jsonPath().getString("[2].passengerName");
        System.out.println("Your booking was posted successfully: " + passengerName);
        String bookingId=response.jsonPath().getString("[2].bookingId");
        

        return bookingId;
    }



    public void getrequest() throws FileNotFoundException {

        given()
        .spec(BaseTest.setup())
                .when()
                .get(Resource.GET_VIEW_BOOKING_LIST.getResource())
                .then()
                .statusCode(200).log().all();
               
    }

    public void viewBookingById() throws FileNotFoundException {
        if (Id == 0) {
            throw new IllegalStateException("No bookingId found! Add booking first.");
        }

        given()
        .spec(BaseTest.setup())
        .when()
                .get(Resource.GET_VIEW_BOOKING_BY_ID.getResource() + Id)
        .then()
                .statusCode(200).log().all();
    }

    public void viewBookingByClass(String trainClass) throws FileNotFoundException {

        given()
        .spec(BaseTest.setup())
                .queryParam("trainClass", trainClass)
        .when()
                .get(Resource.GET_VIEW_BOOKING_BY_CLASS.getResource())
        .then()
                .statusCode(200).log().all();
    }
    public void deleteBookingById() throws FileNotFoundException {
        if (Id == 0) {
            throw new IllegalStateException("No bookingId found! Add booking first.");
        }
        given()
        .spec(BaseTest.setup())
        .when()
                .delete(Resource.DELETE_BOOKING_BY_ID.getResource() + Id)
        .then()
                .statusCode(200).log().all();

        System.out.println("Booking deleted successfully: " + Id);
    }

    public int postBookingFromExcelAndGetStatus(String bookingId, String bookingDate, String departCity, String arrivalCity,
            String passengerCount, String trainName, String passengerName,
            String trainClass, String ticketType) throws FileNotFoundException {

        return given()
                .spec(BaseTest.setup())
                .contentType("application/x-www-form-urlencoded")
                .formParam("bookingId", bookingId)
                .formParam("bookingDate", bookingDate)
                .formParam("departCity", departCity)
                .formParam("arrivalCity", arrivalCity)
                .formParam("passengerCount", passengerCount)
                .formParam("trainName", trainName)
                .formParam("passengerName", passengerName)
                .formParam("trainClass", trainClass)
                .formParam("ticketType", ticketType)
            .when()
                .post(Resource.POST_ADD_BOOKING.getResource())
            .then()
                .extract()
                .statusCode();
    }


}
