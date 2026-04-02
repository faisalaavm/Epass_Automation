package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BuildingCreation;
import pageObjects.CreateCompanyPage;
import pageObjects.CreateGatePage;
import pageObjects.CreateParkAdminPage;
import pageObjects.EditNDeleteParkAdminPage;
import pageObjects.LoginPage;
import pageObjects.ParkCreation;
import pageObjects.ParkPhaseBuildingDelete;
import pageObjects.PhaseAdminCreationPage;
import pageObjects.PhasePage;
import pageObjects.View_Park_Phase_Building_Page;
import testBase.BaseClass;
import utilities.Log;

public class Regression_TestCases extends BaseClass {
	String randomParkName;
	String randomPhaseName;
	String randomBuildingName;
	String CompanyName;
	String newgatename;
	String newGateUsername;
	String newGateEmail;
	String firstParkName;
	String newusername;
	String newcontactname;
	String newemail;
	String newContactnumber;
	String phaseAdminUserName;
	String phaseAdminEmail;
	String createdusername;
	@Test(priority=1,groups="Regression",description="TC_001_Verify sucessfully creating a park, phase, building,gate and company with valid details")
	public void verify_Park() throws InterruptedException {

		LoginPage lp=new LoginPage(driver);
		lp.setuserName("superadmin");
		lp.setpassWord("Admin@1234");
		lp.setSubmit();
		System.out.println("Verified successful login");
		ParkCreation PC=new ParkCreation (driver);
		PC.GoToParksButton();
		Log.info("Clicking on Create Park button");
		PC.CreateParkButton2();
		randomParkName = PC.randomString(); // Generate a unique park name
		PC.setparkName(randomParkName);
		PC.setparkLocation("Kochi");		
		PC.setparkStreetAddress("Test street");
		PC.setparkAdditionalStreetAddress("Test additional street");
		PC.setparkCity("Ernakulam");
		PC.setparkDistrict("Ernakulam");
		PC.setparkState("Kerala");
		PC.setparkPinCode("683101");
		PC.scrollToBottom();
		Log.info("Setting park details");
		PC.setCreateParkButton();
		Thread.sleep(3000); // Wait for the park creation to complete
		PC.scrollToTop();
		System.out.println("Verified successfull creation of park");
		View_Park_Phase_Building_Page Vp= new View_Park_Phase_Building_Page(driver);
		Log.info("Clicked on Go to Park button");
		Thread.sleep(3000);
		boolean isParkFound = Vp.searchForParkInPaginatedList(randomParkName);
		Assert.assertTrue(isParkFound, "The newly created park was not found in the list.");

		// If the park is found, click the View button for that park
		if (isParkFound) {
			Vp.clickViewButtonForPark(randomParkName);
			//cpunp.clickViewButtonForParkNew(p.getProperty("park_name5"));
			Thread.sleep(3000);

		}
		Log.info("Clicked on View button for the newly created park");
		Vp.scrollToBottom();
		PhasePage pp = new PhasePage(driver);
		pp.clickViewAllPhasesLink();
		Log.info("Clicked on View All Phases link");
		pp.clickCreatePhaseButton();
		Thread.sleep(5000);
		randomPhaseName =pp.randomString();
		pp.setPhaseName(randomPhaseName);
		pp.setPhaseLocation(p.getProperty("phaselocation"));
		pp.setPhasePrimaryStreetAddress(p.getProperty("phaseprimaryaddress"));
		pp.setPhaseAdditionalStreetAddress(p.getProperty("phaseadditionalstreetaddress"));
		pp.setPhaseCity(p.getProperty("phasecity"));
		pp.setPhaseDistrict(p.getProperty("phasedisrict"));
		pp.setPhaseState(p.getProperty("phasestate"));
		pp.setPhasePinCode(p.getProperty("phasepincode"));
		pp.clickCreatePhaseSubmitButton();
		Thread.sleep(3000); // Wait for the phase creation to complete
		System.out.println("Verified successfull creation of phase");
		View_Park_Phase_Building_Page VP=new View_Park_Phase_Building_Page(driver);
		VP.viewPhase(randomPhaseName);
		Thread.sleep(2000);
		PhaseAdminCreationPage PhsAdmin= new PhaseAdminCreationPage(driver);
		PhsAdmin.clickCreatePhaseAdmin();
		Log.info("Create Phase Admin button clicked");
		PhsAdmin.scrollToBottom();
		PhsAdmin.clickCreatePhaseAdminSubmit();
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
		BuildingCreation BC=new BuildingCreation(driver);
		BC.scrollToBottom();
		BC.viewAllBuildings();
		BC.createBuildingButton();
		randomBuildingName = BC.randomString(); // Generate a unique park name
		BC.setBuildingNameInput(randomBuildingName);
		BC.setNumberOfFloorsInput("5");
		BC.scrollToBottom();
		BC.setCreateBuildingButton();
		System.out.println("Verified successfull creation of building");
		String expectedmsg="Building created successfully"; 
		Thread.sleep(2000);
		CreateCompanyPage Cp= new CreateCompanyPage(driver);
		Cp.viewBuilding(randomBuildingName);
		Cp.clickCreateCompanyButton();
		CompanyName=Cp.setCompanyName("Company "+Cp.randomAlphaNumeric());
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
		System.out.println("Verified successfull creation of company");
		Thread.sleep(6000);
		Cp.clickBackButton();
		Cp.clickViewAllPark();
		Cp.viewPark(randomParkName);
		Cp.clickViewAllPhase();
		Cp.viewPhase(randomPhaseName);
		Thread.sleep(5000);
		Cp.scrollToBottom();
		Log.info("Clicked on View All Phases link");
		CreateGatePage cgp = new CreateGatePage(driver);
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		cgp.clickViewAllGatesLink();
		Log.info("Clicked on View All Gates link");
		cgp.clickCreateGateButton();
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
		Thread.sleep(3000);
		cgp.clickSubmitGateButton();
		Thread.sleep(4000);
		Log.info("Clicked on Submit Gate button");
		System.out.println("Verified successfull creation of gate");
		Log.info("Clicking on Profile Icon");
		driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']")).click();
		lp.ProfileIconClick();
		lp.clickLogout();
		lp.setuserName(p.getProperty("username"));
		lp.setpassWord(p.getProperty("password"));
		lp.setSubmit();
		Cp.clickViewAllPark();
		ParkPhaseBuildingDelete epark=new ParkPhaseBuildingDelete(driver);
		epark.deletePark(randomParkName);
		Log.info("Clicked on Delete Park button for the newly created park");
		epark.confirmDelete();
		Thread.sleep(3000);
		System.out.println("Verified successfull deletion of park");
		driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']")).click();
		lp.ProfileIconClick();
		lp.clickLogout();
	}
	
