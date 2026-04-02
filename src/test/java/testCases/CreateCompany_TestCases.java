package testCases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CreateCompanyPage;
import pageObjects.LoginPage;
import pageObjects.PhaseAdminCreationPage;
import testBase.BaseClass;
import utilities.Log;

public class CreateCompany_TestCases extends BaseClass{
	String CompanyUserName;
	String CompanyEmail;
	String CompanyName;
	
	@Test (priority=1,groups="Positive",description="TC_001_Verify redirection to Create Company Page")
	public void successful_company_creation() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		lp.setuserName(p.getProperty("username"));
		lp.setpassWord(p.getProperty("password"));
		lp.setSubmit();
		CreateCompanyPage Cp= new CreateCompanyPage(driver);
		Cp.clickViewAllPark();
		Cp.viewPark(p.getProperty("RandomParkName"));
		Cp.clickViewAllPhase();
		Cp.viewPhase(p.getProperty("RandomPhaseName"));
		Cp.scrollToBottom();
		Cp.clickViewAllBuildings();
		Cp.viewBuilding(p.getProperty("RandomBuildingName"));
	}

	@Test (priority=2,groups="Negative",description="TC_003_Verify blank field validation for Company Creation")
	public void blank_field_validations_for_company_creation() throws InterruptedException {
		CreateCompanyPage Cp= new CreateCompanyPage(driver);
		Cp.clickCreateCompanyButton();
		Cp.scrollToBottom();
		Cp.clickSubmitButton();
				
		String expectedMessage = "Company name is required";
		String actualMessage = Cp.getCompanyNameRequiredMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
		String expectedMessage2 = "Location is required";
		String actualMessage2 = Cp.getLocationRequiredMessage();
		Assert.assertEquals(actualMessage2, expectedMessage2);
		String expectedMessage3 = "Floor is required";
		String actualMessage3 = Cp.getFloorRequiredMessage();
		Assert.assertEquals(actualMessage3, expectedMessage3);
		String expectedMessage4 = "Username is required";
		String actualMessage4 = Cp.getUsernameRequiredMessage();
		Assert.assertEquals(actualMessage4, expectedMessage4);
		String expectedMessage5 = "Password is required";
		String actualMessage5 = Cp.getPasswordRequiredMessage();
		Assert.assertEquals(actualMessage5, expectedMessage5);
		String expectedMessage6 = "Contact name is required";
		String actualMessage6 = Cp.getContactNameRequiredMessage();
		Assert.assertEquals(actualMessage6, expectedMessage6);
		String expectedMessage7 = "HR email id is required";
		String actualMessage7 = Cp.getContactEmailRequiredMessage();
		Assert.assertEquals(actualMessage7, expectedMessage7);
		String expectedMessage8 = "Contact number is required";
		String actualMessage8 = Cp.getContactNumberRequiredMessage();
		Assert.assertEquals(actualMessage8, expectedMessage8);
		System.out.println("Blank field validation for Company Creation is successful.");
	
	}	
	
	@Test (priority=3,groups="Negative",description="TC_004_Verify username field validation for Company Creation")
	public void username_field_validations_for_company_creation() throws InterruptedException {
		
		CreateCompanyPage Cp= new CreateCompanyPage(driver);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2)");
		Cp.setCompanyLoginUsername(p.getProperty("invalidusername1"));
		Cp.getUsernameValidationMessage1();
		String expectedMessage = "Username should not start with a number";
		String actualMessage = Cp.getUsernameValidationMessage1();
		Assert.assertEquals(actualMessage,expectedMessage);	
		Cp.setCompanyLoginUsername(p.getProperty("invalidusername2"));
		String expectedMessage1 = "Username can only contain alphanumeric characters";
		String actualMessage1 = Cp.getUsernameValidationMessage();
		Assert.assertEquals(actualMessage1,expectedMessage1);	
		
			}	
	
	@Test (priority=4,groups="Negative",description="TC_005_Verify validations for password")
	public void creation_with_invalid_password() throws InterruptedException {
		 
	CreateCompanyPage Cp= new CreateCompanyPage(driver);
	Cp.setCompanyLoginPassword(p.getProperty("invalidpassword"));
	String expectedMessage = "Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)";
	String actualMessage = Cp.getPasswordValidationMessage();
	Assert.assertEquals(actualMessage,expectedMessage);	
	Cp.setCompanyLoginPassword(p.getProperty("invalidpassword1"));
	String expectedMessage1 = "Password length must be greater than 8 characters";
	String actualMessage1 = Cp.getPasswordValidationMessage1();
	Assert.assertEquals(actualMessage1,expectedMessage1);	
			}
	
	@Test (priority=5,groups="Negative",description="TC_006_Verify validations for contact name")
	public void creation_with_invalid_contact_name() throws InterruptedException {
		 
	CreateCompanyPage Cp= new CreateCompanyPage(driver);
	Cp.setContactName(p.getProperty("invalidcontactname"));
	String expectedMessage = "Contact name must start with a letter and contain only alphanumeric characters";
	String actualMessage = Cp.getInvalidContactNameMessage();
	Assert.assertEquals(actualMessage,expectedMessage);	
	}
	
	@Test (priority=6,groups="Negative",description="TC_007_Verify validations for email ID")
	public void creation_with_invalid_mail_id() throws InterruptedException {
		 
		CreateCompanyPage Cp= new CreateCompanyPage(driver);
		Cp.setContactEmail(Cp.randomAlphaNumeric()+"getnada.com");
		String expectedMessage = "Please enter a valid email id";
	String actualMessage = Cp.getInvalidEmailMessage();
	Assert.assertEquals(actualMessage,expectedMessage);}

