package testCases;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.PhaseAdminCreationPage;
import testBase.BaseClass;
import utilities.Log;

public class PhaseAdminCreation_Testcases extends BaseClass {
	String phaseAdminUserName ;
	String phaseAdminEmail;
	
	@Test (priority=1,groups="Negative",description="TC_001_Verify mandatory fields for Phase Admin creation")
	public void mandatory_fields_for_phase_admin_creation() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.setuserName(p.getProperty("username"));
		lp.setpassWord(p.getProperty("password"));
		lp.setSubmit();
		Log.info("Login successful");
		
		PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
		PhsAdmin.GoToParksButton();
		PhsAdmin.viewPark(p.getProperty("RandomParkName"));
		
		//PhsAdmin.eyeIcon_button();
		
		PhsAdmin.clickViewAllPhase();
		Log.info("View all phases link clicked");
		PhsAdmin.viewPhase(p.getProperty("RandomPhaseName"));
		PhsAdmin.clickCreatePhaseAdmin();
		Log.info("Create Phase Admin button clicked");
		PhsAdmin.scrollToBottom();
		PhsAdmin.clickCreatePhaseAdminSubmit();
		Log.info("Create Phase Admin submit button clicked");
		String expectedMessage = "Password is required";
		String actualMessage = PhsAdmin.getPasswordRequiredMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
		String expectedMessage2 = "Contact name is required";
		String actualMessage2 = PhsAdmin.getContactNameRequiredMessage();
		Assert.assertEquals(actualMessage2, expectedMessage2);
		String expectedMessage3 = "Email id is required";
		String actualMessage3 = PhsAdmin.getContactEmailRequiredMessage();
		Assert.assertEquals(actualMessage3, expectedMessage3);
		PhsAdmin.scrollToBottom();
		String expectedMessage4 = "Contact number is required";
		String actualMessage4 = PhsAdmin.getContactNumberRequiredMessage();
		Assert.assertEquals(actualMessage4, expectedMessage4);
		System.out.println("Mandatory fields validation for Phase Admin creation is successful.");
		Log.info("Mandatory fields validation for Phase Admin creation is successful.");
		}
	
	@Test (priority=2,groups="Negative",description="TC_002_Verify validations for email ID")
	public void creation_with_invalid_mail_id() throws InterruptedException {
		 
	PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
	PhsAdmin.setPhaseAdminContactEmail(PhsAdmin.randomAlphaNumeric()+"getnada.com");
	PhsAdmin.clickCreatePhaseAdminSubmit();
	String expectedMessage = "Please enter a valid email id";
	String actualMessage = PhsAdmin.getInvalidEmailMessage();
	Assert.assertEquals(actualMessage,expectedMessage);
	Log.info("Invalid email ID validation for Phase Admin creation is successful.");
	}

@Test (priority=3,groups="Negative",description="TC_003_Verify validations for alternate email ID")
	public void creation_with_invalid_alternate_email() throws InterruptedException {
		 
	PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
    PhsAdmin.setPhaseAdminContactAlternateEmail(PhsAdmin.randomAlphaNumeric()+"getnada.com");
	PhsAdmin.clickCreatePhaseAdminSubmit();
	String expectedMessage1 = "Please enter a valid alternate email id";
	String actualMessage1 = PhsAdmin.getInvalidAlternateEmailMessage();
	Assert.assertEquals(actualMessage1,expectedMessage1);
	Log.info("Invalid alternate email ID validation for Phase Admin creation is successful.");
	}


@Test (priority=4,groups="Negative",description="TC_004_Verify validations for contact number")
	public void creation_with_invalid_contact_number() throws InterruptedException {
		 
	PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
	PhsAdmin.setPhaseAdminContactNumber(p.getProperty("invalidcontactnumber"));
	PhsAdmin.clickCreatePhaseAdminSubmit();
	String expectedMessage = "Contact number must contain at least 10 numeric characters";
	String actualMessage = PhsAdmin.getInvalidContactNumberMessage();
	Assert.assertEquals(actualMessage,expectedMessage);
	PhsAdmin.setPhaseAdminContactNumber(p.getProperty("invalidcontactnumber2"));
	String expectedMessage1 = "Contact number must contain only numeric characters";
	String actualMessage1 = PhsAdmin.getInvalidContactNumberMessage2(); 
	Assert.assertEquals(actualMessage1,expectedMessage1);
		}

@Test (priority=5,groups="Negative",description="TC_005_Verify validations for contact name")
	public void creation_with_invalid_contact_name() throws InterruptedException {
		 
	PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
	PhsAdmin.setPhaseAdminContactName(p.getProperty("invalidcontactname"));
	PhsAdmin.clickCreatePhaseAdminSubmit();
	String expectedMessage = "Contact name must start with a letter and contain only alphanumeric characters";
	String actualMessage = PhsAdmin.getInvalidContactNameMessage();
	Assert.assertEquals(actualMessage,expectedMessage);	
	}
