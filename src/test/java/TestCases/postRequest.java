package TestCases;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import utilities.Bookingutilities;

public class postRequest {
	Bookingutilities util =new Bookingutilities();
	
	
	@Test(priority=1)
	public void post() throws FileNotFoundException {
		util.postrequest();
	}
}
