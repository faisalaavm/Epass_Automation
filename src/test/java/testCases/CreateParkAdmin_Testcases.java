package testCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CreateParkAdminPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class CreateParkAdmin_Testcases extends BaseClass{
	
	public static String newusername;
	public static String newemail;
	public static String newcontactname;
	public static String newContactnumber;
	public static String firstParkName;
	
	
	@Test(priority=1,groups="Positive",description="TC_001_Navigate to Create Park Admin page and verify page header")
	public void navigateTo_CreateParkAdminPage() throws InterruptedException
	{
		//Login into E-pass
		LoginPage lp2=new LoginPage(driver);
		lp2.setuserName(p.getProperty("username"));
		lp2.setpassWord(p.getProperty("password"));
		lp2.setSubmit();
		//Go to CreateParkAdmin page
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		cpa.goToPark_button();
		firstParkName=cpa.getFirstParkName();
		System.out.println("First Park Name: " + firstParkName); // Debugging line to check the first park name
//		cpa.eyeIcon_button(ss.getProperty("parkname").trim());
		cpa.eyeIcon_button();
		Thread.sleep(2000);
		cpa.createParkAdmin_button();
		String Expectedmsg="Create Park Admin";
		String Actualmsg=cpa.getCreateParkAdminPageHeader();
		System.out.println("Actual message: " + Actualmsg); // Debugging line to check the actual message
		Assert.assertEquals(Actualmsg, Expectedmsg);
		System.out.println("Navigated to Create Park Admin page successfully.");
    }
	

	@Test(priority=2,groups="Positive",enabled=true, description="TC_002_Verify that default dropdown value for Park Admin is displayed")
	public void defaultDropdownValue_For_ParkAdmin_displayed() throws InterruptedException
	{
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
//		String expectedmsg=ss.getProperty("parkname").trim();		
		String Expedtedmsg= firstParkName;
		System.out.println("Expected message for first park name: " + Expedtedmsg); // Debugging line to check the expected message
		String Actualmsg=cpa.getDropdownValue();
		System.out.println("Dropdown value for first park name: is: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expedtedmsg);
	 }
	
	@Test(priority=3,groups="Negative",enabled=true, description="TC_003_Verify validation when creating Park Admin with blank input values")
	public void CreateParkAdmin_Blank_inputValues() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkUserName(" ");
		cpa2.setParkPassword(" ");
		cpa2.setParkConfirmPassword(" ");
		cpa2.scrollToBottom();
		Thread.sleep(1000);
		cpa2.setSubmitParkAdminButton();
		cpa2.scrollToTop();
//		Thread.sleep(2000);
		String Expectedmsg="Username is required";
		String Actualmsg=cpa2.getUsernameBlankvalidation();
		System.out.println("Actual message: " + Actualmsg); // Debugging line to check the actual message
		Assert.assertEquals(Actualmsg, Expectedmsg);
		String Expectedmsg2="Password is required";
		String Actualmsg2=cpa2.getPasswordBlankvalidation();
		System.out.println("Actual message for password blank validation: " + Actualmsg2); // Debugging line to check the actual message
		Assert.assertEquals(Actualmsg2, Expectedmsg2);
		String Expectedmsg3="Contact name is required";
		String Actualmsg3=cpa2.getContactNameBlankvalidation();
		System.out.println("Actual message for contact name blank validation: " + Actualmsg3); // Debugging line to check the actual message
		Assert.assertEquals(Actualmsg3, Expectedmsg3);
		String Expectedmsg4="Email id is required";
		String Actualmsg4=cpa2.getEmailIdBlankvalidation();
		System.out.println("Actual message for email blank validation: " + Actualmsg4); // Debugging line to check the actual message
		Assert.assertEquals(Actualmsg4, Expectedmsg4);
		String Expectedmsg5="Contact number is required";
		String Actualmsg5=cpa2.getContactNumberBlankvalidation();
		System.out.println("Actual message for contact number blank validation: " + Actualmsg5); // Debugging line to check the actual message
		Assert.assertEquals(Actualmsg5, Expectedmsg5);		
	}
	
	@Test(priority=4,groups="Negative",enabled=true, description="TC_004_Verify validation when username starts with a number")
	public void CreateParkAdmin_Username_StartsWith_Number() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkUserName(p.getProperty("invalidvalue2"));
//		Thread.sleep(1000);
		String Expectedmsg="Username should not start with a number";
		String Actualmsg=cpa2.getUsernameStartsWithNumberValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	@Test(priority=5,groups="Negative", enabled =true, description="TC_005_Verify validation for special characters in username")
	public void CreateParkAdmin_with_specialCharacters_inUsername() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkUserName(p.getProperty("invalidvalue1"));
//		Thread.sleep(1000);
		String Expectedmsg="Username must contain only alphanumeric characters";
		String Actualmsg=cpa2.getUsernameAsSpecialCharValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	@Test(priority=6,groups="Negative",enabled =true, description="TC_006_Verify validation for password length less than eight characters")
	public void CreateParkAdmin_with_PasswordLength_lessThanEight() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkPassword(p.getProperty("passwdlessthan8"));
//		Thread.sleep(1000);
		String Expectedmsg="Password length must be greater than 8 characters";
		String Actualmsg=cpa2.getPasswordLessThanEightValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	@Test(priority=7,groups="Negative", enabled =true, description="TC_007_Verify validation for invalid password format")
	public void CreateParkAdmin_with_InvalidPassword() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkPassword(p.getProperty("invalidvalue1"));
//		Thread.sleep(1000);
		String Expectedmsg="Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)";
		String Actualmsg=cpa2.getInvalidPasswordValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	@Test(priority=8,groups="Negative", enabled =true, description="TC_008_Verify validation when password and confirm password do not match")
	public void CreateParkAdmin_with_Password_and_ConfirmPassword_not_matching() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);	
		cpa2.setParkPassword(p.getProperty("parkpassword"));
		cpa2.setParkConfirmPassword(p.getProperty("invalidvalue1"));
