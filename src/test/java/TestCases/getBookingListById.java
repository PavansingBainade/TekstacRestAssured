package TestCases;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import utilities.Bookingutilities;

public class getBookingListById {
Bookingutilities util =new Bookingutilities();
	
	
	@Test(priority=3)
	public void getBookings() throws FileNotFoundException {
		util.viewBookingById();
	}
}
