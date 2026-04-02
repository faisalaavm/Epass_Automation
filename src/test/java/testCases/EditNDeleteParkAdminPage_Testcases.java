package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CreateParkAdminPage;
import pageObjects.EditNDeleteParkAdminPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class EditNDeleteParkAdminPage_Testcases extends BaseClass{
	
	public static String updatedcontactname;
	public static String updatedEmail;
	public static String updatedcontactnumber;
	String createdusername;
	String createdcontactname;
	String firstParkName;
	
	
	@Test(priority=1,groups="Positive",enabled=true, description="TC_001_Navigate to Edit Park Admin page by clicking on the edit icon")
	public void navigateTo_EditParkAdmin_ByClickingOn_EditIcon() throws InterruptedException
	{
		LoginPage lp2=new LoginPage(driver);
		lp2.setuserName(p.getProperty("username"));
		lp2.setpassWord(p.getProperty("password"));
		lp2.setSubmit();
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		cpa.goToPark_button();
		firstParkName=cpa.getFirstParkName();
		cpa.eyeIcon_button();
		createdusername= CreateParkAdmin_Testcases.newusername;
		System.out.println("Created Username: " + createdusername); // Debugging line to check the created username
		EditNDeleteParkAdminPage ead=new EditNDeleteParkAdminPage(driver);
		ead.EditIcon_click(createdusername); // Using the created username to click on the edit icon
		String Expectedmsg="Edit Park Admin";
		String Actualmsg=ead.getEditIconPageHeader();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
//		Thread.sleep(3000);
	}	
	
	@Test(priority=2,groups="Positive",enabled=true, description="TC_002_Verify that default dropdown value is displayed in the Edit Park Admin page")
	public void defaultDropdownValue_displayed() throws InterruptedException
	{
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
//		String expectedmsg=ss.getProperty("parkname").trim();
		String Expedtedmsg= firstParkName;
		String Actualmsg=cpa.getDropdownValue();
		System.out.println("Dropdown value is: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expedtedmsg);
	 }
	
	@Test(priority=3,groups="Negative",enabled=true, description="TC_003_Verify validation when updating Park Admin with blank input values")
	public void UpdateParkAdmin_Blank_inputValues() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkContactName("  ");
		cpa2.setParkEmail(" ");
		cpa2.setParkContactNumber(" ");
		String Expectedmsg="Contact name is required";
		String Actualmsg=cpa2.getContactNameBlankvalidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
		String Expectedmsg2="Email id is required";
		String Actualmsg4=cpa2.getEmailIdBlankvalidation();
		System.out.println("Actual message for email blank validation: " + Actualmsg4); // Debugging line to check the actual message
		Assert.assertEquals(Actualmsg4, Expectedmsg2);
		String Expectedmsg3="Contact number is required";
		String Actualmsg5=cpa2.getContactNumberBlankvalidation();
		System.out.println("Actual message for contact number blank validation: " + Actualmsg5); // Debugging line to check the actual message
		Assert.assertEquals(Actualmsg5, Expectedmsg3);	
	}
	
	@Test(priority=4,groups="Negative",enabled=true, description="TC_004_Verify validation for special characters in contact name")
	public void UpdateParkAdmin_with_specialCharacters_inContactName() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
//		Thread.sleep(2000);
		cpa2.setParkContactName(p.getProperty("invalidvalue1"));		
		String Expectedmsg="Contact name must start with a letter and contain only alphanumeric characters";	
		String Actualmsg=cpa2.getSpecialCharInContactNameValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=5,groups="Negative",enabled =true, description="TC_005_Verify validation for invalid email in Park Admin update")
	public void UpdateParkAdmin_with_invalidEmail() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkEmail(p.getProperty("invalidvalue1"));
//		Thread.sleep(1000);
		String Expectedmsg="Please enter a valid email id";
		String Actualmsg=cpa2.getInvalidEmailValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=6,groups="Negative",enabled =true, description="TC_006_Verify validation for invalid alternate email in Park Admin update")
	public void UpdateParkAdmin_with_invalidAlternateEmail() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkAlterEmail(p.getProperty("invalidvalue1"));