//		Thread.sleep(1000);
		String Expectedmsg="Passwords do not match";
		String Actualmsg=cpa2.passworddNotMatchingvalidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=9,groups="Negative",enabled =true, description="TC_009_Verify validation for special characters in contact name")	
	public void CreateParkAdmin_with_specialCharacters_inContactName() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.scrollToBottom();
//		Thread.sleep(1000);
		cpa2.setParkContactName(p.getProperty("invalidvalue1"));		
//		Thread.sleep(2000);
		String Expectedmsg="Contact name must start with a letter and contain only alphanumeric characters";
		String Actualmsg=cpa2.getSpecialCharInContactNameValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=10,groups="Negative",enabled =true, description="TC_010_Verify validation for invalid email format")
	public void CreateParkAdmin_with_invalidEmail() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkEmail(p.getProperty("invalidvalue1"));
//		Thread.sleep(1000);
		String Expectedmsg="Please enter a valid email id";
		String Actualmsg=cpa2.getInvalidEmailValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=11,groups="Negative", enabled =true, description="TC_11_Verify validation for invalid alternate email format")
	public void CreateParkAdmin_with_invalidAlternateEmail() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkAlterEmail(p.getProperty("invalidvalue1"));
//		Thread.sleep(1000);
		String Expectedmsg="Please enter a valid alternate email id";
		String Actualmsg=cpa2.getInvalidAlternateEmailValidation();
  	    Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=12,groups="Negative", enabled =true, description="TC_012_Verify validation for non-numeric contact number")
	public void CreateParkAdmin_with_nonNumericContactNumber() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkContactNumber(p.getProperty("invalidvalue1"));
//		Thread.sleep(1000);
		String Expectedmsg="Contact number must contain only numeric characters";
		String Actualmsg=cpa2.getNonNumericContactNumberValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=13,groups="Negative", enabled =true, description="TC_013_Verify validation for contact number with less than ten digits")
	public void CreateParkAdmin_with_lessThanTenNumericContactNumber() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);		
		cpa2.setParkContactNumber(p.getProperty("invalidvalue2"));
