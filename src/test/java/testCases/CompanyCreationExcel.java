package testCases;

import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CreateCompanyPage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.ExcelUtils;

public class CompanyCreationExcel extends BaseClass{
	String CompanyUserName;
	String CompanyEmail;
	String CompanyName;
	
	@DataProvider(name = "CompanyData")
	public Object[][] getCompanyTestData() throws IOException {
	    return ExcelUtils.getCompanyData("CompanyCreationPage");
	}
	@Test(dataProvider = "CompanyData", priority = 1, groups = "CompanyTests")
	public void company_creation_excel(String testCaseId, String runMode, String scenarioType,
	    String parkName, String phaseName, String buildingName,String companyName,String Location, String userName,String confirmPassword, String contactName, String email,
	    String altEmail, String contactNumber, String password, String expectedMessage) throws Exception {

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
	    Cp.clickViewAllPhase();
	    Cp.viewPhase(phaseName);
	    Cp.scrollToBottom();
	    Cp.clickViewAllBuildings();
	    Cp.viewBuilding(buildingName);
	    Cp.clickCreateCompanyButton();

	    CompanyName = Cp.setCompanyName(companyName);
	    Thread.sleep(1000);
	    Cp.setLocation(Location);
	    Cp.parkfoundIndropDown(parkName);
	    Cp.phasefoundIndropDown(phaseName);
	    Cp.buildingfoundIndropDown(buildingName);
	    Cp.setSelectFloor();

	    CompanyUserName = Cp.setCompanyLoginUsername(userName);
	    Cp.setCompanyLoginPassword(password);
	    Cp.setCompanyLoginRePassword(password);
	    Cp.setContactName(contactName);
	    CompanyEmail = Cp.setContactEmail(email);
	    Cp.setContactAlternateEmail(altEmail);
	    Cp.setContactNumber(contactNumber);
	    Cp.scrollToBottom();
	    Cp.clickSubmitButton();
	    Thread.sleep(6000);

	    // Validate the expected message based on the scenario type
	    if(expectedMessage.equals("Company Admin created successfully and credentials send to email.")){ 
	    	String actualMessage = Cp.getSuccessMessage();
	    Assert.assertEquals(actualMessage, expectedMessage);
	    driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']")).click();
	    System.out.println("✅ Company creation successful: " + actualMessage);
	    }
	    else if (expectedMessage.equals("Contact number must contain only numeric characters")) {
	        String actualMessage = Cp.getInvalidContactNumberMessage2();
	        Assert.assertEquals(actualMessage, expectedMessage);
	        System.out.println("✅ Contact number validation passed");
	        

	    } else if (expectedMessage.equals("Please enter a valid email id")) {
	        String actualMessage = Cp.getInvalidEmailMessage();
	        Assert.assertEquals(actualMessage, expectedMessage);
	        System.out.println("✅ Email id validation passed");

	    } else if (expectedMessage.equals("Please enter a valid alternate email id")) {
	        String actualMessage = Cp.getInvalidAlternateEmailMessage();
	        Assert.assertEquals(actualMessage, expectedMessage);
	        System.out.println("✅ Alternate email id validation passed");

	    } else if (expectedMessage.equals("Contact name must start with a letter and contain only alphanumeric characters")) {
	        String actualMessage = Cp.getInvalidContactNameMessage();
	        Assert.assertEquals(actualMessage, expectedMessage);
	        System.out.println("✅ Contact name validation passed");

	    } else if (expectedMessage.equals("Please enter a valid email id")) {
	        String actualMessage = Cp.getInvalidEmailMessage();
	        Assert.assertEquals(actualMessage, expectedMessage);
	        System.out.println("✅ Invalid email validation passed");

	    } else if (expectedMessage.equals("User with same Username exists")) {
	        String actualMessage = Cp.getUserExistsMessage();
	        Assert.assertEquals(actualMessage, expectedMessage);
	        System.out.println("✅ Duplicate username validation passed");

	   
	    } else {
	        Assert.fail("⚠️ No matching validation handler found for: " + expectedMessage);
	    }


	    lp.ProfileIconClick();
		lp.clickLogout();



	}

}
