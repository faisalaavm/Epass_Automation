package testCases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CreateCompanyPage;
import pageObjects.LoginPage;
import pageObjects.PhaseAdminCreationPage;
import pageObjects.PhasePage;
import pageObjects.View_Park_Phase_Building_Page;
import testBase.BaseClass;
import utilities.Log;

/**
 * TestNG test class for verifying Phase page functionalities.
 * Contains test cases for login, phase creation, validation messages, and UI checks.
 */
public class PhasePage_Testcases extends  BaseClass {
	
	// Randomly generated phase name for testing
    /** Stores a randomly generated phase name for use across tests. */
	String randomPhaseName;
	/**
	 * TC_001: Verifies that the IT Park name is autofilled in the Create Phase form.
	 * @throws InterruptedException if thread sleep is interrupted
	 */
	@Test(priority=1,groups="Positive",description="TC_001_Verify Redirection to Create Phase page and IT Park name autofill")
	public void verifyLogin() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
	    lp.setuserName(p.getProperty("username"));
	    lp.setpassWord(p.getProperty("password"));
	    lp.setSubmit();
	    Log.info("Login successful");
        PhasePage pp = new PhasePage(driver);
        Log.info("Navigating to Park Page");
        pp.clickGoToParkButton();
        View_Park_Phase_Building_Page Vp= new View_Park_Phase_Building_Page(driver);
        Log.info("Clicked on Go to Park button");
        
    	boolean isParkFound = Vp.searchForParkInPaginatedList(p.getProperty("RandomParkName"));
        Assert.assertTrue(isParkFound, "The newly created park was not found in the list.");
        
        // If the park is found, click the View button for that park
        if (isParkFound) {
            Vp.clickViewButtonForPark(p.getProperty("RandomParkName"));
            //cpunp.clickViewButtonForParkNew(p.getProperty("park_name5"));
            Thread.sleep(3000);
            System.out.println("Navigated to park details page." );
        }
              Vp.scrollToBottom();
        
        
        
        System.out.println("Clicked on park eye icon");
        pp.clickViewAllPhasesLink();
       System.out.println("Clicked on view all phases link");
      
        pp.clickCreatePhaseButton();
        System.out.println("Clicked on create phase button");
        Thread.sleep(5000);
        Log.info("Create Phase button clicked, now checking for autofilled IT Park name");
        // Step 4: Get the autofilled park name from the dropdown
        String actualParkName = pp.getAutofilledParkNameFromDropdown();
        System.out.println("Actual park name from dropdown: '" + actualParkName + "'");
        String expectedParkName = p.getProperty("RandomParkName");
        System.out.println("Expected park name: '" + expectedParkName + "'");
        // Step 5: Assert both values match
        Assert.assertEquals(actualParkName, expectedParkName, "IT Park auto-filled value is incorrect");
    }

	/**
     * TC_002: Verifies that the 'Create Phase' title is displayed.
     * @throws InterruptedException if thread sleep is interrupted
     */
	
	@Test(priority = 2,groups="Positive", description = "TC_002 Verify 'Create Phase' title is displayed")