//		Thread.sleep(1000);
		String Expectedmsg="Contact number must contain at least 10 numeric characters";
		String Actualmsg=cpa2.getContactNumberLessThanTenValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	@Test(priority=14,groups="Negative",enabled =true, description="TC_014_Verify validation for continuous sequence of ten same numbers in contact number")
	public void CreateParkAdmin_with_ContinuousSequenceOfTenSameNumbers() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkContactNumber(p.getProperty("invalidvalue3"));
//		Thread.sleep(1000);
		String Expectedmsg="Continuous sequence of 10 same numbers is not allowed";
		String Actualmsg=cpa2.getContactNumberSequenceValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	@Test(priority=15,groups="Positive", enabled =true, description="TC_015_Verify creating Park Admin with valid input values")
	public void CreateParkAdmin_with_ValidInputValues() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.scrollToTop();
//		Thread.sleep(2000);
		newusername=cpa2.randomString();
		cpa2.setParkUserName(newusername);
		cpa2.setParkPassword(p.getProperty("parkpassword"));
		cpa2.setParkConfirmPassword(p.getProperty("parkpassword"));
		cpa2.scrollToBottom();
//		Thread.sleep(2000);
		newcontactname=newusername;
		cpa2.setParkContactName(newcontactname);
		newemail=newusername+"@gmail.com"; // Generating a random email
		cpa2.setParkEmail(newemail);
		cpa2.setParkAlterEmail(p.getProperty("parkalteremail"));
		newContactnumber=cpa2.randomNumeric();
		cpa2.setParkContactNumber(newContactnumber);		
		cpa2.setSubmitParkAdminButton();
//		Thread.sleep(4000);		
		String Expectedmsg="Park Admin created successfully";
		String Actualmsg=cpa2.getParkAdminCreationSuccessMessage();
		Assert.assertEquals(Actualmsg, Expectedmsg);
		
	}
	@Test(priority=16,groups="Negative", enabled =true, description="TC_016_Verify validation for creating Park Admin with existing username")
	public void CreateParkAdmin_with_ExistingUsername() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.createParkAdmin_button();
		cpa2.setParkUserName(newusername); // Using the same user name as before
		cpa2.setParkPassword(p.getProperty("parkpassword"));
		cpa2.setParkConfirmPassword(p.getProperty("parkpassword"));
		cpa2.scrollToBottom();
		Thread.sleep(2000);
		cpa2.setParkContactName(p.getProperty("parkcontactname"));
		String newemail2=cpa2.randomString()+"@gmail.com"; // Generating a random email
		cpa2.setParkEmail(newemail2); 
		cpa2.setParkAlterEmail(p.getProperty("parkalteremail"));
		cpa2.setParkContactNumber(p.getProperty("parkcontactnumber"));		
		cpa2.setSubmitParkAdminButton();
//		Thread.sleep(4000);		
		String Expectedmsg="Park Admin with same Username exists";
		String Actualmsg=cpa2.getParkAdminWithSameUsernameExistsMessage();
		Assert.assertEquals(Actualmsg, Expectedmsg);
		
	}
	@Test(priority=17,groups="Negative",enabled =true, description="TC_017_Verify validation for creating Park Admin with existing email")
	public void CreateParkAdmin_with_ExistingEmail() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.scrollToTop();
//		Thread.sleep(2000);
		String newusername2=cpa2.randomString(); // Generating a random user name
		cpa2.setParkUserName(newusername2);
		cpa2.setParkPassword(p.getProperty("parkpassword"));
		cpa2.setParkConfirmPassword(p.getProperty("parkpassword"));
		cpa2.scrollToBottom();
//		Thread.sleep(2000);
		cpa2.setParkContactName(p.getProperty("parkcontactname"));
		cpa2.setParkEmail(newemail); // Using the same email as before
		cpa2.setParkAlterEmail(p.getProperty("parkalteremail"));
		cpa2.setParkContactNumber(p.getProperty("parkcontactnumber"));		
		cpa2.setSubmitParkAdminButton();
//		Thread.sleep(4000);		
		String Expectedmsg="User with same Email exists";
		String Actualmsg=cpa2.getUserWithSameEmailExistsMessage();
		Assert.assertEquals(Actualmsg, Expectedmsg);
		
	}
	

}