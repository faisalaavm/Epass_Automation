package pageObjects;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhaseAdminCreationPage extends BasePage {

	public PhaseAdminCreationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//span[normalize-space()='Go to parks']")
	WebElement goToParksButton;
	WebElement ViewAllParks;
	@FindBy(xpath="//div[@id='root']//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//a[1]//*[name()='svg']")
	WebElement ViewPark;
	@FindBy(xpath="(//div[@class='d-flex flex-column gap-0 card-wrap'])[1]//following-sibling::div//a[1]//*[name()='svg']")
	WebElement eyeIconForFirstParkInTheList;
	@FindBy(xpath="//a[normalize-space()='View all phases']")
	WebElement ViewAllPhase;
	@FindBy(xpath="//div[@id='root']//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//a[1]//*[name()='svg']//*[name()='path' and contains(@d,'M572.52 24')]")
	WebElement ViewPhase;
	@FindBy(xpath="//a[@role='button']")
	WebElement createPhaseAdminBtn;
	@FindBy(xpath="//input[@placeholder='Enter username for phase admin login']")
	WebElement PhaseAdminUserName;
	@FindBy(xpath="//input[@placeholder='Enter password for phase admin login']")
	WebElement PhaseAdminPassword;
	@FindBy(xpath="//input[@placeholder='Re-enter password for phase admin login']")
	WebElement PhaseAdminConfirmPassword;
	@FindBy(xpath="//input[@placeholder='Enter contact name for phase admin']")
	WebElement PhaseAdminContactName;
	@FindBy(xpath="//input[@placeholder='Enter email id for phase admin contact']")
	WebElement PhaseAdminContactEmail;
	@FindBy(xpath="	//input[@placeholder='Enter alternate email id for phase admin contact']")
	WebElement PhaseAdminContactAlternateEmail;
	@FindBy(xpath="//input[@placeholder='Enter contact number for phase admin']")
	WebElement PhaseAdminContactNumber;
	@FindBy(xpath="//button[@type='submit']")
	WebElement createPhaseAdminSubmitBtn; 
	@FindBy(xpath = "//span[contains(text(),'›')]")
	WebElement btn_Next;
	@FindBy(xpath = "//div[contains(text(),'Phase Admin created successfully')]")
	WebElement successMessage;
	@FindBy(xpath="//div[contains(text(),'Phase Admin with same Username exists')]")
	WebElement phaseAdminExistsMessage; 
	@FindBy(xpath="//div[contains(text(),'Phase Admin with same Email ID exists')]")
	WebElement phaseAdminEmailExistsMessage;
	@FindBy(xpath="//div[contains(text(),'Password is required')]")	
	WebElement passwordRequiredMessage;
	@FindBy(xpath="//div[contains(text(),'Contact name is required')]")	
	WebElement contactNameRequiredMessage;
	@FindBy(xpath="//div[contains(text(),'Email id is required')]")	
	WebElement contactEmailRequiredMessage;
	@FindBy(xpath="//div[contains(text(),'Contact number is required')]")
	WebElement contactNumberRequiredMessage;
	@FindBy(xpath="//div[contains(text(),'Please enter a valid email id')]")	
	WebElement invalidEmailMessage;
	@FindBy(xpath="//div[contains(text(),'Please enter a valid alternate email id')]")
	WebElement invalidAlternateEmailMessage;
	@FindBy(xpath="//div[normalize-space()='Contact number must contain at least 10 numeric characters']")
	WebElement invalidContactNumberMessage; 
	@FindBy(xpath="//div[normalize-space()='Contact number must contain only numeric characters']")
	WebElement invalidContactNumberMessage2; 
	@FindBy(xpath="//div[normalize-space()='Contact name must start with a letter and contain only alphanumeric characters']")
	WebElement invalidContactNameMessage; 
	@FindBy(xpath="//div[contains(text(),'Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)')]")
	WebElement passwordValidationMessage; 
	@FindBy(xpath="//div[contains(text(),'Passwords do not match')]")
	WebElement passwordsDoNotMatchMessage;
	@FindBy(xpath="//div[contains(text(),'Username must contain only alphanumeric characters')]")
	WebElement usernameValidationMessage; 
	
	
	 
//	public void eyeIcon_button() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
//		wait.until(ExpectedConditions.elementToBeClickable(eyeIconForFirstParkInTheList));
//		eyeIconForFirstParkInTheList.click();
//	}
//	

	public void GoToParksButton() throws InterruptedException {
		Thread.sleep(3000);
	goToParksButton.click();
	
}

	public void clickViewPark() throws InterruptedException {
		Thread.sleep(3000);
		ViewPark.click();	
	}

	public void clickViewAllPhase() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Execute JavaScript to scroll to the bottom of the page
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		ViewAllPhase.click();	
	}

	public boolean viewPark(String parkName) throws InterruptedException {
		boolean parkFound = false;

		// loop to handle pagination
		do {
			// Step 1: Locate the park by its name
			List<WebElement> parksList = driver.findElements(By.xpath("//h5[text()='" + parkName + "']"));

			// Check if the park is present
			if (parksList.size() > 0) {
				// Park is found; get the ID from the corresponding edit icon
				WebElement parkElement = parksList.get(0); // Assuming the first match is the desired one

				//  Locate the corresponding edit icon using the park's position
				WebElement viewIcon = parkElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'park-details')]"));

				// Click the edit icon
				viewIcon.click();
				parkFound = true; // Mark park as found
				break; // Exit the loop if the park is found
			} 
			else {
				// No park found, handle scrolling and pagination

				// Scroll up to the top of the page and check again
				scrollToTop(); // Scroll to the top of the page
				parksList = driver.findElements(By.xpath("//h5[text()='" + parkName + "']"));
				if (parksList.size() > 0) {
					// If found after scrolling up
					WebElement parkElement = parksList.get(0);
					WebElement viewIcon = parkElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'park-details')]"));
					viewIcon.click();
					parkFound = true;
					break;
				}

				// If still not found, scroll down to check the bottom part of the page
				scrollToBottom();
				parksList = driver.findElements(By.xpath("//h5[text()='" + parkName + "']"));
				if (parksList.size() > 0) {
					// If found after scrolling down
					WebElement parkElement = parksList.get(0);
					WebElement viewIcon = parkElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'park-details')]"));
					viewIcon.click();
					parkFound = true;
					break;
				}

				// Step 6: If the park is still not found, go to the next page
				if (btn_Next.isEnabled()) {
					btn_Next.click(); // Click the Next button to go to the next page
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0, 0);"); // Scroll to the top of the page
					Thread.sleep(2000);// Wait for the page to load
				} else {
					// Next button is not enabled, no more pages to check
					break; // Exit the loop
				}
			}
		} while (!parkFound); // Continue until the park is found or no more pages are available

		return parkFound; // Return whether the park was found
	}

	public boolean viewPhase(String phaseName) throws InterruptedException {
		boolean phaseFound = false;

		// loop to handle pagination
		do {
			// Step 1: Locate the park by its name
			List<WebElement> phaseList = driver.findElements(By.xpath("//h5[text()='" + phaseName + "']"));

			// Check if the park is present
			if (phaseList.size() > 0) {
				// Park is found; get the ID from the corresponding edit icon
				WebElement phaseElement = phaseList.get(0); // Assuming the first match is the desired one

				//  Locate the corresponding edit icon using the park's position
				WebElement editIcon = phaseElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'phase-details')]"));

				// Click the edit icon
				editIcon.click();
				phaseFound = true; // Mark park as found
				break; // Exit the loop if the park is found
			} 
			else {
				// No park found, handle scrolling and pagination

				// Scroll up to the top of the page and check again
				scrollToTop(); // Scroll to the top of the page
				phaseList = driver.findElements(By.xpath("//h5[text()='" + phaseName + "']"));
				if (phaseList.size() > 0) {
					// If found after scrolling up
					WebElement parkElement = phaseList.get(0);
					WebElement editIcon = parkElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'phase-details')]"));
					editIcon.click();
					phaseFound = true;
					break;
				}

				// If still not found, scroll down to check the bottom part of the page
				scrollToBottom();
				phaseList = driver.findElements(By.xpath("//h5[text()='" + phaseName + "']"));
				if (phaseList.size() > 0) {
					// If found after scrolling down
					WebElement phaseElement = phaseList.get(0);
					WebElement editIcon = phaseElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'phase-details')]"));
					editIcon.click();
					phaseFound = true;
					break;
				}

				// Step 6: If the park is still not found, go to the next page
				if (btn_Next.isEnabled()) {
					btn_Next.click(); // Click the Next button to go to the next page
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0, 0);"); // Scroll to the top of the page
					Thread.sleep(2000);// Wait for the page to load
				} else {
					// Next button is not enabled, no more pages to check
					break; // Exit the loop
				}
			}
		} while (!phaseFound); // Continue until the park is found or no more pages are available

		return phaseFound; // Return whether the park was found
	}

	public void clickEditIconByName(String name) {
		
		String xpath = String.format("//h6[.//div[text()='%s']]//a[contains(@href, 'create-phase-admin')]", name);
		driver.findElement(By.xpath(xpath)).click();
	}

	public void clickCreatePhaseAdmin() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(createPhaseAdminBtn));
		createPhaseAdminBtn.click();	
			}

	public String setPhaseAdminUserName(String username) {
		wait.until(ExpectedConditions.visibilityOf(PhaseAdminUserName));
		PhaseAdminUserName.clear();
		PhaseAdminUserName.sendKeys(username);
		return username;	
	}

	public void setPhaseAdminPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(PhaseAdminPassword));
		PhaseAdminPassword.clear();
		PhaseAdminPassword.sendKeys(password);	
	}

	public void setPhaseAdminConfirmPassword(String confirmPassword) {
		wait.until(ExpectedConditions.visibilityOf(PhaseAdminConfirmPassword));
		PhaseAdminConfirmPassword.clear();
		PhaseAdminConfirmPassword.sendKeys(confirmPassword);	
	}

	public void setPhaseAdminContactName(String contactName) {
		wait.until(ExpectedConditions.visibilityOf(PhaseAdminContactName));
		PhaseAdminContactName.clear();
		PhaseAdminContactName.sendKeys(contactName);	
	}
	public String setPhaseAdminContactEmail(String contactEmail) {
		wait.until(ExpectedConditions.visibilityOf(PhaseAdminContactEmail));
		PhaseAdminContactEmail.clear();
		PhaseAdminContactEmail.sendKeys(contactEmail);
		return contactEmail;	
	}
	public void setPhaseAdminContactAlternateEmail(String contactAlternateEmail) {
		wait.until(ExpectedConditions.visibilityOf(PhaseAdminContactAlternateEmail));
		PhaseAdminContactAlternateEmail.clear();
		PhaseAdminContactAlternateEmail.sendKeys(contactAlternateEmail);	
	}
	public void setPhaseAdminContactNumber(String contactNumber) {
		wait.until(ExpectedConditions.visibilityOf(PhaseAdminContactNumber));
		PhaseAdminContactNumber.clear();
		PhaseAdminContactNumber.sendKeys(contactNumber);	
	}
	public void clickCreatePhaseAdminSubmit() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(createPhaseAdminSubmitBtn));
		createPhaseAdminSubmitBtn.click();	    
	}
	public String getSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		return successMessage.getText();
	}
	public String getPhaseAdminExistsMessage() {
		wait.until(ExpectedConditions.visibilityOf(phaseAdminExistsMessage));
		return phaseAdminExistsMessage.getText();
	}
	public String getPhaseAdminEmailExistsMessage() {
		wait.until(ExpectedConditions.visibilityOf(phaseAdminEmailExistsMessage));
		return phaseAdminEmailExistsMessage.getText();
	}
	public String getInvalidEmailMessage() {
		wait.until(ExpectedConditions.visibilityOf(invalidEmailMessage));
		return invalidEmailMessage.getText();
	}
	public String getInvalidAlternateEmailMessage() {
		wait.until(ExpectedConditions.visibilityOf(invalidAlternateEmailMessage));
		return invalidAlternateEmailMessage.getText();}
	public String getInvalidContactNumberMessage() {
		wait.until(ExpectedConditions.visibilityOf(invalidContactNumberMessage));
		return invalidContactNumberMessage.getText();}
	public String getInvalidContactNumberMessage2() {
		wait.until(ExpectedConditions.visibilityOf(invalidContactNumberMessage2));
		return invalidContactNumberMessage2.getText();}
	public String getInvalidContactNameMessage() {
		wait.until(ExpectedConditions.visibilityOf(invalidContactNameMessage));
		return invalidContactNameMessage.getText();}
	
	public String getPasswordRequiredMessage() {	
		wait.until(ExpectedConditions.visibilityOf(passwordRequiredMessage));
		return passwordRequiredMessage.getText();
	}
	public String getContactNameRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(contactNameRequiredMessage));
		return contactNameRequiredMessage.getText();
	}
	public String getContactEmailRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(contactEmailRequiredMessage));
		return contactEmailRequiredMessage.getText();
	}
	public String getContactNumberRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(contactNumberRequiredMessage));
		return contactNumberRequiredMessage.getText();
	}
	public String getPasswordValidationMessage() {
		wait.until(ExpectedConditions.visibilityOf(passwordValidationMessage));
		return passwordValidationMessage.getText();
	}
	public String getPasswordsDoNotMatchMessage() {
		wait.until(ExpectedConditions.visibilityOf(passwordsDoNotMatchMessage));
		return passwordsDoNotMatchMessage.getText();
	}
	public String getUsernameValidationMessage() {
		wait.until(ExpectedConditions.visibilityOf(usernameValidationMessage));
		return usernameValidationMessage.getText();
	}
	
	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(4);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + generatednumber);
	}
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public void scrollToBottom() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(2000);
	}
	public void scrollToTop() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);"); 
		Thread.sleep(2000); 
	}

//	public String getPhaseAdminUserName() {
//		// TODO Auto-generated method stub
//		wait.until(ExpectedConditions.visibilityOf(PhaseAdminUserName));
//	    return PhaseAdminUserName.getAttribute("value");
//	}
	

}
