package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BuildingCreation;
import pageObjects.LoginPage;
import pageObjects.PhasePage;
import pageObjects.PhaseViewPage;
import testBase.BaseClass;

public class PhaseViewPage_Testcases extends BaseClass {
	public static String createdRandomPhaseName;
	public static String secondPhaseName;
	public static String updatedPhaseName;

	/**
	 * TC_001: Verifies successful login with valid credentials.
	 * @throws InterruptedException if thread sleep is interrupted
	 */

	@Test(priority=1,groups="Positive",description="TC_001_Verify clicking on the eye icon opens the Phase View page")
	public void verifyLogin() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setuserName(p.getProperty("username"));
		lp.setpassWord(p.getProperty("password"));
		//lp.setuserName("superadmin"); // Use valid credentials
		//lp.setpassWord("Admin@1234");
		lp.setSubmit();
		
		PhasePage pp = new PhasePage(driver);
		pp.clickGoToParkButton();
		pp.clickParkEyeIcon();
		pp.clickViewAllPhasesLink();
		pp.clickCreatePhaseButton();
		createdRandomPhaseName =pp.randomString();
		pp.setPhaseName(createdRandomPhaseName);
		pp.setPhaseLocation(p.getProperty("phaselocation"));
		pp.setPhasePrimaryStreetAddress(p.getProperty("phaseprimaryaddress"));
		pp.setPhaseAdditionalStreetAddress(p.getProperty("phaseadditionalstreetaddress"));
		pp.setPhaseCity(p.getProperty("phasecity"));
		pp.setPhaseDistrict(p.getProperty("phasedisrict"));
		pp.setPhaseState(p.getProperty("phasestate"));
		pp.setPhasePinCode(p.getProperty("phasepincode"));
		pp.clickCreatePhaseSubmitButton();
		System.out.println("Creating first phase with name: " +createdRandomPhaseName );
		String expectedMessage = "Phase created successfully";
		String actualMessage = pp.getPhaseCreationSuccessMessage();
		System.out.println("Actual message: '" + actualMessage + "'");
		Assert.assertEquals(actualMessage, expectedMessage, "Success message not displayed as expected.");
		secondPhaseName = "Automation Phase " + System.currentTimeMillis(); // Name with system time
		System.out.println("Creating second phase with name: " + secondPhaseName);
		pp.clickCreatePhaseButton();
		pp.setPhaseName(secondPhaseName);
		pp.setPhaseLocation(p.getProperty("phaselocation"));
		pp.setPhasePrimaryStreetAddress(p.getProperty("phaseprimaryaddress"));
		pp.setPhaseAdditionalStreetAddress(p.getProperty("phaseadditionalstreetaddress"));
		pp.setPhaseCity(p.getProperty("phasecity"));
		pp.setPhaseDistrict(p.getProperty("phasedisrict"));
		pp.setPhaseState(p.getProperty("phasestate"));
		pp.setPhasePinCode(p.getProperty("phasepincode"));
		pp.clickCreatePhaseSubmitButton();
		String expectedMessage2 = "Phase created successfully";
		String actualMessage2 = pp.getPhaseCreationSuccessMessage();
		System.out.println("Actual message for Phase 2: '" + actualMessage2 + "'");
		Assert.assertEquals(actualMessage2, expectedMessage2, "Success message not displayed as expected for Phase 2.");
		System.out.println("Clicked eye icon for phase: " + createdRandomPhaseName);

