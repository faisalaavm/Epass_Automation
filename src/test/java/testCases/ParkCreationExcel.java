package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CreateCompanyPage;
import pageObjects.LoginPage;
import pageObjects.ParkCreation;
import testBase.BaseClass;
import utilities.ExcelUtils;
import utilities.Log;

public class ParkCreationExcel extends BaseClass {
	
	
	@DataProvider(name = "ParkData")
	public Object[][] getCompanyTestData() throws IOException {
	    return ExcelUtils.getCompanyData("ParkCreationPage");
	}
	@Test(dataProvider = "ParkData", priority = 1, groups = "ParkTests")
	public void park_creation_excel(String testCaseId, String runMode, String scenarioType,
	    String parkName, String Location, String StreetOne,String StreetTwo,String City, String District,String State, String pinCode) throws Exception {

	    if (!runMode.equalsIgnoreCase("Yes")) {
	        throw new SkipException("Skipping this test case as RunMode is set to No.");
	    }

	    LoginPage lp = new LoginPage(driver);
	    lp.setuserName(p.getProperty("username"));
	    lp.setpassWord(p.getProperty("password"));
	    lp.setSubmit();

	    ParkCreation PC=new ParkCreation (driver);
		PC.GoToParksButton();
		Log.info("Clicking on Create Park button");
		PC.CreateParkButton2();
		PC.setparkName(parkName);
		PC.setparkLocation(Location);
		PC.setparkStreetAddress(StreetOne);
		PC.setparkAdditionalStreetAddress(StreetTwo);
		PC.setparkCity(City);
		PC.setparkDistrict(District);
		PC.setparkState(State);
		PC.setparkPinCode(pinCode);
		PC.scrollToBottom();
		PC.setCreateParkButton();
	    Thread.sleep(6000);
//	    // Validate the expected message based on the scenario type
//	    if(expectedMessage.equals("Company Admin created successfully and credentials send to email.")){ 
//	    	String actualMessage = Cp.getSuccessMessage();
//	    Assert.assertEquals(actualMessage, expectedMessage);
//	    driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']")).click();
//	    System.out.println("✅ Company creation successful: " + actualMessage);
//	    }
//	    else if (expectedMessage.equals("Contact number must contain only numeric characters")) {
//	        String actualMessage = Cp.getInvalidContactNumberMessage2();
//	        Assert.assertEquals(actualMessage, expectedMessage);
//	        System.out.println("✅ Contact number validation passed");
//	        
//
//	    } else if (expectedMessage.equals("Please enter a valid email id")) {
//	        String actualMessage = Cp.getInvalidEmailMessage();
//	        Assert.assertEquals(actualMessage, expectedMessage);
//	        System.out.println("✅ Email id validation passed");
//
//	    } else if (expectedMessage.equals("Please enter a valid alternate email id")) {
//	        String actualMessage = Cp.getInvalidAlternateEmailMessage();
//	        Assert.assertEquals(actualMessage, expectedMessage);
//	        System.out.println("✅ Alternate email id validation passed");
//
//	    } else if (expectedMessage.equals("Contact name must start with a letter and contain only alphanumeric characters")) {
//	        String actualMessage = Cp.getInvalidContactNameMessage();
//	        Assert.assertEquals(actualMessage, expectedMessage);
//	        System.out.println("✅ Contact name validation passed");
//
//	    } else if (expectedMessage.equals("Please enter a valid email id")) {
//	        String actualMessage = Cp.getInvalidEmailMessage();
//	        Assert.assertEquals(actualMessage, expectedMessage);
//	        System.out.println("✅ Invalid email validation passed");
//
//	    } else if (expectedMessage.equals("User with same Username exists")) {
//	        String actualMessage = Cp.getUserExistsMessage();
//	        Assert.assertEquals(actualMessage, expectedMessage);
//	        System.out.println("✅ Duplicate username validation passed");
//
//	   
//	    } else {
//	        Assert.fail("⚠️ No matching validation handler found for: " + expectedMessage);
//	    }
//
//	   	    
	    lp.ProfileIconClick();
		lp.clickLogout();


	}

}