public void verifyCreatePhaseTitleIsDisplayed() throws InterruptedException {
	// Navigate and create a phase
    PhasePage pp = new PhasePage(driver);
    String expectedTitle = "Create Phase";
    String actualTitle = pp.getCreatePhasePageTitleText();
    System.out.println("Actual title: '" + actualTitle + "'");
    Assert.assertEquals(actualTitle, expectedTitle, "'Create Phase' title is not displayed as expected.");
    Log.info("Create Phase title verified successfully");
}
	/**
     * TC_003: Verifies that the 'Phase Details' title is displayed.
     * @throws InterruptedException if thread sleep is interrupted
     */
	@Test(priority = 3,groups="Positive", description = "TC_003 Verify 'Phase Details' title is displayed")
	public void verifyPhaseDetailsTitleIsDisplayed() throws InterruptedException {
	    PhasePage pp = new PhasePage(driver);
	    String expectedTitle = "Phase Details";
	    String actualTitle = pp. getPhasePageDetailsTitleText();
	    System.out.println("Actual title: '" + actualTitle + "'");
	    Assert.assertEquals(actualTitle, expectedTitle, "'Phase Details' title is not displayed as expected.");
	    Log.info("Phase Details title verified successfully");
	}
	
	/**
	 * TC_004: Verifies that the help text for creating a phase is displayed.
	 * @throws InterruptedException if thread sleep is interrupted
	 */
	@Test(priority = 4,groups="Positive", description = "TC_004 Verify help text for creating a phase is displayed")
	public void verifyCreatePhaseHelpTextIsDisplayed() throws InterruptedException {
	    PhasePage pp = new PhasePage(driver);
        String expectedHelpText = "Provide the necessary information to create a phase.";
	    String actualHelpText = pp.getCreatePhaseHelpText() ;
	    System.out.println("Actual help text: '" + actualHelpText + "'");
	    Assert.assertEquals(actualHelpText, expectedHelpText, "Help text is not displayed as expected.");
	    Log.info("Create Phase help text verified successfully");
	}
	
	/**
	 * TC_005: Verifies that the Pearlsoft logo is displayed on the Create Phase page.
	 * @throws InterruptedException if thread sleep is interrupted
	 */
	@Test(priority = 5,groups="Positive", description = "TC_005 Verify Pearlsoft logo is displayed on Create Phase page")
	public void verifyPearlsoftLogoIsDisplayed() throws InterruptedException {
	    PhasePage pp = new PhasePage(driver);
	    boolean isLogoDisplayed = pp.isPearlsoftLogoDisplayed();
	    Assert.assertTrue(isLogoDisplayed, "Pearlsoft logo is not displayed on the Create Phase page.");
	    Log.info("Pearlsoft logo verified successfully on Create Phase page");
	}
	
	/**
	 * TC_006: Verifies that the Infopark logo is displayed on the Create Phase page.
	 * @throws InterruptedException if thread sleep is interrupted
	 */
	@Test(priority = 6,groups="Positive", description = "TC_006  Verify Infopark logo is displayed on Create Phase page")
	public void verifyInfoparkLogoIsDisplayed() throws InterruptedException {
	    PhasePage pp = new PhasePage(driver);
	    boolean isLogoDisplayed = pp.isInfoparkLogoDisplayed();
	    Assert.assertTrue(isLogoDisplayed, "Infopark logo is not displayed on the Create Phase page.");
	    Log.info("Infopark logo verified successfully on Create Phase page");
	}
	
	/**
	 * TC_007: Verifies mandatory check for phase creation Page
	 * @throws InterruptedException if thread sleep is interrupted
	 */

	@Test(priority = 7, groups="Negative", description = "TC_007 Verify mandatory check for Phase Creation Page")
	public void verifyBlankPhaseNameValidation() throws InterruptedException {
		
	    PhasePage pp = new PhasePage(driver);
	    pp.setPhaseName("");
	    pp.setPhaseLocation("");
	    pp.setPhasePrimaryStreetAddress("");
	    pp.setPhaseAdditionalStreetAddress("");
	    pp.setPhaseCity("");
	    pp.setPhaseDistrict("");
	    pp.setPhaseState("");
	    pp.setPhasePinCode("");
	    pp.clickCreatePhaseSubmitButton();
	    String expectedMessage="Phase name is required";
		String actualMessage=pp.blankPhaseNameValidation();
		System.out.println("Actual message: '" + actualMessage + "'");
        Assert.assertEquals(actualMessage, expectedMessage);
        Log.info("Blank phase name validation message verified successfully");
        
             }
	
	 /**
     * TC_008: Verifies validation for phase name starting with a letter.
     * @throws InterruptedException if thread sleep is interrupted
     */
	
  
	    @Test(priority = 8,groups="Negative", description = "TC_008 Verify phase name must start with a letter validation")
 public void verifyPhaseNameMustStartWithLetterValidation() throws InterruptedException {
	    	
	    PhasePage pp = new PhasePage(driver);
    // Set phase name starting with a non-letter character
    
    pp.setPhaseName(p.getProperty("phasenamestartlettervalidation"));
    pp.setPhaseLocation(p.getProperty("phaselocation"));
    pp.setPhasePrimaryStreetAddress(p.getProperty("phaseprimaryaddress"));
    pp.setPhaseAdditionalStreetAddress(p.getProperty("phaseadditionalstreetaddress"));
    pp.setPhaseCity(p.getProperty("phasecity"));
    pp.setPhaseDistrict(p.getProperty("phasedisrict"));
    pp.setPhaseState(p.getProperty("phasestate"));
    pp.setPhasePinCode(p.getProperty("phasepincode"));
    pp.clickCreatePhaseSubmitButton();
    CreateCompanyPage Cp= new CreateCompanyPage(driver);
	Cp.scrollToTop();
    String expectedMessage = "Phase name must start with a letter and contain only alphanumeric characters";
    String actualMessage = pp.getPhaseNameMustStartWithLetterValidation();
    System.out.println("Actual message: '" + actualMessage + "'");
    Assert.assertEquals(actualMessage, expectedMessage);
    Log.info("Phase name must start with letter validation message verified successfully");
}

	    /**
	     * TC_009: Verifies validation for pin code containing only numbers.
	     * @throws InterruptedException if thread sleep is interrupted
	     */
	  
	    @Test(priority = 9,groups="Negative", description = "TC_009 Verify pin code must contain only numbers validation")
	    public void verifyPinCodeMustContainOnlyNumbersValidation() throws InterruptedException {
	        PhasePage pp = new PhasePage(driver);
	        pp.setPhaseName(p.getProperty("phasename"));
	        pp.setPhaseLocation(p.getProperty("phaselocation"));
	        pp.setPhasePrimaryStreetAddress(p.getProperty("phaseprimaryaddress"));
	        pp.setPhaseAdditionalStreetAddress(p.getProperty("phaseadditionalstreetaddress"));
	        pp.setPhaseCity(p.getProperty("phasecity"));
	        pp.setPhaseDistrict(p.getProperty("phasedisrict"));
	        pp.setPhaseState(p.getProperty("phasestate"));
	        pp.setPhasePinCode(p.getProperty("invalidpincode")); // Invalid pin code with letters
	        CreateCompanyPage Cp= new CreateCompanyPage(driver);
	    	Cp.scrollToBottom();
	        pp.clickCreatePhaseSubmitButton();
	        String expectedMessage = "Pin code must contain only numbers";
	        String actualMessage = pp.getPinCodeMustContainOnlyNumbersValidation();
	        System.out.println("Actual message: '" + actualMessage + "'");
	        Assert.assertEquals(actualMessage, expectedMessage);
	        Log.info("Pin code must contain only numbers validation message verified successfully");
	    }
	    /**
	     * TC_010: Verifies successful creation of a phase with valid data.
	     * @throws InterruptedException if thread sleep is interrupted
	     * @throws IOException 
	     * @throws FileNotFoundException 
	     */
	    
	    @Test(priority =10,groups="Positive", description = "TC_010 Verify successful creation of a phase with valid data")
	    public void verifySuccessfulPhaseCreation() throws InterruptedException, FileNotFoundException, IOException {
	        PhasePage pp = new PhasePage(driver);
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
	        String expectedMessage = "Phase created successfully";
	        String actualMessage = pp.getPhaseCreationSuccessMessage();
	        System.out.println("Actual message: '" + actualMessage + "'");
	        Assert.assertEquals(actualMessage, expectedMessage, "Success message not displayed as expected.");
	        p.setProperty("RandomPhaseName", randomPhaseName);
	        
			
			try (FileOutputStream out = new FileOutputStream("./src//test//resources//config.properties")) {
			    p.store(out, "Saved Phase Name ");
			}
				        Log.info("Phase created successfully with name: " + randomPhaseName);
			  }

	    /**
	     * TC_011: Verifies the functionality of the Go back button.
	     * @throws InterruptedException if thread sleep is interrupted
	     */
	    
    @Test(priority=11,groups="Positive", description = "TC_011 Verify Go back button functionality")
    public void verifyBackButtonFunctionality() throws InterruptedException {
		PhasePage pp = new PhasePage(driver);
		pp.clickCreatePhaseButton();
		pp.clickGoBackButton();
		Thread.sleep(5000);
		String expectedTitle = "Phase List";
		String actualTitle = pp.getPhaseListHeaderText();
		System.out.println("Actual title after clicking back: '" + actualTitle + "'");
		Assert.assertEquals(actualTitle, expectedTitle, "Back button did not navigate to the Phase List page as expected.");
		Log.info("Back button functionality verified successfully, navigated to Phase List page");
	}
    
    
    /**
     * TC_012: Verifies that the created phase name is displayed in the list after creation.
     * Depends on verifySuccessfulPhaseCreation.
     * @throws InterruptedException if thread sleep is interrupted
     */
	  @Test(priority = 12,groups="Positive", description = "TC_012 Verify phase name is displayed in the list after creation", dependsOnMethods = "verifySuccessfulPhaseCreation")
	  public void verifyPhaseNameInListAfterCreation() throws InterruptedException {
	      PhasePage pp = new PhasePage(driver);	   
	      boolean isPhaseNameDisplayed = pp.isPhaseNameDisplayed(randomPhaseName);
	      Assert.assertTrue(isPhaseNameDisplayed, "Created phase name is not displayed in the list.");
	      Log.info("Phase name '" + randomPhaseName + "' is displayed in the list after creation");
	  }
	  /**
	     * TC_013: Verifies error message when creating a phase with an existing name.
	     * @throws InterruptedException if thread sleep is interrupted
	     */
	    
	    @Test(priority = 13,groups="Negative", description = "TC_013 Verify error when creating a phase with an existing name")
	    public void verifyPhaseWithSameNameExistsValidation() throws InterruptedException {
	        PhasePage pp = new PhasePage(driver);
	        pp.clickCreatePhaseButton();
	        pp.setPhaseName(randomPhaseName);
	        pp.setPhaseLocation(p.getProperty("phaselocation"));
	        pp.setPhasePrimaryStreetAddress(p.getProperty("phaseprimaryaddress"));
	        pp.setPhaseAdditionalStreetAddress(p.getProperty("phaseadditionalstreetaddress"));
	        pp.setPhaseCity(p.getProperty("phasecity"));
	        pp.setPhaseDistrict(p.getProperty("phasedisrict"));
	        pp.setPhaseState(p.getProperty("phasestate"));
	        pp.setPhasePinCode(p.getProperty("phasepincode"));
	        pp.clickCreatePhaseSubmitButton();
	        String expectedMessage = "Phase with same name exists";
	        String actualMessage = pp.getPhaseWithSameNameExistsMessage();
	        System.out.println("Actual message: '" + actualMessage + "'");
	        Assert.assertEquals(actualMessage, expectedMessage, "Duplicate phase name error message not displayed as expected.");
	        Log.info("Phase with same name exists validation message verified successfully");
	    }
	   

	

	    
	    
	

	
}