@Test (priority=7,groups="Negative",description="TC_008_Verify validations for alternate email ID")
	public void creation_with_invalid_alternate_email() throws InterruptedException {
		 
	CreateCompanyPage Cp= new CreateCompanyPage(driver);
	Cp.scrollToBottom();
	Cp.setContactAlternateEmail(Cp.randomAlphaNumeric()+"getnada.com");
	String expectedMessage1 = "Please enter a valid alternate email id";
	String actualMessage1 = Cp.getInvalidAlternateEmailMessage();
	Assert.assertEquals(actualMessage1,expectedMessage1);}


@Test (priority=8,groups="Negative",description="TC_009_Verify validations for contact number")
	public void creation_with_invalid_contact_number() throws InterruptedException {
		 
	CreateCompanyPage Cp= new CreateCompanyPage(driver);
	Cp.setContactNumber(p.getProperty("invalidcontactnumber"));
	String expectedMessage = "Contact number must contain at least 10 numeric characters";
	String actualMessage = Cp.getInvalidContactNumberMessage();
	Assert.assertEquals(actualMessage,expectedMessage);
	Cp.setContactNumber(p.getProperty("invalidcontactnumber2"));
	String expectedMessage1 = "Contact number must contain only numeric characters";
	String actualMessage1 = Cp.getInvalidContactNumberMessage2(); 
	Assert.assertEquals(actualMessage1,expectedMessage1);
		}

@Test (priority=9,groups="Positive",description="TC_010_Verify successful creation of Company and Company Admin Credentials")
public void creation_of_valid_Company_details() throws InterruptedException, IOException {
	
	CreateCompanyPage Cp= new CreateCompanyPage(driver);
	Cp.scrollToTop();;
	CompanyName=Cp.setCompanyName("Company "+Cp.randomAlphaNumeric());
	Thread.sleep(2000);
	Cp.setLocation(p.getProperty("location"));
	Cp.setSelectFloor();
	CompanyUserName=Cp.setCompanyLoginUsername("Admin"+Cp.randomAlphaNumeric());
	Cp.setCompanyLoginPassword(p.getProperty("validpassword"));
	Cp.setCompanyLoginRePassword(p.getProperty("validpassword"));
	Cp.setContactName(p.getProperty("validcontactname"));
	CompanyEmail=Cp.setContactEmail(Cp.randomAlphaNumeric()+"@getnada.com");
	Cp.setContactAlternateEmail(Cp.randomAlphaNumeric()+"@getnada.com");
	Cp.setContactNumber(p.getProperty("validcontactnumber"));
	Cp.scrollToBottom();
	Cp.clickSubmitButton();
	System.out.println("Company Admin created successfully.");
	Thread.sleep(6000);
	p.setProperty("companyUsername", CompanyUserName);
	p.setProperty("companyEmail", CompanyEmail);
	p.setProperty("companyName", CompanyName);
	FileOutputStream out = new FileOutputStream("./src//test//resources//config.properties");
    p.store(out, "Saved Company Admin credentials");
    	}

