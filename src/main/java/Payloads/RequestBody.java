package Payloads;

import POJO.addBooking;

public class RequestBody {
	public addBooking postbody() {
	addBooking add=new addBooking();
	add.setBookingId(15);
	add.setBookingDate("11-08-2025");
	add.setDepartCity("Chennai");
	add.setArrivalCity("Coimbatore");
	add.setPassengerCount(2);
	add.setTrainName("Express");
	add.setPassengerName("Rakesh");
	add.setTicketType("SLEEPER");
	add.setTrainClass("GENERAL");
	
	return add;
	
	}
}