//		Thread.sleep(1000);
		String Expectedmsg="Please enter a valid alternate email id";
		String Actualmsg=cpa2.getInvalidAlternateEmailValidation();
  	    Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=7,groups="Negative",enabled =true, description="TC_007_Verify validation for non-numeric contact number in Park Admin update")
	public void UpdateParkAdmin_with_nonNumericContactNumber() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkContactNumber(p.getProperty("invalidvalue1"));
//		Thread.sleep(1000);
		cpa2.scrollToBottom();
		String Expectedmsg="Contact number must contain only numeric characters";
//		Thread.sleep(1000);
		String Actualmsg=cpa2.getNonNumericContactNumberValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=8,groups="Negative", enabled =true, description="TC_008_Verify validation for contact number less than ten digits in Park Admin update")
	public void UpdateParkAdmin_with_contactNumberLessThanTen() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);		
		cpa2.setParkContactNumber(p.getProperty("invalidvalue2"));
//		Thread.sleep(1000);
		String Expectedmsg="Contact number must contain at least 10 numeric characters";
		String Actualmsg=cpa2.getContactNumberLessThanTenValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=9,groups="Negative", enabled =true, description="TC_009_Verify validation for contact number with continuous sequence in Park Admin update")
	public void UpdateParkAdmin_with_contactNumberSequence() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		cpa2.setParkContactNumber(p.getProperty("invalidvalue3"));
//		Thread.sleep(1000);
		String Expectedmsg="Continuous sequence of 10 same numbers is not allowed";
		String Actualmsg=cpa2.getContactNumberSequenceValidation();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=10,groups="Negative",enabled =true, description="TC_010_Verify updating email with already existing mail id of another admin")
	public void UpdateParkAdmin_with_existingEmail() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		createdcontactname= CreateParkAdmin_Testcases.newcontactname; // Getting the created contact name from the previous test case
		cpa2.setParkContactName(createdcontactname);
		cpa2.setParkEmail(p.getProperty("existingmail"));
		cpa2.setParkAlterEmail(p.getProperty("parkalteremail"));
		updatedcontactnumber=cpa2.randomNumeric(); // Generating a random contact number
		cpa2.setParkContactNumber(updatedcontactnumber);
		EditNDeleteParkAdminPage ead=new EditNDeleteParkAdminPage(driver);
		ead.clickSaveButton();
		Thread.sleep(2000);
		String Expectedmsg="Email ID exists";
		String Actualmsg=ead.getEmailExistsValidationMessage();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=11,groups="Positive", enabled =true, description="TC_011_Verify updating Park Admin with valid input values")
	public void UpdateParkAdmin_with_validInputValues() throws InterruptedException
	{
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
//		Thread.sleep(2000);
		createdcontactname= CreateParkAdmin_Testcases.newcontactname; // Getting the created contact name from the previous test case
		cpa2.setParkContactName(createdcontactname);
		updatedEmail=cpa2.randomString()+"@newmail.com"; // Generating a random email
		cpa2.setParkEmail(updatedEmail);
		String updatedAlterEmail=cpa2.randomString()+"@newmail.com"; // Generating a random email
		cpa2.setParkAlterEmail(updatedAlterEmail); // Using the same email for alternate email
		cpa2.setParkContactNumber(updatedcontactnumber);		
		EditNDeleteParkAdminPage ead=new EditNDeleteParkAdminPage(driver);
		ead.clickSaveButton();
//		Thread.sleep(4000);
		String Expectedmsg="Park Admin updated successfully";
		String Actualmsg=ead.getParkAdminUpdationSuccessMessage();
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=12,groups="Positive", enabled =true, description="TC_012_Verify deleting a Park Admin")
	public void Delete_ParkAdmin() throws InterruptedException
	{
		createdusername= CreateParkAdmin_Testcases.newusername; // Getting the created username from the previous test case
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		EditNDeleteParkAdminPage ead=new EditNDeleteParkAdminPage(driver);	
		ead.DeleteIcon_click(createdusername); // Using the created username to click on the delete icon		
//		Thread.sleep(5000);
		cpa2.scrollToTop();
		String Expectedmsg="Park admin deleted successfully";		
		String Actualmsg=ead.getParkAdminDeletionSuccessMessage();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}	

}
