package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/Feature",  // Path to feature files
	    glue = "Stepdefinitions",                 // Path to step definitions
	    plugin = {"pretty", "html:target/cucumber-reports.html","json:target/cucumber.json",

		        "rerun:target/rerun.txt",
		        
		        "TestRunner.CucumberExtentReportPlugin"}
	)
public class TestRunner extends AbstractTestNGCucumberTests{

}
