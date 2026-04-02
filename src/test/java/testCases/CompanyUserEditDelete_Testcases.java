package testCases;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CompanyUserEditDelete_Testcases {

	WebDriver driver;
	WebDriverWait wait;
	String timestamp = String.valueOf(System.currentTimeMillis());
	String randomUsername = "user" + timestamp;
	String randomEmail = "user" + timestamp + "@getnada.com";

	String updateTimestamp = String.valueOf(System.currentTimeMillis());
	String updatedEmail = "updated" + updateTimestamp + "@getnada.com";
	Random random = new Random();
	String randomContactNumber = String.valueOf(1000000000L + random.nextLong(9000000000L));


	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();               
		ChromeOptions co = new ChromeOptions();                
		co.addArguments("--incognito");                        
		driver = new ChromeDriver(co);                         
		driver.manage().window().maximize();                   
		driver.get("https://epassqa.pearlglobalsolutions.com/"); 
		wait = new WebDriverWait(driver, Duration.ofSeconds(80)); 
	}

	/**
	 * This test case verifies the login functionality for a company user with valid credentials.
	 * It checks if the user is able to log in successfully and if the welcome message is displayed correctly.
	 */
	@Test(priority = 1, description = "TC_001_Verify login of Company user with valid credentials")
	public void loginToEpass() {
		driver.findElement(By.xpath("//input[@placeholder='Enter username']")).sendKeys("parvathy");
		driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		String ActualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='main-title mb-0'and contains(text(),'Welcome')]"))).getText();
		String ExpectedTitle = "Welcome Parvathy";
		Assert.assertEquals(ActualTitle, ExpectedTitle, "Login failed or title mismatch.");
	}

	/**
	 * This test case verifies the creation of a company user profile.
	 * It checks if the user can create a new company user with valid details and if the success message is displayed.
	 */
	@Test(priority = 2, description = "TC_002_Verify create company user profile")
	public void createCompanyUserProfile() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Go To Company Users')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Create Company User')]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder, 'username for company user login')]"))).sendKeys(randomUsername);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter password for company user login']"))).sendKeys("Qwerty@123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Re-enter password for company user login']"))).sendKeys("Qwerty@123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter contact name for company user']"))).sendKeys("Test");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter email id for company user contact']"))).sendKeys(randomEmail);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter contact number for company user']"))).sendKeys(randomContactNumber);
		WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Create Company User')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", createButton);
		String expectedTitle = "Company Users List";
		String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(), 'Company Users List')]"))).getText();
		Assert.assertEquals(actualTitle, expectedTitle, "Company Users List page title mismatch after creation.");

	}

	/**	 
	 *  This test case verifies that clicking on the edit icon opens the company user profile for editing.
	 */

	@Test(priority = 3, description = "TC_003_Verify click on edit icon opens company user profile")
	public void editCompanyUserProfile() {
		System.out.println("Editing company user profile for: " + randomUsername);
		clickCompanyUserEditIcon(randomUsername);
		System.out.println("Clicked on edit icon for company user: " + randomUsername);
		String expectedTitle = "Edit Company User";
		System.out.println("Expected Title: " + expectedTitle);
		String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Edit Company User']"))).getText();
		System.out.println("Actual Title: " + actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle, "Company User edit page title mismatch.");
	}


	/**
	 * This test case verifies that the Contact Name field is mandatory in the Edit Company User Profile.
	 * It checks if the appropriate validation message is displayed when the field is left empty.
	 */
	@Test(priority = 4, description = "TC_004_Verify Contact Name is mandatory in Edit Company User Profile")
	public void verifyContactNameMandatoryInEditProfile() throws InterruptedException {

		// Clear the Contact Name field
		Thread.sleep(2000); // Wait for the page to load after clicking edit
		WebElement contactNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter contact name for company user']")));
		contactNameField.clear();
		contactNameField.sendKeys("      "); // Clear the field by sending an empty string
		Thread.sleep(2000); // Wait for the field to be cleared
		// Click the Save/Update button
		WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[text()='Edit Company User']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateButton);
		System.out.println("Clicked on Edit Company User button without entering Contact Name.");
		// Assert validation message is displayed
		String expectedValidation = "Contact name is required";
		WebElement validationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(text(),'Contact name is required')]")));
		String actualValidation = validationMsg.getText();
		System.out.println("Actual Validation Message: " + actualValidation);
		Assert.assertEquals(actualValidation, expectedValidation, "Validation message for Contact Name is missing or incorrect.");

	}


	/**	 * This test case verifies that the Email field is mandatory in the Edit Company User Profile.
	 * It checks if the appropriate validation message is displayed when the field is left empty.
	 * */

	@Test(priority = 5, description = "TC_005_Verify Email is mandatory in Edit Company User Profile")
	public void verifyEmailMandatoryInEditProfile() throws InterruptedException {
		WebElement contactNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter contact name for company user']")));
		contactNameField.clear();
		contactNameField.sendKeys("TestContact"); // Re-enter a valid contact name
		// Clear the Email field
		WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter email id for company user contact']")));
		emailField.clear();
		emailField.sendKeys("      "); // Clear the field by sending an empty string
		// Click the Save/Update button
		WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[text()='Edit Company User']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateButton);
		// Assert validation message is displayed
		String expectedValidation = "HR email id is required";
		WebElement validationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[text()='HR email id is required']")));
		String actualValidation = validationMsg.getText();
		Assert.assertEquals(actualValidation, expectedValidation, "Validation message for Email is missing or incorrect.");
	}


	/**
	 * This test case verifies that the Contact Number field is mandatory in the Edit Company User Profile.
	 * It checks if the appropriate validation message is displayed when the field is left empty.
	 */
	@Test(priority = 6, description = "TC_006_Verify Contact Number is mandatory in Edit Company User Profile")
	public void verifyContactNumberMandatoryInEditProfile() {
		WebElement contactNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter contact name for company user']")));
		contactNameField.clear();
		contactNameField.sendKeys("TestContact"); // Re-enter a valid contact name
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter contact name for company user']"))).sendKeys("TestContact");
		// Clear the Email field
		WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter email id for company user contact']")));
		emailField.clear();
		emailField.sendKeys(randomEmail); // Re-enter a valid email
		// Clear the Contact Number field
		WebElement contactNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter contact number for company user']")));
		contactNumberField.clear();
		contactNumberField.sendKeys("      "); // Clear the field by sending an empty string
		// Assert validation message is displayed
		String expectedValidation = "Contact number is required";
		WebElement validationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(text(),'Contact number is required')]")));
		String actualValidation = validationMsg.getText();
		Assert.assertEquals(actualValidation, expectedValidation, "Validation message for Contact Number is missing or incorrect.");
	}



	/**	 * This test case verifies that updating the Contact Name in the Edit Company User Profile works correctly.
	 * It checks if the user can update the Contact Name and if the success message is displayed.
	 * */


	@Test(priority = 7, description = "TC_007_Verify updating Contact Name in Edit Company User Profile")
	public void verifyUpdateContactNameInEditProfile() {
		WebElement contactNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter contact name for company user']")));
		contactNameField.clear();
		String updatedContactName = "UpdatedContact";
		contactNameField.sendKeys(updatedContactName);
		//update the Email field
		WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter email id for company user contact']")));
		emailField.clear();
		emailField.sendKeys(randomEmail);

		// Update the Contact Number field
		WebElement contactNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter contact number for company user']")));
		contactNumberField.clear();
		// Example updated contact number
		contactNumberField.sendKeys(randomContactNumber);

		// Click the Update button
		WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[text()='Edit Company User']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateButton);

		// Assert success toast message is displayed
		String expectedToast = "Company user updated successfully";
		WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(text(),'Company user updated successfully')]")));
		String actualToast = toastMsg.getText();
		Assert.assertEquals(actualToast, expectedToast, "Update success message is missing or incorrect.");
	}


	/**	 * This test case verifies that updating the Email ID in the Edit Company User Profile works correctly.
	 * * It checks if the user can update the Email ID and if the success message is displayed.
	 */
	@Test(priority = 8, description = "TC_008_Verify updating Email ID in Edit Company User Profile")
	public void verifyUpdateEmailInEditProfile() {
		// Go to edit profile for the created user
		clickCompanyUserEditIcon(randomUsername);

		// Update the Email field
		//updatedEmail = "updated" + System.currentTimeMillis() + "@getnada.com";
		WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter email id for company user contact']")));
		emailField.clear();
		emailField.sendKeys(updatedEmail);

		// Optionally update other mandatory fields if required
		WebElement contactNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter contact name for company user']")));
		contactNameField.clear();
		contactNameField.sendKeys("UpdatedContact");

		WebElement contactNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter contact number for company user']")));
		contactNumberField.clear();
		contactNumberField.sendKeys(randomContactNumber); // Reusing the random contact number

		// Click the Update button
		WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[text()='Edit Company User']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateButton);

		// Assert success toast message is displayed
		String expectedToast = "Company user updated successfully";
		WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(text(),'Company user updated successfully')]")));
		String actualToast = toastMsg.getText();
		Assert.assertEquals(actualToast, expectedToast, "Update success message is missing or incorrect.");
	}

	/**	 * This test case verifies that updating the Contact Number in the Edit Company User Profile works correctly.
	 * It checks if the user can update the Contact Number and if the success message is displayed.
	 */

	@Test(priority = 9, description = "TC_009_Verify updating Contact Number in Edit Company User Profile")
	public void verifyUpdateContactNumberInEditProfile() throws InterruptedException {
		// Go to edit profile for the created user
		clickCompanyUserEditIcon(randomUsername);

		// Update the Contact Number field
		String updatedContactNumber = String.valueOf(9000000000L + random.nextInt(999999999));
		WebElement contactNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter contact number for company user']")));
		contactNumberField.clear();
		contactNumberField.sendKeys(updatedContactNumber);

		// update the contact name field
		WebElement contactNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter contact name for company user']")));
		contactNameField.clear();
		contactNameField.sendKeys("UpdatedContact");
		Thread.sleep(2000); // Wait for the field to be updated
		WebElement emailField1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//input[@placeholder='Enter email id for company user contact']")));
		emailField1.clear();
		emailField1.sendKeys(updatedEmail);

		// Click the Update button
		WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[text()='Edit Company User']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateButton);

		// Assert success toast message is displayed
		String expectedToast = "Company user updated successfully";
		WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(text(),'Company user updated successfully')]")));
		String actualToast = toastMsg.getText();
		Assert.assertEquals(actualToast, expectedToast, "Update success message is missing or incorrect.");
	}



	/**	 * This test case verifies the deletion of a company user profile.
	 * It checks if the user can delete a company user and if the success message is displayed.
	 */
	@Test(priority = 10, description = "TC_010_Verify delete company user profile")
	public void deleteCompanyUserProfile() throws InterruptedException {
		System.out.println("Deleting company user profile for: " + updatedEmail);
		clickCompanyUserDeleteIconByEmail(updatedEmail);
		System.out.println("Clicked on delete icon for company user: " + updatedEmail);

		Thread.sleep(3000); // Wait for the confirmation dialog to appear
		WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary' and text()='Yes']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmButton);
		String expectedToast = "Company user deleted successfully";
		WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(text(),'Company user deleted successfully')]")));
		String actualToast = toastMsg.getText();
		Assert.assertEquals(actualToast, expectedToast, "Delete success message is missing or incorrect.");
	}
	//Edit icon click method to handle pagination and find the company user card

	public void clickCompanyUserEditIcon(String companyUserName) {
		boolean companyUserFound = false;

		while (!companyUserFound) {
			// Wait for company user cards to be present
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'card') and .//h5]")));
			List<WebElement> companyUserCards = driver.findElements(By.xpath("//div[contains(@class,'card') and .//h5]"));

			for (WebElement card : companyUserCards) {
				String cardText = card.getText().toLowerCase();
				if (cardText.contains(companyUserName.toLowerCase())) {
					WebElement editIcon = card.findElement(By.xpath(".//a[contains(@href,'create-company-user')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editIcon);
					wait.until(ExpectedConditions.elementToBeClickable(editIcon)).click();
					companyUserFound = true;
					break;
				}
			}

			if (!companyUserFound) {
				List<WebElement> nextButtonList = driver.findElements(
						By.xpath("//ul[contains(@class,'pagination')]//li[last()]//a[contains(@class,'page-link')]")
						);

				if (nextButtonList.isEmpty() || !nextButtonList.get(0).isEnabled()) {
					throw new NoSuchElementException("Company user with name '" + companyUserName + "' not found.");
				}

				WebElement nextButton = nextButtonList.get(0);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);

				// Wait until the previous card becomes stale (page updated)
				wait.until(ExpectedConditions.stalenessOf(companyUserCards.get(0)));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'card') and .//h5]")));
			}
		}
	}



	//delete icon click method to handle pagination and find the company user card by email

	public void clickCompanyUserDeleteIconByEmail(String emailId) {
		boolean userFound = false;

		while (!userFound) {

			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//p[contains(@class,'text-wrap') and contains(text(),'@')]")));

			List<WebElement> emailElements = driver.findElements(
					By.xpath("//p[contains(@class,'text-wrap') and contains(text(),'@')]"));

			for (WebElement emailElement : emailElements) {
				String actualEmail = emailElement.getText().replaceAll("\\s+", "");
				String expectedEmail = emailId.replaceAll("\\s+", "");

				System.out.println("Checking email: " + actualEmail + " == " + expectedEmail);

				if (actualEmail.equalsIgnoreCase(expectedEmail)) {
					WebElement card = emailElement.findElement(
							By.xpath("./ancestor::div[contains(@class,'border-bottom')]"));

					WebElement deleteIcon = card.findElement(
							By.xpath(".//a[contains(@href,'user-list')]"));

					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
					wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();

					System.out.println("Clicked on delete icon for company user: " + emailId);
					userFound = true;
					break;
				}
			}

			if (!userFound) {
				List<WebElement> nextButtons = driver.findElements(
						By.xpath("//ul[contains(@class,'pagination')]//li[last()]//a[contains(@class,'page-link')]"));

				if (nextButtons.isEmpty() || !nextButtons.get(0).isEnabled()) {
					throw new NoSuchElementException("Company user with email '" + emailId + "' not found.");
				}

				WebElement nextButton = nextButtons.get(0);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);


				wait.until(ExpectedConditions.stalenessOf(emailElements.get(0)));
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
						By.xpath("//p[contains(@class,'text-wrap') and contains(text(),'@')]")));
			}
		}
	}


	public void takeScreenshot(String testName){
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String filePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + System.currentTimeMillis() + ".png";
			FileUtils.copyFile(srcFile, new File(filePath));
			System.out.println("Screenshot taken: " + filePath);
		} catch (Exception e) {
			System.out.println("Failed to take screenshot: " + e.getMessage());
		}
	}	


	@AfterMethod
	public void afterEachTest(ITestResult result) {
		//takeScreenshot(result.getMethod().getMethodName()); // Take a screenshot after each test method

		if (result.getStatus() == ITestResult.FAILURE) {
			takeScreenshot(result.getMethod().getMethodName());  // Take a screenshot if the test fails
		}
	}


	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit(); // Close the browser after test execution
		}
	}
}

