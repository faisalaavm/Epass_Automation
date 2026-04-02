package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CreateCompanyPage;
import pageObjects.LoginPage;
import pageObjects.ParkCreation;
import pageObjects.PhasePage;
import pageObjects.View_Park_Phase_Building_Page;
import testBase.BaseClass;
import utilities.ExcelUtils;
import utilities.Log;

public class PhaseCreationExcel extends BaseClass {


	@DataProvider(name = "PhaseData")
	public Object[][] getCompanyTestData() throws IOException {
		return ExcelUtils.getCompanyData("PhaseCreationPage");
	}
	@Test(dataProvider = "PhaseData", priority = 1, groups = "PhaseTests")
	public void phase_creation_excel(String testCaseId, String runMode, String scenarioType,
			String parkName,String phaseName, String Location, String StreetOne,String StreetTwo,String City, String District,String State, String pinCode) throws Exception {

		if (!runMode.equalsIgnoreCase("Yes")) {
			throw new SkipException("Skipping this test case as RunMode is set to No.");
		}

		LoginPage lp = new LoginPage(driver);
		lp.setuserName(p.getProperty("username"));
		lp.setpassWord(p.getProperty("password"));
		lp.setSubmit();

		CreateCompanyPage Cp = new CreateCompanyPage(driver);
		Cp.clickViewAllPark();
		Cp.viewPark(parkName);
		View_Park_Phase_Building_Page Vp= new View_Park_Phase_Building_Page(driver);
		Log.info("Clicked on Go to Park button");

		boolean isParkFound = Vp.searchForParkInPaginatedList(parkName);
		Assert.assertTrue(isParkFound, "The newly created park was not found in the list.");

		// If the park is found, click the View button for that park
		if (isParkFound) {
			Vp.clickViewButtonForPark(parkName);
			//cpunp.clickViewButtonForParkNew(p.getProperty("park_name5"));
			Thread.sleep(5000);
			System.out.println("Navigated to park details page." );
		}
		Vp.scrollToBottom();
		System.out.println("Clicked on park eye icon");
		PhasePage pp = new PhasePage(driver);
		pp.clickViewAllPhasesLink();
		System.out.println("Clicked on view all phases link");
		pp.clickCreatePhaseButton();
		System.out.println("Clicked on create phase button");
		Thread.sleep(5000);
		pp.setPhaseName(phaseName);
		pp.setPhaseLocation(Location);
		pp.setPhasePrimaryStreetAddress(StreetOne);
		pp.setPhaseAdditionalStreetAddress(StreetTwo);
		pp.setPhaseCity(City);
		pp.setPhaseDistrict(District);
		pp.setPhaseState(State);
		pp.setPhasePinCode(pinCode);
		pp.clickCreatePhaseSubmitButton();
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


