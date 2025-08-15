package TestCases;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import utilities.Bookingutilities;

public class getBookingListByClass {
Bookingutilities util =new Bookingutilities();
	
	
	@Test(priority=4)
	public void getBookings() throws FileNotFoundException {
		util.viewBookingByClass("THIRDAC");
	}
}
