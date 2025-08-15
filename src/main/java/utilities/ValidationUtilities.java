package utilities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;

import baseclassUtility.BaseTest;
import static org.hamcrest.Matchers.hasItem;

import io.restassured.http.ContentType;

public class ValidationUtilities {
	//  Status code validation
    public void statuscodeValidation(int expectedStatusCode, String endpoint) throws FileNotFoundException {
        given(BaseTest.setup())
            .accept(ContentType.XML) // Expect XML
        .when()
            .get(endpoint)
        .then()
            .statusCode(expectedStatusCode);
    }

    //Response body validation for XML list
    public void responseBodyValidation(String endpoint, String expectedPassenger, String expectedDepartCity) throws FileNotFoundException {
        given(BaseTest.setup())
            .accept(ContentType.XML)
        .when()
            .get(endpoint)
        .then()
            .statusCode(200)
            // XML path, expects list so use hasItem matcher
            .body("bookings.booking.passengerName", hasItem(expectedPassenger))
            .body("bookings.booking.departCity", hasItem(expectedDepartCity));
    }
    public void headerValidation() throws FileNotFoundException {
        given(BaseTest.setup())
            .accept(ContentType.JSON) //  Force JSON
        .when()
            .get(Resource.GET_VIEW_BOOKING_LIST.getResource())
        .then()
            .statusCode(200)
            .header("Content-Type", equalTo("application/json"));
    }

    
}