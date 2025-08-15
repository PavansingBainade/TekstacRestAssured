package utilities;

public enum Resource {

    // Booking Service endpoints
    POST_ADD_BOOKING("/addBooking"),
    GET_VIEW_BOOKING_LIST("/viewBookingList"),
    GET_VIEW_BOOKING_BY_ID("/viewBookingById/"), // append bookingId when calling
    GET_VIEW_BOOKING_BY_CLASS("/viewBookingByClass"), // use query param ?trainClass=
    DELETE_BOOKING_BY_ID("/deleteBookingById/"); // append bookingId when calling

    private String resource;

    public String getResource() {
        return resource;
    }

    Resource(String resource) {
        this.resource = resource;
    }
}