@Test (priority=6,groups="Negative",description="TC_006_Verify validations for username")
	public void creation_with_invalid_username() throws InterruptedException {
		 
	PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
	PhsAdmin.scrollToTop();
	PhsAdmin.setPhaseAdminUserName(p.getProperty("invalidusername"));
	String expectedMessage = "Username must contain only alphanumeric characters";
	String actualMessage = PhsAdmin.getUsernameValidationMessage();
	Assert.assertEquals(actualMessage,expectedMessage);	
	}
@Test (priority=7,groups="Negative",description="TC_007_Verify validations for password")
	public void creation_with_invalid_password() throws InterruptedException {
		 
	PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
	PhsAdmin.setPhaseAdminPassword(p.getProperty("invalidpassword"));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2)");
	String expectedMessage = "Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)";
	String actualMessage = PhsAdmin.getPasswordValidationMessage();
	Assert.assertEquals(actualMessage,expectedMessage);	
			}
	
	@Test (priority=8,groups="Positive",description="TC_008_Verify whether admin can create Phase Admin for a Phase")
	public void valid_phase_admin_creation() throws InterruptedException, FileNotFoundException, IOException {
		PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
		PhsAdmin.scrollToTop();
		phaseAdminUserName=PhsAdmin.setPhaseAdminUserName("Admin"+PhsAdmin.randomAlphaNumeric());
		PhsAdmin.setPhaseAdminPassword(p.getProperty("validpassword"));
		PhsAdmin.setPhaseAdminConfirmPassword(p.getProperty("validconfirmpassword"));
		PhsAdmin.setPhaseAdminContactName(p.getProperty("validcontactname"));
		phaseAdminEmail=PhsAdmin.setPhaseAdminContactEmail(PhsAdmin.randomAlphaNumeric()+"@getnada.com");
		PhsAdmin.scrollToBottom();
		PhsAdmin.setPhaseAdminContactAlternateEmail(PhsAdmin.randomAlphaNumeric()+"@getnada.com");
		PhsAdmin.setPhaseAdminContactNumber(p.getProperty("validcontactnumber"));
		PhsAdmin.clickCreatePhaseAdminSubmit();
		String expectedMessage = "Phase Admin created successfully";
		String actualMessage = PhsAdmin.getSuccessMessage();
		Assert.assertEquals(actualMessage,expectedMessage);
		Thread.sleep(5000);
		p.setProperty("phaseAdminUsername", phaseAdminUserName);
		p.setProperty("phaseAdminEmail", phaseAdminEmail);
		try (FileOutputStream out = new FileOutputStream("./src//test//resources//config.properties")) {
		    p.store(out, "Saved Phase Admin credentials");
		}
		  }
	 
	@Test (priority=9,groups="Negative",description="TC_009_Verify whether admin can create Phase Admin with already existing username")
	public void creation_with_already_existing_username() throws InterruptedException, FileNotFoundException, IOException {
		 
	PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
	PhsAdmin.clickCreatePhaseAdmin();
	PhsAdmin.setPhaseAdminUserName(p.getProperty("phaseAdminUsername"));
	PhsAdmin.setPhaseAdminPassword(p.getProperty("validpassword"));
	PhsAdmin.setPhaseAdminConfirmPassword(p.getProperty("validconfirmpassword"));
	PhsAdmin.setPhaseAdminContactName(p.getProperty("validcontactname"));
	PhsAdmin.setPhaseAdminContactEmail(PhsAdmin.randomAlphaNumeric()+"@getnada.com");
	PhsAdmin.scrollToBottom();
	PhsAdmin.setPhaseAdminContactAlternateEmail(PhsAdmin.randomAlphaNumeric()+"@getnada.com");
	PhsAdmin.setPhaseAdminContactNumber(p.getProperty("validcontactnumber"));
	PhsAdmin.clickCreatePhaseAdminSubmit();
	String expectedMessage = "Phase Admin with same Username exists";
	String actualMessage = PhsAdmin.getPhaseAdminExistsMessage();
	Assert.assertEquals(actualMessage,expectedMessage);
			}
	
	@Test (priority=10,groups="Negative",description="TC_10_Verify whether admin can create Phase Admin with already existing mail ID")
	public void creation_with_already_existing_email() throws InterruptedException {
		 
	PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
	PhsAdmin.scrollToTop();
	Thread.sleep(2000);
	PhsAdmin.setPhaseAdminUserName(p.getProperty("validusername"));
	PhsAdmin.scrollToBottom();
	Thread.sleep(2000);
	PhsAdmin.setPhaseAdminContactEmail(p.getProperty("phaseAdminEmail"));
	PhsAdmin.clickCreatePhaseAdminSubmit();
	Thread.sleep(2000);
	String expectedMessage = "Phase Admin with same Email ID exists";
	String actualMessage = PhsAdmin.getPhaseAdminEmailExistsMessage();
	Assert.assertEquals(actualMessage,expectedMessage);
	
		}

		
}
