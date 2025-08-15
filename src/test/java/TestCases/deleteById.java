package TestCases;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import utilities.Bookingutilities;

public class deleteById {
Bookingutilities util =new Bookingutilities();
	
	
	@Test(priority=5)
	public void getBookings() throws FileNotFoundException {
		util.deleteBookingById();
	}
}