	@Test(priority=2,groups="Regression",description="TC_002_Verify sucessfully creation of park admin  with valid details")
	public void verify_ParkAdmin() throws InterruptedException, IOException {
		//Login into E-pass
		LoginPage lp2=new LoginPage(driver);
		lp2.setuserName(p.getProperty("username"));
		lp2.setpassWord(p.getProperty("password"));
		lp2.setSubmit();
		CreateParkAdminPage cpa=new CreateParkAdminPage(driver);
		cpa.goToPark_button();
		firstParkName=cpa.getFirstParkName();
		System.out.println("First Park Name: " + firstParkName); // Debugging line to check the first park name
		cpa.eyeIcon_button();
		Thread.sleep(2000);
		cpa.createParkAdmin_button();
		CreateParkAdminPage cpa2=new CreateParkAdminPage(driver);
		newusername=cpa2.randomString();
		cpa2.setParkUserName(newusername);
		cpa2.setParkPassword(p.getProperty("parkpassword"));
		cpa2.setParkConfirmPassword(p.getProperty("parkpassword"));
		cpa2.scrollToBottom();
		newcontactname=newusername;
		cpa2.setParkContactName(newcontactname);
		newemail=newusername+"@gmail.com"; // Generating a random email
		cpa2.setParkEmail(newemail);
		cpa2.setParkAlterEmail(p.getProperty("parkalteremail"));
		newContactnumber=cpa2.randomNumeric();
		cpa2.setParkContactNumber(newContactnumber);		
		cpa2.setSubmitParkAdminButton();
		String Expectedmsg="Park Admin created successfully";
		String Actualmsg=cpa2.getParkAdminCreationSuccessMessage();
		Assert.assertEquals(Actualmsg, Expectedmsg);
		System.out.println("Successfully created Park Admin: " + Actualmsg);
		Thread.sleep(3000);
		 // Getting the created username from the previous test case
		EditNDeleteParkAdminPage ead=new EditNDeleteParkAdminPage(driver);	
		ead.DeleteIcon_click(newusername); // Using the created username to click on the delete icon		
		cpa2.scrollToTop();
		String Expectedmsg1="Park admin deleted successfully";		
		String Actualmsg1=ead.getParkAdminDeletionSuccessMessage();
		System.out.println("Successfully deleted Park Admin: " + Actualmsg1);
		Assert.assertEquals(Actualmsg1, Expectedmsg1);
	}
}