@Test (priority=10,groups="Negative",description="TC_010_Verify validation for already existing company username")
public void already_existing_username() throws InterruptedException, IOException {
	CreateCompanyPage Cp= new CreateCompanyPage(driver);
	Cp.clickCreateCompanyButton();
	Cp.setCompanyName("Company "+Cp.randomAlphaNumeric());
	Thread.sleep(2000);
	Cp.setLocation(p.getProperty("location"));
	Cp.setSelectFloor();
	Cp.setCompanyLoginUsername(p.getProperty("companyUsername"));
	Cp.setCompanyLoginPassword(p.getProperty("validpassword"));
	Cp.setCompanyLoginRePassword(p.getProperty("validpassword"));
	Cp.setContactName(p.getProperty("validcontactname"));
	Cp.setContactEmail(p.getProperty("companyEmail"));
	Cp.setContactAlternateEmail(Cp.randomAlphaNumeric()+"@getnada.com");
	Cp.setContactNumber(p.getProperty("validcontactnumber"));
	Cp.scrollToBottom();
	Cp.clickSubmitButton();
	Thread.sleep(4000);
	String expectedMessage = "User with same Username exists";
	String actualMessage =Cp.getUserExistsMessage();
	Assert.assertEquals(actualMessage, expectedMessage);
	System.out.println("User with same Username exists");}

@Test (priority=11,groups="Negative",description="TC_011_Verify validation for already existing company mail ID")
public void already_existing_email() throws InterruptedException, IOException {
	CreateCompanyPage Cp= new CreateCompanyPage(driver);
	Cp.setCompanyName("Company "+Cp.randomAlphaNumeric());
	Thread.sleep(2000);
	Cp.setLocation(p.getProperty("location"));
	Cp.setSelectFloor();
	Cp.setCompanyLoginUsername("Admin"+Cp.randomAlphaNumeric());
	Cp.setCompanyLoginPassword(p.getProperty("validpassword"));
	Cp.setCompanyLoginRePassword(p.getProperty("validpassword"));
	Cp.setContactName(p.getProperty("validcontactname"));
	Cp.setContactEmail(p.getProperty("companyEmail"));
	Cp.setContactAlternateEmail(Cp.randomAlphaNumeric()+"@getnada.com");
	Cp.setContactNumber(p.getProperty("validcontactnumber"));
	Cp.scrollToBottom();
	Cp.clickSubmitButton();
	Thread.sleep(4000);
	String expectedMessage = "User with same Email exists";
	String actualMessage =Cp.getCompanyEmailExistsMessage();
	Assert.assertEquals(actualMessage, expectedMessage);
	System.out.println("User with same Email exists");}

@Test (priority=12,groups="Negative",description="TC_012_Verify validation for already existing company Name")
public void already_existing_company_name() throws InterruptedException, IOException {
	CreateCompanyPage Cp= new CreateCompanyPage(driver);
	Cp.setCompanyName(p.getProperty("companyName"));
	Thread.sleep(2000);
	Cp.setLocation(p.getProperty("location"));
	Cp.setSelectFloor();
	Cp.setCompanyLoginUsername("Admin"+Cp.randomAlphaNumeric());
	Cp.setCompanyLoginPassword(p.getProperty("validpassword"));
	Cp.setCompanyLoginRePassword(p.getProperty("validpassword"));
	Cp.setContactName(p.getProperty("validcontactname"));
	Cp.setContactEmail(Cp.randomAlphaNumeric()+"@getnada.com");
	Cp.setContactAlternateEmail(Cp.randomAlphaNumeric()+"@getnada.com");
	Cp.setContactNumber(p.getProperty("validcontactnumber"));
	Cp.scrollToBottom();
	Cp.clickSubmitButton();
	Thread.sleep(4000);
	String expectedMessage = "Company with same name exists";
	String actualMessage =Cp.getCompanyNameExistsMessage();
	Assert.assertEquals(actualMessage, expectedMessage);
	System.out.println("Company with same name exists");}}


	
	
	


