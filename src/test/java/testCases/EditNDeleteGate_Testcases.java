package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CreateGatePage;
import pageObjects.CreateParkAdminPage;
import pageObjects.EditNDeleteGate;
import pageObjects.EditNDeleteParkAdminPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class EditNDeleteGate_Testcases extends BaseClass{
	String firstParkName;
	String firstPhaseName;
	String newgatename;
	String newGateUsername;
	String newGateEmail;
	String newGateContactNumber;
	String updatedgatename;
	String getfirstgateName;
	
	@Test(priority=0,groups="Positive",enabled=true, description="TC_001_Navigate to Create Gate page by clicking on the Create Gate button")
	public void LoginIntoEpass_AndNavigateTo_AllGatesPage() throws InterruptedException
	{
		//Login into ePass application
		LoginPage lp2=new LoginPage(driver);
		lp2.setuserName(p.getProperty("username"));
		lp2.setpassWord(p.getProperty("password"));
		lp2.setSubmit();
		// Navigate to the ParkList page and clicking on eye icon to view the first park
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		cpa.goToPark_button();
		firstParkName=cpa.getFirstParkName(); // This will store the first park name for later verification
		cpa.eyeIcon_button();		
		// Navigate to the All Phases List page and clicking on eye icon to view the first phase
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.clickViewAllPhasesLink();
		System.out.println("Clicked on All Phases link");
		firstPhaseName=cgp.getFirstPhaseName(); // This will store the first phase name for later verification
		cgp.clickEyeIconForFirstPhaseInTheList();
		System.out.println("Clicked on Eye Icon for first phase in the list");
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(50));
		// Navigate to the All Gates List page
		cgp.clickViewAllGatesLink();
		Thread.sleep(4000); // Adding a wait to ensure the page is fully loaded
		System.out.println("Clicked on View All Gates link");			
	}
	
	@Test(priority=1,groups="Positive",enabled=true, description="TC_002_Navigate to Edit Gate page by clicking on the edit icon")
	public void CreateNewGate_andNavigateTo_EditGatePage_ByClickingOn_EditIcon() throws InterruptedException
	{		
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		CreateGatePage cgp = new CreateGatePage(driver);
		// Creating a new gate
		cgp.clickCreateGateButton();
		newgatename="Gate No: "+ cpa.randomNumeric();
		System.out.println("New Gate Name: " + newgatename); // Debugging line to check the new gate name
		cgp.setGateReferenceId(newgatename.trim()); // Setting a valid gate reference ID
		newGateUsername=cpa.randomString();
		cgp.setGateUsername(newGateUsername);
		cgp.setGatePassword(p.getProperty("parkpassword").trim());
		cgp.setGateConfirmPassword(p.getProperty("parkpassword").trim());		
		cgp.setGateContactName(newGateUsername);
		newGateEmail=newGateUsername+"@gmail.com";
		cgp.setGateContactEmail(newGateEmail);		
	    newGateContactNumber=cpa.randomNumeric();
		cgp.setGateContactNumber(newGateContactNumber);
		cpa.scrollToBottom();
		Thread.sleep(1000);
		cgp.clickSubmitGateButton();
	//	Thread.sleep(2000); 
		cgp.closeButton();
		// Wait for the gate creation to complete
		//Navigating to the Edit page of newly created gate
		String createdGateName=newgatename;
		EditNDeleteGate edg = new EditNDeleteGate(driver);
		edg.gate_EditIcon_click(createdGateName);
		String expectedPageHeader = "Edit Gate";
		String actualPageHeader = edg.getEditIconPageHeader();
		System.out.println("Actual Page Header: " + actualPageHeader);
		Assert.assertEquals(actualPageHeader, expectedPageHeader);		
      }        
	
	@Test(priority=2,groups="Negative",enabled=true, description="TC_003_Verify Edit Gate functionality with blank input values")
	public void Verify_validation_with_Blank_inputValuesIn_EditGatePage() throws InterruptedException
	{
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		cpa.scrollToBottom();
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateReferenceId("  "); // Setting blank gate name / number
		cgp.setGateContactName("  "); // Setting blank gate username
		cgp.setGateContactEmail("  "); // Setting blank gate email
		cgp.setGateContactNumber("  "); // Setting blank gate contact number
		String expedtedmsg4="Contact number is required";
		System.out.println("Expected Gate Contact Number value is: " + expedtedmsg4);
		String actualmsg4=cgp.getGateContactNumberRequiredValidation();
		System.out.println("Actual Gate Contact Number value is: " + actualmsg4);
			
		
	}
	
	@Test(priority=3,groups="Negative",enabled=true, description="TC_004_Verify Edit Gate functionality with gate name starting with special character")
	public void Verify_editGateName_SpecialChar_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);		
		cgp.setGateReferenceId(p.getProperty("stringstartswithspecialchar").trim());
		String expedtedmsg="Gate name/number should not start with a special character"; 
		System.out.println("Expected gate name  value is: " + expedtedmsg);
		String actualmsg=cgp.getGateNameSpecialCharValidation();
		System.out.println("Actual gate name value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=4,groups="Negative",enabled=true, description="TC_005_Verify Edit Gate functionality with gate contact name starting with special character")
	public void Verify_editGateContactName_SpecialChar_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateContactName(p.getProperty("stringstartswithspecialchar").trim());
		String expedtedmsg="Contact name must start with a letter and contain only alphanumeric characters"; 
		System.out.println("Expected Gate Contact Name value is: " + expedtedmsg);
		String actualmsg=cgp.getSpecialCharInGateContactName();
		System.out.println("Actual Gate Contact Name value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=5,groups="Negative",enabled=true, description="TC_006_Verify Edit Gate functionality with invalid gate contact email")
	public void Verify_Invalid_editGateContactEmail_Validation() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateContactEmail(p.getProperty("invalidvalue1").trim());
		String expedtedmsg="Please enter a valid email id"; 
		System.out.println("Expected Gate Contact Email value is: " + expedtedmsg);
		String actualmsg=cgp.getInvalidGateContactEmailValidation();
		System.out.println("Actual Gate Contact Email value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=6,groups="Negative",enabled=true, description="TC_007_Verify Edit Gate functionality with non-numeric contact number")
	public void Verify_nonNumericContactNumber_Validation_inEditGatepage() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateContactNumber(p.getProperty("invalidvalue1").trim());
		String expedtedmsg="Contact number must contain only numeric characters"; 
		System.out.println("Expected Gate Contact Number value is: " + expedtedmsg);
		String actualmsg=cgp.getNonNumericGateContactNumberValidation();
		System.out.println("Actual Gate Contact Number value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=7,groups="Negative",enabled=true, description="TC_008_Verify Create Gate functionality with contact number less than ten digits")
	public void Verify_GateContactNumber_LessThanTen_Validation_inEditGatepage() {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateContactNumber(p.getProperty("invalidvalue2").trim());
		String expedtedmsg="Contact number must contain at least 10 numeric characters"; 
		System.out.println("Expected Gate Contact Number value is: " + expedtedmsg);
		String actualmsg=cgp.getGateContactNumberLessThanTenValidation();
		System.out.println("Actual Gate Contact Number value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}
	
	@Test(priority=8,groups="Negative",enabled=true, description="TC_009_Verify Create Gate functionality with continuous sequence of ten same numbers")
	public void Verify_Edit_Gate_with_ContinuousSequenceOfTenSameNumbers() throws InterruptedException {
		CreateGatePage cgp = new CreateGatePage(driver);
		cgp.setGateContactNumber(p.getProperty("invalidvalue3").trim());
		String expedtedmsg="Continuous sequence of 10 same numbers is not allowed"; 
		System.out.println("Expected Gate Contact Number value is: " + expedtedmsg);
		String actualmsg=cgp.getGateContactNumberSequenceValidation();
		System.out.println("Actual Gate Contact Number value is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);	
	}	
	
	@Test(priority=9,groups="Negative",enabled=true, description="TC_010_Verify Create Gate functionality with existing  email")
	public void Verify_CreateGateFunctionalityWithExistingGateContactEmail() throws InterruptedException {
		CreateGatePage cgp = new CreateGatePage(driver);
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		EditNDeleteGate edg = new EditNDeleteGate(driver);
		cgp.setGateReferenceId("Gate No: "+ cpa.randomNumeric().trim()); // Setting a valid gate reference ID
		String newGateContactname=cpa.randomString();	
		cgp.setGateContactName(newGateContactname);
		cgp.setGateContactEmail(p.getProperty("existingmail")); 		
		String newGateContactNumber2=cpa.randomNumeric();
		cgp.setGateContactNumber(newGateContactNumber2);
//		cpa.scrollToBottom();
//		Thread.sleep(1000);
		edg.clickSaveButton(); // Clicking the save button to update the gate details
		Thread.sleep(4000); // Adding a wait to ensure the page is fully loaded
		String expedtedmsg="User with same Email exists"; 
		System.out.println("Expected Gate creation success message is: " + expedtedmsg);
		String actualmsg=cgp.getGateUserWithSameEmailExistsMessage();
		System.out.println("Actual Gate creation success message is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);
	}
	
	@Test(priority=10,groups="Positive",enabled =true, description="TC_011_Verify updating Gate with valid input values")
	public void UpdateGate_with_validInputValues() throws InterruptedException
	{
		CreateGatePage cgp = new CreateGatePage(driver);
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		EditNDeleteGate edg = new EditNDeleteGate(driver);
		updatedgatename="UpdatedGate No: "+ cpa.randomNumeric();
		cgp.setGateReferenceId(updatedgatename.trim()); // Setting a valid gate reference ID
		String updatedGateContactName= "Updated" + cpa.randomString();
		cgp.setGateContactName(updatedGateContactName);
		String updatedGateEmail=updatedGateContactName+"@gmail.com";
		cgp.setGateContactEmail(updatedGateEmail);		
		String updatedGateContactNumber=cpa.randomNumeric();
		cgp.setGateContactNumber(updatedGateContactNumber);
//		cpa.scrollToBottom();
//		Thread.sleep(1000);
		edg.clickSaveButton(); // Clicking the save button to update the gate details
		Thread.sleep(4000); // Adding a wait to ensure the page is fully loaded
		String expedtedmsg="Gate updated sucessfully"; // Using the first park name stored earlier
		System.out.println("Expected Gate updation success message is: " + expedtedmsg);
		String actualmsg= edg.getGateUpdationSuccessMessage();
		System.out.println("Actual Gate creation success message is: " + actualmsg);
		Assert.assertEquals(actualmsg, expedtedmsg);
	}

	@Test(priority=11,groups="Positive",enabled =true, description="TC_012_Verify deleting Gate")
	public void Delete_ParkAdmin() throws InterruptedException
	{

		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);

		EditNDeleteGate edg = new EditNDeleteGate(driver);
		edg.DeleteIcon_click(updatedgatename); // Using the updated  gate name to click on the delete icon	
		cpa2.scrollToTop();
		String Expectedmsg="Gate deleted sucessfully";	
		System.out.println("Expected message after deleting: " + Expectedmsg);
		String Actualmsg=edg.getGateDeletionSuccessMessage();
		System.out.println("Actual message after deleting: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}	


}
