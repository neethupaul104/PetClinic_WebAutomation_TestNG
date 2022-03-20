package TestCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PageObjects.Home;
import Utilities.Base;


public class HomeTC extends Base{
	
	
	Home homeObj= new Home();
	
	@Test(priority = 1)
	public void HomeImageIcon_Validation() throws InterruptedException, IOException
	{
		test = ext.createTest("HomeImageIcon_Validation");
		Boolean ImageStatus = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete "
				+ "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0",
				homeObj.HomeImageIcon());

		
		Assert.assertTrue(ImageStatus,"The home image is not available in home page");
	    test.log(Status.PASS, "The home image is available in home page");	
		
		
	}

}
