package TestCases;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import PageObjects.FindOwner;
import Utilities.Base;
import Utilities.CommonMethods;

public class FindOwnerTC extends Base {
	

	FindOwner findOwnerObj= new FindOwner();
	CommonMethods commonMethodsObj = new CommonMethods();
	
	@Test(priority = 2)
	public void FindOwner_AddNewOwner_Validation() throws InterruptedException, IOException
	{
		test = ext.createTest("FindOwner_AddNewOwner_Validation");
		try {

			Navigate_FindOwner();
			Add_NewOwner();
			Validate_NewOwner();

		} catch (Exception e) {
			String SC = commonMethodsObj.TakeScreenshot("Screenshot");
			test.error("Failed", MediaEntityBuilder.createScreenCaptureFromPath("." + SC).build());
		}
		
	}
	
	@Test(priority = 3)
	public void FindOwner_AddNewPet_Validation() throws InterruptedException, IOException
	{
		test = ext.createTest("FindOwner_AddNewPet_Validation");
		try {

			AddNewPet();
			ValidatePetDetails();

		} catch (Exception e) {
			String SC = commonMethodsObj.TakeScreenshot("Screenshot");
			test.error("Failed", MediaEntityBuilder.createScreenCaptureFromPath("." + SC).build());
		}
		
	}
	
	
	public void Navigate_FindOwner() throws IOException {
		try {
			findOwnerObj.lnk_FindOwner().click();
		} catch (Exception e) {
			String SC = commonMethodsObj.TakeScreenshot("Screenshot");
			test.error("Failed", MediaEntityBuilder.createScreenCaptureFromPath("." + SC).build());
		}
	}

	public void Add_NewOwner() throws IOException {

		findOwnerObj.lnk_AddOwner().click();
		try {
			findOwnerObj.txt_FirstName().sendKeys(prop.getProperty("FirstName"));
			findOwnerObj.txt_LastName().sendKeys(prop.getProperty("LastName"));
			findOwnerObj.txt_Address().sendKeys(prop.getProperty("Address"));
			findOwnerObj.txt_City().sendKeys(prop.getProperty("City"));
			findOwnerObj.txt_Telephone().sendKeys(prop.getProperty("Telephone"));
			findOwnerObj.btn_AddOwner().click();
		} catch (Exception e) {
			String SC = commonMethodsObj.TakeScreenshot("Screenshot");
			test.error("Failed", MediaEntityBuilder.createScreenCaptureFromPath("." + SC).build());
		}
	}

	public void Validate_NewOwner() throws IOException {
		try {			
			String OwnerName = prop.getProperty("FirstName").concat(" ").concat(prop.getProperty("LastName"));
			Assert.assertTrue(findOwnerObj.lbl_OwnerName().getText().toString().equals(OwnerName),"Added Owner Name is not matching");
			Assert.assertTrue(findOwnerObj.lbl_OwnerAddress().getText().toString().equals(prop.getProperty("Address")),"Added Owner Address is not matching");
			Assert.assertTrue(findOwnerObj.lbl_OwnerCity().getText().toString().equals(prop.getProperty("City")),"Added Owner City is not matching");
			Assert.assertTrue(findOwnerObj.lbl_OwnerTelephone().getText().toString().equals(prop.getProperty("Telephone")),"Added Owner Telephone number is not matching");

			test.log(Status.PASS, "New Owner details are validated successfully");			

		} catch (Exception e) {
			String SC = commonMethodsObj.TakeScreenshot("Screenshot");
			test.error("Failed", MediaEntityBuilder.createScreenCaptureFromPath("." + SC).build());
		}

	}
	
	public void AddNewPet() throws IOException {
		try {
			findOwnerObj.lnk_AddNewPet().click();
			findOwnerObj.txt_PetName().sendKeys(prop.getProperty("PetName"));
			findOwnerObj.dp_PetDateOfBirth().sendKeys(prop.getProperty("PetDateOfBirth"));

			Select PetType = new Select(findOwnerObj.drp_PetType());
			PetType.selectByVisibleText(prop.getProperty("Type"));
			findOwnerObj.btn_AddPet().click();

		} catch (Exception e) {
			String SC = commonMethodsObj.TakeScreenshot("Screenshot");
			test.error("Failed", MediaEntityBuilder.createScreenCaptureFromPath("." + SC).build());
		}

	}

	public void ValidatePetDetails() throws IOException {
		try {			
			String PetDateOfBirth = prop.getProperty("PetDateOfBirth");
			PetDateOfBirth = new SimpleDateFormat("yyyy-MM-dd")
					.format(new SimpleDateFormat("MM/dd/yyyy").parse(PetDateOfBirth));
			Assert.assertTrue(findOwnerObj.lbl_PetName().getText().toString().equals(prop.getProperty("PetName")),"Added Pet Name is not matching");
			Assert.assertTrue(findOwnerObj.lbl_PetDateOfBirth().getText().toString().equals(PetDateOfBirth),"Added Pet Date Of Birth is not matching");
			Assert.assertTrue(findOwnerObj.lbl_PetType().getText().toString().equals(prop.getProperty("Type")),"Added Pet Type is not matching");

			test.log(Status.PASS, "Pet Details are validated successfully");			

		} catch (Exception e) {
			String SC = commonMethodsObj.TakeScreenshot("Screenshot");
			test.error("Failed", MediaEntityBuilder.createScreenCaptureFromPath("." + SC).build());
		}
	}



}
