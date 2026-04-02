package testCases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BuildingCreation;
import pageObjects.LoginPage;
import pageObjects.ParkCreation;
import pageObjects.View_Park_Phase_Building_Page;
import testBase.BaseClass;
import utilities.Log;

public class BuildingCreation_Testcases extends BaseClass { 

	String randomBuildingName  ;
	@Test(priority=1,groups="Negative",description="TC_001_Verify user entering blank field values")
	public void verify_Building_Creation_blankvalues() throws InterruptedException {

		LoginPage lp=new LoginPage(driver);
		
		lp.setuserName("superadmin");
		lp.setpassWord("Admin@1234");
		lp.setSubmit();
		Log.info("Submit with blank details");
		BuildingCreation BC=new BuildingCreation(driver);
		
		BC.goToParksButton();
		Log.info("Navigating to Parks page");
		View_Park_Phase_Building_Page VP=new View_Park_Phase_Building_Page(driver);
		boolean isParkFound = VP.searchForParkInPaginatedList(p.getProperty("RandomParkName"));
		Assert.assertTrue(isParkFound, "The newly created park was not found in the list.");

		// If the park is found, click the View button for that park
		if (isParkFound) {
			VP.clickViewButtonForPark(p.getProperty("RandomParkName"));
			Thread.sleep(3000);
			System.out.println("Navigated to park details page." );
		}
		BC.scrollToBottom();
		BC.clickViewAllPhasesLink();
		VP.viewPhase(p.getProperty("RandomPhaseName"));
		Thread.sleep(2000);
		BC.scrollToBottom();
		BC.viewAllBuildings();
		BC.createBuildingButton();
		BC.setBuildingNameInput("");
		BC.setNumberOfFloorsInput("");
		BC.scrollToBottom();
		BC.setCreateBuildingButton();

		Log.info("Displaying validation message and compare the results");
		String expectedmsg="Building name is required"; 
		Thread.sleep(2000);
		String actualmsg=BC.buildingNameMandatoryValidation();
		Assert.assertEquals(actualmsg,expectedmsg);
	}
	@Test(priority=2,groups="Negative",description="TC_002_Verify user entering invalid field values")
	public void verify_Building_Creation_invalidValues() throws InterruptedException {



		BuildingCreation BC=new BuildingCreation(driver);

		BC.setBuildingNameInput(p.getProperty("invalidbuildingname"));
		BC.setNumberOfFloorsInput("105");
		BC.scrollToBottom();
		BC.setCreateBuildingButton();

		Log.info("Displaying validation message and compare the results");
		String expectedmsg="Building name must start with a letter and contain only alphanumeric characters"; 
		Thread.sleep(2000);
		String actualmsg=BC.buildingNameInvalidValueValidation();
		Assert.assertEquals(actualmsg,expectedmsg);
	}
	@Test(priority=3,groups="Positive",description="TC_003_Verify user entering valid field values")
	public void verify_Building_Creation_validValues() throws InterruptedException, IOException {



		BuildingCreation BC=new BuildingCreation(driver);
		randomBuildingName = BC.randomString(); // Generate a unique park name
		BC.setBuildingNameInput(randomBuildingName);
		//BC.setBuildingNameInput(jj.getProperty("buildingname"));
		BC.setNumberOfFloorsInput("5");
		BC.scrollToBottom();
		BC.setCreateBuildingButton();

		Log.info("Displaying validation message and compare the results");
		String expectedmsg="Building created successfully"; 
		Thread.sleep(2000);
		String actualmsg=BC.buildingCreationSuccessMessage();
		Assert.assertEquals(actualmsg,expectedmsg);
		p.setProperty("RandomBuildingName", randomBuildingName);

		try (FileOutputStream out = new FileOutputStream("./src//test//resources//config.properties")) {
			p.store(out, "Saved Building Name ");
		}
	}

	@Test(priority=5,groups="Negative",description="TC_005_Verify user trying with same building name")
	public void verify_Building_Creation_sameBuildingName() throws InterruptedException {



		BuildingCreation BC=new BuildingCreation(driver);
		BC.createBuildingButton();
		BC.setBuildingNameInput(p.getProperty("RandomBuildingName"));
		BC.setNumberOfFloorsInput("5");
		BC.scrollToBottom();
		BC.setCreateBuildingButton();

		Log.info("Displaying validation message and compare the results");
		String expectedmsg="Building with the same name already exists"; 
		Thread.sleep(2000);
		String actualmsg=BC.buildingNameExistsValidation();
		Assert.assertEquals(actualmsg,expectedmsg);
		BC.closeButton();
	}
	@Test(priority=6,groups="Positive",description="TC_006_Verify user selecting IT Park from dropdown")
	public void verify_Building_Creation_ITParkDropdown() throws InterruptedException {


		
		BuildingCreation BC = new BuildingCreation(driver);
		String actualValue = BC.getDefaultDropdownValue();
		Assert.assertEquals(actualValue, (p.getProperty("RandomParkName")), "Default value in dropdown is incorrect.");
		System.out.println();
		Log.info("Default value in dropdown is Infopark Cherthala");


	}

	@Test(priority=7,groups="Positive",description="TC_007_Verify user selecting Phase from dropdown")
	public void verify_Building_Creation_PhaseDropdown() throws InterruptedException {



		BuildingCreation BC = new BuildingCreation(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String actualValue = BC.getPhaseDropdownValue();
		Assert.assertEquals(actualValue, (p.getProperty("RandomPhaseName")), "Default value in dropdown is incorrect.");
		System.out.println();
		Log.info("Default value in dropdown is displayed");

	}
	
	@Test(priority =4, groups="Positive", description = "TC_004 Verify building name is displayed in the list after creation")
	public void verifybuildingNameInListAfterCreation() throws InterruptedException {
		BuildingCreation BC= new BuildingCreation(driver);


		boolean isBuildingNameDisplayed = BC.isBuildingNameDisplayed(p.getProperty("RandomBuildingName"));
		Assert.assertTrue(isBuildingNameDisplayed, "Created building name is not displayed in the list.");
	}

}

