package testCases;

import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.ParkCreation;
import testBase.BaseClass;
import utilities.Log;

public class ParkCretion_Testcases extends BaseClass{ 
	String randomParkName;
	@Test(priority=1,groups="Negative",description="TC_001_Verify park creation with blank field values")
	public void verify_Park_Creation_blankvalues() throws InterruptedException {

		LoginPage lp=new LoginPage(driver);
		Log.info("Submit with blank details");
		lp.setuserName("superadmin");
		lp.setpassWord("Admin@1234");
		lp.setSubmit();
		Log.info("Creating a new park with blank values");
		ParkCreation PC=new ParkCreation (driver);
		PC.GoToParksButton();
		Log.info("Clicking on Create Park button");
		PC.CreateParkButton2();
		PC.setparkName("");
		PC.setparkLocation("");
		PC.setparkStreetAddress("");
		PC.setparkAdditionalStreetAddress("");
		PC.setparkCity("");
		PC.setparkDistrict("");
		PC.setparkState("");
		PC.setparkPinCode("");
		PC.scrollToBottom();
		PC.setCreateParkButton();
		PC.scrollToTop();
		Log.info("Displaying validation message and compare the results");
		String expectedmsg="Park name is required"; 
		String actualmsg=PC.validation();
		Assert.assertEquals(actualmsg,expectedmsg);
		System.out.println("Verification of Park name validation message is successful");
	}
	@Test(priority=2,groups="Negative",description="TC_002_Verify park creation with invalid field values")
	public void verify_Park_Creation_invalidvalues() throws InterruptedException {


		ParkCreation PC=new ParkCreation (driver);
		PC.setparkName("1212");
		PC.setparkLocation("';][';';");
		PC.setparkCity("656565");
		PC.setparkState("4656");
		PC.setparkDistrict("666");
		PC.setparkPinCode("hghjh");
		PC.scrollToBottom();
		PC.scrollToTop();	
		//			
		Log.info("Displaying validation message and compare the results");
		String expectedmsg="Park name must start with a letter and contain only alphanumeric characters";
		//			Thread.sleep(2000);
		String actualmsg=PC.validation2();
		Assert.assertEquals(actualmsg,expectedmsg);

		Log.info("Displaying validation message and compare the results");
		String expectedmsg1="Location must start with a letter and contain only alphanumeric characters";
		//			Thread.sleep(2000);
		String actualmsg1=PC.validation3();

		Assert.assertEquals(actualmsg1,expectedmsg1);

		Log.info("Displaying validation message and compare the results");
		String expectedmsg2="City must contain only letters";
		//			Thread.sleep(2000);
		String actualmsg2=PC.validation4();

		Assert.assertEquals(actualmsg2,expectedmsg2);

		Log.info("Displaying validation message and compare the results");
		String expectedmsg3="District must contain only letters";
		//			Thread.sleep(2000);
		String actualmsg3=PC.validation5();

		Assert.assertEquals(actualmsg3,expectedmsg3);

		Log.info("Displaying validation message and compare the results");
		String expectedmsg4="Pin code must contain only numbers";
		//			Thread.sleep(2000);
		String actualmsg4=PC.validation6();

		Assert.assertEquals(actualmsg4,expectedmsg4);
	}

	@Test(priority=3,groups="Positive",description="TC_003_Verify park creation with valid field values")
	public void verify_Park_Creation_valid_values() throws InterruptedException, IOException {


		ParkCreation PC=new ParkCreation (driver);
		
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
		PC.setCreateParkButton();
		
		System.out.println("Park name: " + randomParkName);

		Log.info("Displaying validation message and compare the results");
		String expectedmsg="Park created successfully"; 
		System.out.println("Expected message: " + expectedmsg);

		String actualmsg=PC.get_ParkCreationSuccessMessage();
		System.out.println("Actual message: " + actualmsg);
		
		Assert.assertEquals(actualmsg, expectedmsg, "Success message not displayed as expected.");
		p.setProperty("RandomParkName", randomParkName);

		try (FileOutputStream out = new FileOutputStream("./src//test//resources//config.properties")) {
			p.store(out, "Saved Park Name ");
		}
	}

	@Test(priority = 4,groups="Positive", description = "TC_004 Verify park name is displayed in the list after creation")
	public void verifyparkNameInListAfterCreation() throws InterruptedException {
		ParkCreation PC= new ParkCreation(driver);


		boolean isParkNameDisplayed = PC.isParkNameDisplayed(randomParkName);
		Assert.assertTrue(isParkNameDisplayed, "Created park name is not displayed in the list.");
	}

	@Test(priority=5,groups="Negative",description="TC_005_Verify park name with same name exists")
	public void verify_Park_Creation_samename() throws InterruptedException {


		ParkCreation PC=new ParkCreation (driver);
		
		PC.CreateParkButton2();
		PC.setparkName("Infopark Cherthala");
		PC.setparkLocation("Kochi");		
		PC.setparkStreetAddress("Test street");
		PC.setparkAdditionalStreetAddress("Test additional street");
		PC.setparkCity("Ernakulam");
		PC.setparkDistrict("Ernakulam");
		PC.setparkState("Kerala");
		PC.setparkPinCode("683101");
		PC.scrollToBottom();
		PC.setCreateParkButton();
				
		Log.info("Displaying validation message and compare the results");
		String expectedmsg="Park with same name exists"; 

		String actualmsg=PC.ParkNameExistsValidation();
		Assert.assertEquals(actualmsg,expectedmsg);
	}	



	@Test(priority=7,groups="Positive",description="TC_006_Verify redirection from park creation page")
	public void verify_Park_CreationGoBack() throws InterruptedException {


		ParkCreation PC=new ParkCreation (driver);
		PC.scrollToTop();

		PC.GoBackButton();

		Thread.sleep(5000);			
		Log.info("Redirecting to parks page and compare the results");
		String expectedtitle="Park List"; 
		System.out.println("Expected title: " + expectedtitle);

		String actualtitle=PC.ParklistTitle();
		Assert.assertEquals(actualtitle,expectedtitle);
	}	

	@Test(priority=8,groups="Positive",description="TC_007_Verify logo is displayed on the page")
	public void verifyLogoIsDisplayed() {
		// XPath to the SVG logo
		By logoLocator = By.xpath("(//img[@alt='logo'])[1]\r\n"
				+ "");

		// Find the element
		WebElement logoElement = driver.findElement(logoLocator);
		Assert.assertTrue(logoElement.isDisplayed(), "Logo is not displayed on the page");
		// Check if the element is displayed
		if (logoElement.isDisplayed()) {
			System.out.println("✅ Logo is displayed on the page.");
		} else {
			System.out.println("❌ Logo is NOT displayed on the page.");
		}
	}
	@Test(priority=6,groups="Positive",description="TC_008_Verify header text is displayed on the page")
	public void verifyHeaderTextIsDisplayed() throws InterruptedException {
		// Existing steps (like driver.get, login, etc.)
		ParkCreation PC=new ParkCreation (driver);
		Thread.sleep(2000);
		// Scroll to the top of the page
		PC.scrollToTop();
		// Verify header
		WebElement headertext = driver.findElement(By.xpath("//div[@class='text-muted' and text()='Provide the necessary information to create a park.']\r\n"
				+ ""));
		Assert.assertTrue(headertext.isDisplayed(), "Logo is not displayed on the page");
		if (headertext.isDisplayed()) {
			System.out.println("✅ Headertext is displayed correctly.");
		} else {
			System.out.println("❌ Headertext is NOT visible.");
		}}


}
