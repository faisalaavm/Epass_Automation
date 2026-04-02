package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateGatePage extends BasePage{
	
	// Constructor
		public CreateGatePage(WebDriver driver) {
			super(driver);
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		}

		// Locating all web elements in the Create Gate page
		
		@FindBy(xpath="//a[normalize-space()='View all phases']") WebElement viewAllPhaseLink;
		@FindBy(xpath="(//div[@class='d-flex flex-column gap-0 card-wrap'])[1]//h5") WebElement firstPhaseInTheList;
		@FindBy(xpath="(//div[@class='d-flex flex-column gap-0 card-wrap'])[1]//following-sibling::div//a[1]//*[name()='svg']") 
		WebElement eyeIconForfirstPhaseInTheList;
		@FindBy(xpath="//a[normalize-space()='View all gates']") WebElement viewAllGatesLink;
		@FindBy(xpath="//span[normalize-space()='Create Gate']") WebElement createGateButton;
		@FindBy(xpath="//h4[@class='main-title mb-0' and normalize-space()='Create Gate']") WebElement createGateLabel;
		
		//Locators for Create Gate form fields
		@FindBy(xpath="//input[@placeholder='Enter reference id for the gate']") WebElement gateReferenceId;	
		@FindBy(xpath="(//select[contains(@aria-label,'Default select example')])[1]") WebElement ITparkDropdown;
		@FindBy(xpath="(//select[contains(@aria-label,'Default select example')])[2]") WebElement phaseDropdown;
		@FindBy(xpath="//input[contains(@placeholder,'Enter username for gate login')]") WebElement gateUsername;
		@FindBy(xpath="//input[@placeholder='Enter password for gate login']") WebElement gatePassword;
		@FindBy(xpath="//input[@placeholder='Re-enter the password for gate login']") WebElement gateConfirmPassword;
		@FindBy(xpath="//input[@placeholder='Enter contact name for the gate']") WebElement gateContactName;
		@FindBy(xpath="//input[@placeholder='Enter email id of the gate contact']") WebElement gateContactEmail;
		@FindBy(xpath="//input[@placeholder='Enter contact number for park admin']") WebElement gateContactNumber;
		
		//Locators for Create Gate form button
		@FindBy(xpath="//button[@type='submit' and contains(@class, 'btn-sign') and normalize-space()='Create Gate']") 
		WebElement submiteGateButton;
		
		//Locators for Validation messages for Required fields
		@FindBy(xpath="//div[@class='text-danger' and normalize-space()='Gate name/number is required']") 
		WebElement gateNameRequiredValidation;
		@FindBy(xpath="//div[@class='text-danger' and normalize-space()='Gate username is required']") 
		WebElement gateUserNameRequiredValidation;	
		@FindBy(xpath="//div[@class='text-danger mb-3' and normalize-space()='Password is required']") 
		WebElement gatePasswordRequiredValidation;
		@FindBy(xpath="//div[normalize-space()='Contact name is required']") WebElement gateContactNameRequiredValidation;
		@FindBy(xpath="//div[normalize-space()='Email id is required']") WebElement gateEmailIdRequiredValidation;
		@FindBy(xpath="//div[normalize-space()='Contact number is required']") WebElement gateContactNumberRequiredValidation;	
		
		
		//Locators for Other field validation for all the input fields
		@FindBy(xpath="//div[@class='text-danger' and normalize-space()='Gate name/number should not start with a special character']") 
		WebElement gateNameSpecialCharValidation;	
		@FindBy(xpath="//div[@class='text-danger' and normalize-space()='Gate username must contain only alphanumeric characters']") 
		WebElement gateUserNameSpecialCharacterValidation;	
		@FindBy(xpath="//div[@class='text-danger mb-3' and normalize-space()='Password length must be greater than 8 characters']") 
		WebElement gatePasswdLessthanEight;
		@FindBy(xpath="//div[@class='text-danger mb-3' and normalize-space()='Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)']") 
		WebElement gateInvalidPasswordValidation;
		@FindBy(xpath="//div[@class='text-danger mb-3' and normalize-space()='Passwords do not match']") 
		WebElement gatePasswdNotMatchingvalidation;
		@FindBy(xpath="//div[@class='text-danger' and normalize-space()='Contact name must start with a letter and contain only alphanumeric characters']") 
		WebElement specialCharInGateContactname;
		@FindBy(xpath="//div[@class='text-danger' and normalize-space()='Please enter a valid email id']") 
		WebElement invalidGateContactEmail;
		@FindBy(xpath="//div[@class='text-danger' and normalize-space()='Contact number must contain only numeric characters']")
		WebElement nonnumericGateContactNumber;
		@FindBy(xpath="//div[@class='text-danger' and normalize-space()='Contact number must contain at least 10 numeric characters']")
		WebElement gateContactNumberlessthanTen;
		@FindBy(xpath="//div[@class='text-danger' and normalize-space()='Continuous sequence of 10 same numbers is not allowed']") 
		WebElement gateContactNumberSequence;
		
		//Locators for Success and duplication error messages
		@FindBy(xpath="//div[normalize-space()='Successfully created Gate, Gate User and credentials sent to the email.']") 
		WebElement gateCreationSuccessMessage;
		@FindBy(xpath="//div[contains(text(),'Gate with same name exists in this phase')]") WebElement gateWithSameNameExists;
		@FindBy(xpath="//div[contains(text(),'User with same Username exists')]") WebElement gateUserWithSameUsernameExists;
		@FindBy(xpath="//div[contains(text(),'User with same Email exists')]") WebElement gateuserWithSameEmailExists;
		@FindBy(xpath = "//button[@aria-label='close']//*[name()='svg']")
		WebElement closeButton;
		
		public void closeButton() {
			 //	        wait.until(ExpectedConditions.elementToBeClickable(closeButton));
			 	        closeButton.click();
			 	    }
		// Function for clicking links or buttons and interacting with elements
		public void clickViewAllPhasesLink() {
			wait.until(ExpectedConditions.elementToBeClickable(viewAllPhaseLink));
			viewAllPhaseLink.click();
		}
		public String getFirstPhaseName() {
			wait.until(ExpectedConditions.visibilityOf(firstPhaseInTheList));
			return  firstPhaseInTheList.getText();
		}
		public void clickEyeIconForFirstPhaseInTheList() {
			wait.until(ExpectedConditions.elementToBeClickable(eyeIconForfirstPhaseInTheList));
			eyeIconForfirstPhaseInTheList.click();
		}
		public void clickViewAllGatesLink() {
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

		    // Wait until the element is clickable
		    WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(viewAllGatesLink));

		    // Scroll into view (important in headless mode to avoid overlap)
		    js.executeScript("arguments[0].scrollIntoView(true);", ele);

		    try {
		        ele.click();
		    } catch (ElementClickInterceptedException e) {
		        // Fallback: click via JavaScript if something is blocking the element
		        js.executeScript("arguments[0].click();", ele);
		    }
		}
		public void clickCreateGateButton() {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		    JavascriptExecutor js = (JavascriptExecutor) driver;

		    WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(createGateButton));

		    // Scroll into view (important for headless mode / sticky headers)
		    js.executeScript("arguments[0].scrollIntoView(true);", ele);

		    try {
		        ele.click();
		    } catch (ElementClickInterceptedException e) {
		        // If overlapped, use JS click as a backup
		        js.executeScript("arguments[0].click();", ele);
		    }
		}
		public String getCreateGateLabel() {
			wait.until(ExpectedConditions.visibilityOf(createGateLabel));
			return createGateLabel.getText();
		}
		public void clickSubmitGateButton() {
			wait.until(ExpectedConditions.elementToBeClickable(submiteGateButton));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submiteGateButton);
			submiteGateButton.click();
		}
		
		
		//Functions for various input fields and dropdowns
		public void setGateReferenceId(String referenceId) {
			wait.until(ExpectedConditions.visibilityOf(gateReferenceId)).clear(); // Clear the field before entering new value
			gateReferenceId.sendKeys(referenceId);
		}
		
		public String getDropdownValueITPark() {
			
			wait.until(driver -> {
		        Select parkDropdown = new Select(ITparkDropdown);
		        return !parkDropdown.getFirstSelectedOption().getText().equals("Select IT Park");
		    });		   //This function waits until the selected option in the ITparkDropdown is not "Select IT Park"
			String selectedOption = new Select(ITparkDropdown).getFirstSelectedOption().getText();  //Once the value changes, it retrieves and returns the selected option's text.
			return selectedOption;
		}
		
		public String getDropdownValuePhase() {
			wait.until(driver -> {
		        Select parkDropdown = new Select(phaseDropdown);
		        return !parkDropdown.getFirstSelectedOption().getText().equals("Select Phase");
		    });		
			String selectedOption = new Select(phaseDropdown).getFirstSelectedOption().getText();
			return selectedOption;
		}
		public void setGateUsername(String username) {
			wait.until(ExpectedConditions.visibilityOf(gateUsername)).clear(); // Clear the field before entering new value
			gateUsername.sendKeys(username);
		}
		public void setGatePassword(String passwd) {
			wait.until(ExpectedConditions.visibilityOf(gatePassword)).clear(); // Clear the field before entering new value
			gatePassword.sendKeys(passwd);
		}
		public void setGateConfirmPassword(String confirmPassword) {
			wait.until(ExpectedConditions.visibilityOf(gateConfirmPassword)).clear(); // Clear the field before entering new value
			gateConfirmPassword.sendKeys(confirmPassword);
		}
		public void setGateContactName(String contactName) {
			wait.until(ExpectedConditions.visibilityOf(gateContactName)).clear(); // Clear the field before entering new value
			gateContactName.sendKeys(contactName);
		}
		public void setGateContactEmail(String contactEmail) {
			wait.until(ExpectedConditions.visibilityOf(gateContactEmail)).clear(); // Clear the field before entering new value
			gateContactEmail.sendKeys(contactEmail);
		}
		public void setGateContactNumber(String contactNumber) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Wait for the element to be visible
			wait.until(ExpectedConditions.visibilityOf(gateContactNumber)).clear(); // Clear the field before entering new value
			gateContactNumber.sendKeys(contactNumber);
		}
		
		
		// Functions for the required validations messages for different input fields
		
		public String getGateNameRequiredValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gateNameRequiredValidation));
		    return gateNameRequiredValidation.getText();
		}
		public String getGateUserNameRequiredValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gateUserNameRequiredValidation));
		    return gateUserNameRequiredValidation.getText();
		}
		public String getGatePasswordRequiredValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gatePasswordRequiredValidation));
		    return gatePasswordRequiredValidation.getText();
		}
		public String getGateContactNameRequiredValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gateContactNameRequiredValidation));
		    return gateContactNameRequiredValidation.getText();
		}
		public String getGateEmailIdRequiredValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gateEmailIdRequiredValidation));
		    return gateEmailIdRequiredValidation.getText();
		}
		public String getGateContactNumberRequiredValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gateContactNumberRequiredValidation));
		    return gateContactNumberRequiredValidation.getText();
		}
		
		
		// Function for the validations for different input fields
		
		public String getGateNameSpecialCharValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gateNameSpecialCharValidation));
		    return gateNameSpecialCharValidation.getText();
		}
		
		public String getGateUserNameSpecialCharacterValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gateUserNameSpecialCharacterValidation));
		    return gateUserNameSpecialCharacterValidation.getText();
		}
		
		public String getGatePasswdLessthanEightValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gatePasswdLessthanEight));
		    return gatePasswdLessthanEight.getText();
		}
		public String getGateInvalidPasswordValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gateInvalidPasswordValidation));
		    return gateInvalidPasswordValidation.getText();
		}
		public String getGatePasswdNotMatchingValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gatePasswdNotMatchingvalidation));
		    return gatePasswdNotMatchingvalidation.getText();
		}
		public String getSpecialCharInGateContactName() {
		    wait.until(ExpectedConditions.visibilityOf(specialCharInGateContactname));
		    return specialCharInGateContactname.getText();
		}
		public String getInvalidGateContactEmailValidation() {
		    wait.until(ExpectedConditions.visibilityOf(invalidGateContactEmail));
		    return invalidGateContactEmail.getText();
		}
		public String getNonNumericGateContactNumberValidation() {
		    wait.until(ExpectedConditions.visibilityOf(nonnumericGateContactNumber));
		    return nonnumericGateContactNumber.getText();
		}
		public String getGateContactNumberLessThanTenValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gateContactNumberlessthanTen));
		    return gateContactNumberlessthanTen.getText();
		}
		public String getGateContactNumberSequenceValidation() {
		    wait.until(ExpectedConditions.visibilityOf(gateContactNumberSequence));
		    return gateContactNumberSequence.getText();
		}
		
		//Function to get the success validation
		public String getGateCreationSuccessMessage() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// Update the locator as per your actual success message element
			By successMsgLocator = By.xpath("//div[normalize-space()='Successfully created Gate, Gate User and credentials sent to the email.']");
			WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsgLocator));
			return successMsg.getText();
		    
		}
		
		// Functions to get the error messages for duplication of gate name , gate user and gate user email
		public String getGateWithSameNameExistsMessage() {
			wait.until(ExpectedConditions.visibilityOf(gateWithSameNameExists));
			return gateWithSameNameExists.getText();
		}
		
		public String getGateUserWithSameEmailExistsMessage() {
			wait.until(ExpectedConditions.visibilityOf(gateuserWithSameEmailExists));
			return gateuserWithSameEmailExists.getText();
		}
		public String getGateUserWithSameUsernameExistsMessage() {
			wait.until(ExpectedConditions.visibilityOf(gateUserWithSameUsernameExists));
			return gateUserWithSameUsernameExists.getText();
		}
	}
