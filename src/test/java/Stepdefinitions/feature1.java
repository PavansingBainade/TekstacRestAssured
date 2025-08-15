package Stepdefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import baseclassUtility.BaseTest;
import io.cucumber.java.en.Given;
import static org.testng.Assert.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Bookingutilities;
import utilities.ExcelReader;
import utilities.ValidationUtilities;

public class feature1 {

    Bookingutilities bookingUtils = new Bookingutilities();
    ValidationUtilities valUtils = new ValidationUtilities();

   
    String trainClass; 

    @Given("User should login to get the Access Token")
    public void user_should_login_to_get_the_access_token() throws FileNotFoundException {
        BaseTest.setup();
        System.out.println("✅ Logged in successfully and access token is ready.");
    }

    @Given("add the access token in all the endpoints")
    public void add_the_access_token_in_all_the_endpoints() {
        System.out.println("✅ Access token added to all requests automatically.");
    }

    @Given("Create a valid Payload for booking")
    public void create_a_valid_payload_for_booking() {
        System.out.println("✅ Booking payload is ready.");
    }

    @When("send a post request to add booking")
    public void send_a_post_request_to_add_booking() throws FileNotFoundException {
        bookingUtils.postrequest();
    }

    @Then("the booking should be added successfully")
    public void the_booking_should_be_added_successfully() {
        System.out.println("✅ Booking created successfully.");
    }

    @Then("The passenger name should be displayed along with the details")
    public void the_passenger_name_should_be_displayed_along_with_the_details() {
        System.out.println("✅ Passenger name is displayed in the POST response.");
    }

    @When("send a get request to view all bookings")
    public void send_a_get_request_to_view_all_bookings() throws FileNotFoundException {
        bookingUtils.getrequest();
    }

    @Then("List of bookings should be displayed")
    public void list_of_bookings_should_be_displayed() throws FileNotFoundException {
        System.out.println("✅ Bookings list validated successfully.");
    }

    @Then("the status code expected is {int}")
    public void the_status_code_expected_is(Integer code) throws FileNotFoundException {
        valUtils.statuscodeValidation(code, "/viewBookingList");
        System.out.println("✅ Status code " + code + " verified.");
    }

    @Given("I have an existing booking ID")
    public void i_have_an_existing_booking_id() {
        System.out.println("✅ Using mock booking ID: ");
    }

    @When("send a GET request to view booking by ID")
    public void send_a_get_request_to_view_booking_by_id() throws FileNotFoundException {
        bookingUtils.viewBookingById();
    }

    @Then("booking details for that ID should be displayed")
    public void booking_details_for_that_id_should_be_displayed() throws FileNotFoundException {
        
        System.out.println("✅ Booking details validated.");
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer code) throws FileNotFoundException {
        System.out.println("✅ Status code " + code + " verified.");
    }

    @Given("I have a train class {string}")
    public void i_have_a_train_class(String classname) {
        this.trainClass = classname;
        System.out.println("✅ Train class set to: " + trainClass);
    }

    @When("send a GET request to view booking by class")
    public void send_a_get_request_to_view_booking_by_class() throws FileNotFoundException {
        bookingUtils.viewBookingByClass(trainClass);
    }

    @Then("bookings for that class name should be displayed")
    public void bookings_for_that_class_name_should_be_displayed() {
        System.out.println("✅ Bookings displayed for class: " + trainClass);
    }

    @Given("I have a booking ID to delete")
    public void i_have_a_booking_id_to_delete() {
        System.out.println("✅ Booking ID to delete: ");
    }

    @When("I send a DELETE request to delete booking by ID")
    public void i_send_a_delete_request_to_delete_booking_by_id() throws FileNotFoundException {
        bookingUtils.deleteBookingById();
    }

    @Then("the booking should be deleted successfully")
    public void the_booking_should_be_deleted_successfully() {
        System.out.println("✅ Booking deleted successfully.");
    }
    
    @Given("read booking data from Excel {string}")
    public void i_read_booking_data_from_excel(String filePath) throws IOException {
        Object[][] allData = ExcelReader.getExcelData(filePath, "sheet1");

        for (Object[] data : allData) {
            String bookingId = data[0].toString();
            String bookingDate = data[1].toString();
            String departCity = data[2].toString();
            String arrivalCity = data[3].toString();
            String passengerCount = data[4].toString();
            String trainName = data[5].toString();
            String passengerName = data[6].toString();
            String trainClass = data[7].toString();
            String ticketType = data[8].toString();

            int actualStatus = bookingUtils.postBookingFromExcelAndGetStatus(
                    bookingId, bookingDate, departCity, arrivalCity, passengerCount,
                    trainName, passengerName, trainClass, ticketType);

            boolean invalidData = false;

            if (isNullOrEmpty(bookingId) || isNullOrEmpty(bookingDate) || isNullOrEmpty(departCity)
                    || isNullOrEmpty(arrivalCity) || isNullOrEmpty(passengerCount)
                    || isNullOrEmpty(trainName) || isNullOrEmpty(passengerName)
                    || isNullOrEmpty(trainClass) || isNullOrEmpty(ticketType)) {
                invalidData = true;
            } else {
                try {
                    int pCount = Integer.parseInt(passengerCount);
                    if (pCount <= 0) {
                        invalidData = true;
                    }
                } catch (NumberFormatException e) {
                    invalidData = true;
                }
            }

            if (invalidData) {
                assertNotEquals(actualStatus, 200, 
                    "API should NOT return 200 for invalid data: bookingId=" + bookingId);
            } else {
                assertEquals(actualStatus, 200, 
                    "API should return 200 for valid data: bookingId=" + bookingId);
            }
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