		// Check if name is correctly passed
		if (createdRandomPhaseName == null || createdRandomPhaseName.isEmpty()) {
			throw new IllegalStateException("randomPhaseName is null or empty. Ensure the phase was created successfully before this test.");
		}
		// Act: Click on the eye icon for the matching phase
		PhaseViewPage po = new PhaseViewPage(driver);
		po.clickPhaseEyeIcon(createdRandomPhaseName);
		// Assert: Check that the view page title or header is as expected
		String expectedTitle = createdRandomPhaseName;
		System.out.println("Expected view page title: '" + expectedTitle + "'");
		// Replace with actual title on view page if different
		String actualTitle = po.getMainTitleText(); 
		System.out.println("Actual view page title: '" + actualTitle + "'");
		Assert.assertEquals(actualTitle.toLowerCase(), expectedTitle.toLowerCase(), "Phase view page did not open correctly.");
	}

	

	@Test(priority = 2,groups="Positive", description = "TC_002 Verify that the phase view page displays the correct phase details")
	public void verifyPhaseViewPageDisplaysCorrectDetails() throws InterruptedException {
		// Navigate to the phase view page for the created phase
		PhaseViewPage po = new PhaseViewPage(driver);
		// Get the displayed address from the view page
		String actualAddress = po.getAddressText();
		System.out.println("Actual address displayed on phase view page: '" + actualAddress + "'");

		// Construct the expected address from the test data
		String expectedAddress = String.format("%s %s %s %s %s %s",

				p.getProperty("phaseprimaryaddress"),
				p.getProperty("phaseadditionalstreetaddress"),
				p.getProperty("phasecity"),
				p.getProperty("phasedisrict"),
				p.getProperty("phasestate"), p.getProperty("phasepincode")
				).trim();
		System.out.println("Expected address: '" + expectedAddress + "'");
		// Assert that the displayed address matches the expected address
		Assert.assertEquals(actualAddress, expectedAddress, "Phase address details do not match.");
	}



	/**
	 * TC_005: Verifies that clicking the edit icon opens the Phase Edit page for the correct phase.
	 * @throws InterruptedException if thread sleep is interrupted
	 */
	@Test(priority = 3,groups="Positive", description = "TC_003 Verify clicking the edit icon opens the Phase Edit page for the correct phase")
	public void verifyPhaseEditIconOpensEditPage() throws InterruptedException {
		PhasePage pp = new PhasePage(driver);
		pp.clickGoBackButton();
		// Arrange: Ensure a phase is created and its name is available
		if (createdRandomPhaseName == null || createdRandomPhaseName.isEmpty()) {
			throw new IllegalStateException("createdRandomPhaseName is null or empty. Ensure the phase was created successfully before this test.");
		}

		// Act: Click the edit icon for the created phase
		PhaseViewPage po = new PhaseViewPage(driver);
		po.clickPhaseEditIcon(createdRandomPhaseName);

		// Assert: Check that the edit page title or header matches the phase name
		String expectedTitle = "Edit Phase"; // Adjust if the edit page shows a different title
		System.out.println("Expected edit page title: '" + expectedTitle + "'");
		String actualTitle = po.getEditPageTitleText(); // Implement this method to fetch the edit page title
		System.out.println("Actual edit page title: '" + actualTitle + "'");

		Assert.assertEquals(actualTitle, expectedTitle, "Phase edit page did not open for the correct phase.");
	}

	/**
	 * TC_006: Verifies that the phase name field is mandatory while editing a phase.
	 * @throws InterruptedException if thread sleep is interrupted
	 */

	@Test(priority = 4,groups="Negative", description = "TC_004 Verify Validation check for phase name while editing")
	public void verifyMandatoryCheckForPhaseNameWhileEditing() throws InterruptedException {
		PhasePage pp = new PhasePage(driver);
		pp.clickGoBackButton();
		// Go to edit page for the created phase
		PhaseViewPage po = new PhaseViewPage(driver);
		po.clickPhaseEditIcon(createdRandomPhaseName);
		Thread.sleep(5000); // Wait for the edit page to load
		// Clear the phase name field (implement clearPhaseName if not present)
		pp.setPhaseName("                "); // Assuming setPhaseName can clear the field by passing an empty string
		System.out.println("Cleared phase name field for editing.");
		// Click Save button (ensure this method is implemented in PhaseViewPage)
		po.clickEditPhaseSaveButton();
		// Assert: Check for mandatory error message
		String expectedError = "Phase name is required";
		String actualError = pp.blankPhaseNameValidation(); // Implement this to fetch the error message
		Assert.assertEquals(actualError, expectedError, "Mandatory error message not displayed for empty phase name.");
	
		pp.setPhaseName(p.getProperty("phasenamestartlettervalidation"));
		pp.setPhaseLocation(p.getProperty("phaselocation"));
		pp.setPhasePrimaryStreetAddress(p.getProperty("phaseprimaryaddress"));
		pp.setPhaseAdditionalStreetAddress(p.getProperty("phaseadditionalstreetaddress"));
		pp.setPhaseCity(p.getProperty("phasecity"));
		pp.setPhaseDistrict(p.getProperty("phasedisrict"));
		pp.setPhaseState(p.getProperty("phasestate"));
		pp.setPhasePinCode(p.getProperty("phasepincode"));
		// Click Save button again
		po.clickEditPhaseSaveButton();
		String expectedError1 = "Phase name must start with a letter and contain only alphanumeric characters";
		String actualError1 = pp.getPhaseNameMustStartWithLetterValidation(); // Implement this method to fetch the error message
		Assert.assertEquals(actualError1, expectedError1, "Validation error for phase name not starting with a letter is not displayed.");
		pp.setPhaseName(p.getProperty("PHasename"));
		pp.setPhaseLocation(p.getProperty("phaselocation"));
		pp.setPhasePrimaryStreetAddress(p.getProperty("phaseprimaryaddress"));
		pp.setPhaseAdditionalStreetAddress(p.getProperty("phaseadditionalstreetaddress"));
		pp.setPhaseCity(p.getProperty("phasecity"));
		pp.setPhaseDistrict(p.getProperty("phasedisrict"));
		pp.setPhaseState(p.getProperty("phasestate"));
		pp.setPhasePinCode(p.getProperty("invalidpincode")); // Invalid pin code with letters
		//scroll to the bottom of the page
		BuildingCreation BC=new BuildingCreation(driver);
		BC.scrollToBottom();
		po.clickEditPhaseSaveButton();
		String expectedError2 = "Pin code must contain only numbers";;
		String actualError2 = pp.getPinCodeMustContainOnlyNumbersValidation(); // Implement this method to fetch the error message
		Assert.assertEquals(actualError2, expectedError2, "Validation error for pin code not containing only numbers is not displayed.");
		System.out.println("Validation error for pin code not containing only numbers is displayed as expected.");
	}


	/**
	 * TC_009: Verifies that an error is displayed when editing a phase with an existing name.
	 * @throws InterruptedException if thread sleep is interrupted
	 */

	@Test(priority = 5,groups="Negative", description = "TC_005 Verify error when editing a phase with an existing name")
	public void verifyErrorOnEditingPhaseWithExistingName() throws InterruptedException {
		PhasePage pp = new PhasePage(driver);
		PhaseViewPage po = new PhaseViewPage(driver);
		po.scrollToTop();
		pp.setPhaseName(secondPhaseName);
		System.out.println("Editing phase with existing name: " + secondPhaseName);
		pp.setPhasePinCode(p.getProperty("phasepincode"));
		po.clickEditPhaseSaveButton(); 
		String expectedError = "Phase with same name exists";
		String actualError = pp.getPhaseWithSameNameExistsMessage();
		System.out.println("Actual error message: '" + actualError + "'");
		Assert.assertEquals(actualError, expectedError, "Error message not displayed as expected when editing with existing name.");
	
	}



	/**
	 * TC_010: Verifies successful editing of phase details.
	 * @throws InterruptedException if thread sleep is interrupted
	 */

	@Test(priority = 6,groups="Positive", description = "TC_06 Verify successful edit of phase details")
	public void verifySuccessfulEditOfPhaseDetails() throws InterruptedException {
		PhasePage pp = new PhasePage(driver);
		PhaseViewPage po = new PhaseViewPage(driver);
		// Generate new phase name for update
		updatedPhaseName = createdRandomPhaseName + "_Updated";
		System.out.println("Editing phase. New name: " + updatedPhaseName);

		// Set updated data
		pp.setPhaseName(updatedPhaseName);
		pp.setPhaseLocation(p.getProperty("phaselocation"));
		pp.setPhasePrimaryStreetAddress(p.getProperty("phaseprimaryaddress"));
		pp.setPhaseAdditionalStreetAddress(p.getProperty("phaseadditionalstreetaddress"));
		pp.setPhaseCity(p.getProperty("phasecity"));
		pp.setPhaseDistrict(p.getProperty("phasedisrict"));
		pp.setPhaseState(p.getProperty("phasestate"));
		pp.setPhasePinCode(p.getProperty("phasepincode"));
		Thread.sleep(3000); // Wait for any UI updates
		// Save the updated details
		po.clickEditPhaseSaveButton();
		System.out.println("Clicked Save button after editing phase details.");

		// Validate success toast
		String expectedMessage = "Phase updated successfully";
		String actualMessage = po.getPhaseUpdatedSuccessMessage(); // Reuse this method for toast
		System.out.println("Actual success message: '" + actualMessage + "'");
		Assert.assertEquals(actualMessage, expectedMessage, "Success message not displayed after editing phase.");

	}


	/**
	 * TC_011: Verifies that the updated phase name is displayed on the view page.
	 * @throws InterruptedException if thread sleep is interrupted
	 */
	@Test(priority = 7,groups="Positive", description = "TC_07 Verify that the updated phase name is displayed on the view page")
	public void verifyUpdatedPhaseNameIsDisplayedOnViewPage() throws InterruptedException {
		PhaseViewPage po = new PhaseViewPage(driver);
		po.clickPhaseEyeIcon(updatedPhaseName); 
		// Act: Get the title text on the view page
		String actualTitle = po.getMainTitleText();
		System.out.println("Title after edit: " + actualTitle);

		// Assert: Title should match the updated phase name
		Assert.assertEquals(actualTitle, updatedPhaseName, "Updated phase name not displayed in view page.");
	}

	@Test(priority = 8,groups="Positive", description = "TC_008 Verify canceling phase deletion does not delete the phase")
	public void verifyCancelPhaseDeletion() throws InterruptedException {
		PhasePage pp = new PhasePage(driver);
		PhaseViewPage po = new PhaseViewPage(driver);
		// Go back to phase list
		pp.clickGoBackButton();
		// Attempt to delete the updated phase
		po.clickPhaseDeleteIcon(updatedPhaseName);
		System.out.println("Clicked delete icon for phase: " + updatedPhaseName);
		// Click "No" to cancel deletion
		po.cancelDelete();
		System.out.println("Cancelled deletion of phase: " + updatedPhaseName);

		// Assert: The phase should still be present 
		boolean isPhasePresent = pp.isPhaseNameDisplayed(updatedPhaseName); 
		Assert.assertTrue(isPhasePresent, "Phase should still be present after canceling deletion.");
	}


	/**
	 * TC_012: Verifies successful deletion of a phase.
	 * @throws InterruptedException if thread sleep is interrupted
	 */
	@Test(priority = 9,groups="Positive", description = "TC_009 Verify successful deletion of a phase")
	public void verifySuccessfulPhaseDeletion() throws InterruptedException {
		PhasePage pp = new PhasePage(driver);
		PhaseViewPage po = new PhaseViewPage(driver);
		// Delete the updated phase
		po.clickPhaseDeleteIcon(updatedPhaseName);
		System.out.println("Clicked delete icon for phase: " + updatedPhaseName);
		po.confirmDelete(); //  this method handles the confirmation dialog 
		System.out.println("Confirmed deletion of phase: " + updatedPhaseName);
		// Validate success message
		String expectedMessage = "Phase deleted successfully";
		System.out.println("Expected delete success message: '" + expectedMessage + "'");
		String actualMessage = po.getPhaseDeletedSuccessMessage(); // Implement this method
		System.out.println("Actual delete success message: '" + actualMessage + "'");
		Assert.assertEquals(actualMessage, expectedMessage, "Success message not displayed after deleting phase.");
	}



}

