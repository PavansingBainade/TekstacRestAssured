package TestCases;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import utilities.Bookingutilities;

public class getBookingList {
Bookingutilities util =new Bookingutilities();
	
	
	@Test(priority=2)
	public void getBookings() throws FileNotFoundException {
		util.getrequest();
	}
}
