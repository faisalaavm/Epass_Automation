package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CreateGatePage;
import pageObjects.CreateParkAdminPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class CreateGatePage_Testcases extends BaseClass{

	String firstParkName;
	String firstPhaseName;
	String newgatename;
	String newGateUsername;
	String newGateEmail;

	
	@Test(priority=1,groups="Positive",enabled=true, description="TC_001_Navigate to Create Gate page and verify the page title")
	public void navigateToCreateGatePage() throws InterruptedException {
		//Login into ePass application
		LoginPage lp2=new LoginPage(driver);
		lp2.setuserName(p.getProperty("username"));
		lp2.setpassWord(p.getProperty("password"));
		lp2.setSubmit();
		// Navigate to the Create Gate page
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		cpa.goToPark_button();
		firstParkName=cpa.getFirstParkName(); // This will store the first park name for later verification
		cpa.eyeIcon_button();		
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.clickViewAllPhasesLink();
		System.out.println("Clicked on All Phases link");
		firstPhaseName=cgp.getFirstPhaseName(); // This will store the first phase name for later verification
		cgp.clickEyeIconForFirstPhaseInTheList();
		System.out.println("Clicked on Eye Icon for first phase in the list");
		cgp.clickViewAllGatesLink();
		System.out.println("Clicked on View All Gates link");
		cgp.clickCreateGateButton();	
		System.out.println("Clicked on Create Gate button");
		String expedtedmsg="Create Gate";
		String actualmsg=cgp.getCreateGateLabel();
		System.out.println("Dropdown value actually displayed is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);
	}
	
	@Test(priority=2,groups="Positive",enabled=true, description="TC_002_Verify DropdownValue_displayed_in_the_ITPark_dropdown")
	public void Verify_DropdownValue_displayed_in_the_ITPark_dropdown() {
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		String expedtedmsg=firstParkName; // Using the first park name stored earlier
		System.out.println("Expected dropdown value in the park is: " + expedtedmsg);
		CreateGatePage cgp = new CreateGatePage(driver);
		String actualmsg=cgp.getDropdownValueITPark();
		System.out.println("Dropdown value actually displayed is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);
	}
	
	@Test(priority=3,groups="Positive",enabled=true, description="TC_003_Verify DropdownValue_displayed_in_the_Phase_dropdown")
	public void Verify_DropdownValue_displayed_in_the_Phase_dropdown() {
		CreateGatePage cgp = new CreateGatePage(driver);
		String expedtedmsg=firstPhaseName; // Using the first park name stored earlier
		System.out.println("Expected phase name  value is: " + expedtedmsg);		
		String actualmsg=cgp.getDropdownValuePhase();
		System.out.println("Actual Dropdown value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);
	}
	
	@Test(priority=4,groups="Negative",enabled=true, description="TC_004_Verify Create Gate functionality with blank input values")
	public void Creating_gateName_with_Blank_inputValues() throws InterruptedException {
		CreateGatePage cgp = new CreateGatePage(driver);
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		Thread.sleep(5000); // Adding a wait to ensure the page is fully loaded
		cpa.scrollToBottom();
		cgp.setGateUsername(""); // Setting blank gate username
		cgp.setGatePassword(""); 
		cgp.setGateConfirmPassword(""); 
		cgp.setGateContactName(""); // Setting blank gate contact name
		cgp.setGateContactNumber(""); // Setting blank gate contact number
		// Setting blank gate password
		cgp.clickSubmitGateButton();
		String expedtedmsg6="Contact number is required";
		System.out.println("Expected Gate Contact Number value is: " + expedtedmsg6);
		String actualmsg6=cgp.getGateContactNumberRequiredValidation();
		System.out.println("Actual Gate Contact Number value is: " + actualmsg6);
		
	}
	
	

	@Test(priority=5,groups="Negative",enabled=true, description="TC_006_Verify Create Gate functionality with gate username starting with special character")
	public void Verify_gateUserName_SpecialChar_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateUsername(p.getProperty("stringstartswithspecialchar").trim());
		String expedtedmsg="Gate username must contain only alphanumeric characters"; // Using the first park name stored earlier
		System.out.println("Expected Gate username name  value is: " + expedtedmsg);
		String actualmsg=cgp.getGateUserNameSpecialCharacterValidation();
		System.out.println("Actual Gate username value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}

	@Test(priority=6,groups="Negative",enabled=true, description="TC_007_Verify Create Gate functionality with gate password less than eight characters")
	public void Verify_gatePassword_LessthanEight_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGatePassword(p.getProperty("passwdlessthan8").trim());
		String expedtedmsg="Password length must be greater than 8 characters"; // Using the first park name stored earlier
		System.out.println("Expected Gate Password value is: " + expedtedmsg);
		String actualmsg=cgp.getGatePasswdLessthanEightValidation();
		System.out.println("Actual Gate Password value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	@Test(priority=7,groups="Negative",enabled=true, description="TC_005_Verify Create Gate functionality with gate name starting with special character")
	public void Verify_gateName_SpecialChar_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);	
		//scroll to top of the page 
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		
		cgp.setGateReferenceId(p.getProperty("stringstartswithspecialchar").trim());
		String expedtedmsg="Gate name/number should not start with a special character"; // Using the first park name stored earlier
		System.out.println("Expected gate name  value is: " + expedtedmsg);
		String actualmsg=cgp.getGateNameSpecialCharValidation();
		System.out.println("Actual gate name value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	@Test(priority=8,groups="Negative",enabled=true, description="TC_008_Verify Create Gate functionality with invalid gate password")
	public void Verify_gatePassword_Invalid_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGatePassword(p.getProperty("invalidvalue1").trim());
		String expedtedmsg="Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)"; // Using the first park name stored earlier
		System.out.println("Expected Gate Password value is: " + expedtedmsg);
		String actualmsg=cgp.getGateInvalidPasswordValidation();
		System.out.println("Actual Gate Password value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=9,groups="Negative",enabled=true, description="TC_009_Verify Create Gate functionality with password and confirm password not matching")
	public void Verify_Password_and_ConfirmPassword_not_matching_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGatePassword(p.getProperty("parkpassword").trim());
		cgp.setGateConfirmPassword(p.getProperty("invalidvalue1").trim());
		String expedtedmsg="Passwords do not match"; // Using the first park name stored earlier
		System.out.println("Expected Gate Password value is: " + expedtedmsg);
		String actualmsg=cgp.getGatePasswdNotMatchingValidation();
		System.out.println("Actual Gate Password value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=10,groups="Negative",enabled=true, description="TC_010_Verify Create Gate functionality with gate contact name starting with special character")
	public void Verify_gateContactName_SpecialChar_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//scroll to bottom of the page
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		cgp.setGateContactName(p.getProperty("stringstartswithspecialchar").trim());
		String expedtedmsg="Contact name must start with a letter and contain only alphanumeric characters"; // Using the first park name stored earlier
		System.out.println("Expected Gate Contact Name value is: " + expedtedmsg);
		String actualmsg=cgp.getSpecialCharInGateContactName();
		System.out.println("Actual Gate Contact Name value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=11,groups="Negative",enabled=true, description="TC_011_Verify Create Gate functionality with invalid gate contact email")
	public void Verify_Invalid_gateContactEmail_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateContactEmail(p.getProperty("invalidvalue1").trim());
		String expedtedmsg="Please enter a valid email id"; // Using the first park name stored earlier
		System.out.println("Expected Gate Contact Email value is: " + expedtedmsg);
		String actualmsg=cgp.getInvalidGateContactEmailValidation();
		System.out.println("Actual Gate Contact Email value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=12,groups="Negative",enabled=true, description="TC_012_Verify Create Gate functionality with non-numeric contact number")
	public void Verify_nonNumericContactNumber_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateContactNumber(p.getProperty("invalidvalue1").trim());
		String expedtedmsg="Contact number must contain only numeric characters"; 
		System.out.println("Expected Gate Contact Number value is: " + expedtedmsg);
		String actualmsg=cgp.getNonNumericGateContactNumberValidation();
		System.out.println("Actual Gate Contact Number value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=13,groups="Negative",enabled=true, description="TC_013_Verify Create Gate functionality with contact number less than ten digits")
	public void Verify_GateContactNumber_LessThanTen_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateContactNumber(p.getProperty("invalidvalue2").trim());
		String expedtedmsg="Contact number must contain at least 10 numeric characters"; 
		System.out.println("Expected Gate Contact Number value is: " + expedtedmsg);
		String actualmsg=cgp.getGateContactNumberLessThanTenValidation();
		System.out.println("Actual Gate Contact Number value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=14,groups="Negative",enabled=true, description="TC_014_Verify Create Gate functionality with continuous sequence of ten same numbers")
	public void Verify_Create_Gate_with_ContinuousSequenceOfTenSameNumbers() throws InterruptedException {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateContactNumber(p.getProperty("invalidvalue3").trim());
		String expedtedmsg="Continuous sequence of 10 same numbers is not allowed"; 
		System.out.println("Expected Gate Contact Number value is: " + expedtedmsg);
		String actualmsg=cgp.getGateContactNumberSequenceValidation();
		System.out.println("Actual Gate Contact Number value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=15,groups="Positive",enabled=true, description="TC_015_Verify Create Gate functionality with valid inputs")
	public void Verify_CreateGateFunctionality() throws InterruptedException {
		CreateGatePage cgp = new CreateGatePage(driver);
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		newgatename="Gate No: "+ cpa.randomNumeric();
		cgp.setGateReferenceId(newgatename.trim()); // Setting a valid gate reference ID
		newGateUsername=cpa.randomString();
		cgp.setGateUsername(newGateUsername);
		cgp.setGatePassword(p.getProperty("parkpassword").trim());
		cgp.setGateConfirmPassword(p.getProperty("parkpassword").trim());		
		cgp.setGateContactName(newGateUsername);
		newGateEmail=newGateUsername+"@gmail.com";
		cgp.setGateContactEmail(newGateEmail);		
		String newGateContactNumber=cpa.randomNumeric();
		cgp.setGateContactNumber(newGateContactNumber);
		cpa.scrollToBottom();
		Thread.sleep(1000);
		cgp.clickSubmitGateButton();
		Thread.sleep(4000); // Adding a wait to ensure the page is fully loaded
		String expedtedmsg="Successfully created Gate, Gate User and credentials sent to the email."; // Using the first park name stored earlier
		System.out.println("Expected Gate creation success message is: " + expedtedmsg);
		String actualmsg=cgp.getGateCreationSuccessMessage();
		System.out.println("Actual Gate creation success message is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);
	}
	
	@Test(priority=16,groups="Negative",enabled=true, description="TC_016_Verify Create Gate functionality with existing gate name")
	public void Verify_CreateGateFunctionalityWithExistingGateName() throws InterruptedException {
	CreateGatePage cgp = new CreateGatePage(driver);
	CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
	cgp.clickCreateGateButton();
	cgp.setGateReferenceId(newgatename.trim()); // Using the gate name created in the previous test
	String newGateUsername=cpa.randomString();
	cgp.setGateUsername(newGateUsername);
	cgp.setGatePassword(p.getProperty("parkpassword").trim());
	cgp.setGateConfirmPassword(p.getProperty("parkpassword").trim());		
	cgp.setGateContactName(newGateUsername);
	String newGateEmail=newGateUsername+"@gmail.com";
	cgp.setGateContactEmail(newGateEmail);	
	String newGateContactNumber=cpa.randomNumeric();
	cgp.setGateContactNumber(newGateContactNumber);	
	cpa.scrollToBottom();
	Thread.sleep(2000);
	cgp.clickSubmitGateButton();
	Thread.sleep(4000); // Adding a wait to ensure the page is fully loaded
	String expedtedmsg="Gate with same name exists in this phase"; 
	System.out.println("Expected Gate creation success message is: " + expedtedmsg);
	String actualmsg=cgp.getGateWithSameNameExistsMessage();
	System.out.println("Actual Gate creation success message is: " + actualmsg);
	Assert.assertEquals(actualmsg, expedtedmsg);
	}
	
	@Test(priority=17,groups="Negative",enabled=true, description="TC_017_Verify Create Gate functionality with existing gate username")	
	public void Verify_CreateGateFunctionalityWithExistingGateUsername() throws InterruptedException {
		CreateGatePage cgp = new CreateGatePage(driver);
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		cgp.setGateReferenceId("Gate No: "+ cpa.randomNumeric().trim()); // Setting a valid gate reference ID
		cgp.setGateUsername(newGateUsername);
		cgp.setGatePassword(p.getProperty("parkpassword").trim());
		cgp.setGateConfirmPassword(p.getProperty("parkpassword").trim());		
		cgp.setGateContactName(newGateUsername);
		String newGateEmail2=newGateUsername+"@gmail.com";
		cgp.setGateContactEmail(newGateEmail2);
		cpa.scrollToBottom();
		Thread.sleep(1000);
		String newGateContactNumber2=cpa.randomNumeric();
		cgp.setGateContactNumber(newGateContactNumber2);
		cgp.clickSubmitGateButton();
		Thread.sleep(4000); // Adding a wait to ensure the page is fully loaded
		String expedtedmsg="User with same Username exists"; 
		System.out.println("Expected Gate creation success message is: " + expedtedmsg);
		String actualmsg=cgp.getGateUserWithSameUsernameExistsMessage();
		System.out.println("Actual Gate creation success message is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);
	}
	
	@Test(priority=18,groups="Negative",enabled=true, description="TC_018_Verify Create Gate functionality with existing gate email")
	public void Verify_CreateGateFunctionalityWithExistingGateContactEmail() throws InterruptedException {
		CreateGatePage cgp = new CreateGatePage(driver);
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		cgp.setGateReferenceId("Gate No: "+ cpa.randomNumeric().trim()); // Setting a valid gate reference ID
		String newGateUsername2=cpa.randomString();
		cgp.setGateUsername(newGateUsername2);
		cgp.setGatePassword(p.getProperty("parkpassword").trim());
		cgp.setGateConfirmPassword(p.getProperty("parkpassword").trim());		
		cgp.setGateContactName(newGateUsername2);
		cgp.setGateContactEmail(newGateEmail); // Using the email created in the previous test		
		String newGateContactNumber2=cpa.randomNumeric();
		cgp.setGateContactNumber(newGateContactNumber2);
		cpa.scrollToBottom();
		Thread.sleep(1000);
		cgp.clickSubmitGateButton();
		Thread.sleep(4000); // Adding a wait to ensure the page is fully loaded
		String expedtedmsg="User with same Email exists"; 
		System.out.println("Expected Gate creation success message is: " + expedtedmsg);
		String actualmsg=cgp.getGateUserWithSameEmailExistsMessage();
		System.out.println("Actual Gate creation success message is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);
	}

}
