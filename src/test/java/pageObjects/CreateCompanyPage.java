package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CreateCompanyPage extends BasePage{

	public CreateCompanyPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//a[normalize-space()='View all parks']")
	WebElement ViewAllParks;
	@FindBy(xpath="//div[@id='root']//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//a[1]//*[name()='svg']")
	WebElement ViewPark;
	@FindBy(xpath="//a[normalize-space()='View all phases']")
	WebElement ViewAllPhase;
	@FindBy(xpath="//div[@id='root']//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//a[1]//*[name()='svg']//*[name()='path' and contains(@d,'M572.52 24')]")
	WebElement ViewPhase;
	@FindBy(xpath = "//span[contains(text(),'›')]")
	WebElement btn_Next;
	@FindBy(xpath="//a[normalize-space()='View all buildings']")
	WebElement ViewAllBuildings;
	@FindBy(xpath="//a[@role='button']")
	WebElement CreateCompanyButton;
	@FindBy(xpath="//input[@placeholder='Enter company name']")
	WebElement CompanyName;
	@FindBy(xpath="//input[@placeholder='Location']")
	WebElement Location;
	@FindBy(xpath="//div[6]//div[1]//select[1]")
	WebElement SelectFloor;
	@FindBy(xpath="//input[@placeholder='Enter username for company login']")
	WebElement CompanyLoginUsername;
	@FindBy(xpath="//input[@placeholder='Enter passsword for company login']")
	WebElement CompanyLoginPassword;
	@FindBy(xpath="//input[@placeholder='Re-enter password for company login']")
	WebElement CompanyLoginRePassword;
	@FindBy(xpath="//input[@placeholder='Enter contact name for the company']")
	WebElement ContactName;
	@FindBy(xpath="//input[@placeholder='Enter HR email id for company contact']")
	WebElement ContactEmail;
	@FindBy(xpath="//input[@placeholder='Enter alternate email id for company contact']")
	WebElement ContactAlternateEmail;
	@FindBy(xpath="//input[@placeholder='Enter contact number for the company']")
	WebElement ContactNumber;
	@FindBy(xpath="//div[contains(@class,'mb-4')]//div[3]//div[1]//select[1]")
	WebElement dropdownElement;
	@FindBy(xpath="//div[4]//div[1]//select[1]")
	WebElement dropdownElement1;
	@FindBy(xpath="//div[5]//div[1]//select[1]")
	WebElement dropdownElement2;
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitButton;
	@FindBy(xpath ="//div[contains(text(),'Company Admin created successfully and credentials')]")
	WebElement successMessage;
	@FindBy(xpath="//div[contains(text(),'User with same Username exists')]")
	WebElement userExistsMessage; // This is the message for username validation
	@FindBy(xpath="//div[contains(text(),'Company name is required')]")	
	WebElement CompanyNameRequiredMessage;
	@FindBy(xpath="//div[contains(text(),'Location is required')]")
	WebElement LocationRequiredMessage;
	@FindBy(xpath="//div[contains(text(),'Floor is required')]")
	WebElement FloorRequiredMessage;
	@FindBy(xpath="//div[contains(text(),'Username is required')]")
	WebElement UsernameRequiredMessage; // This is the message for username validation
	@FindBy(xpath="//div[contains(text(),'Password is required')]")
	WebElement PasswordRequiredMessage; // This is the message for password validation
	@FindBy(xpath="//div[contains(text(),'Contact name is required')]")
	WebElement ContactNameRequiredMessage; // This is the message for contact name validation
	@FindBy(xpath="//div[contains(text(),'HR email id is required')]")
	WebElement ContactEmailRequiredMessage; // This is the message for contact email validation
	@FindBy(xpath="//div[contains(text(),'Contact number is required')]")
	WebElement ContactNumberRequiredMessage; // This is the message for contact number validation
	@FindBy(xpath="//div[contains(text(),'Username can only contain alphanumeric characters')]")
	WebElement UsernameValidationMessage;
	@FindBy(xpath="//div[contains(text(),'Username should not start with a number')]")
	WebElement UsernameValidationMessage1;
	// This is the message for username validation
	@FindBy(xpath="//div[contains(text(),'Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)')]")
	WebElement passwordValidationMessage; 
	@FindBy(xpath="//div[contains(text(),'Passwords do not match')]")
	WebElement passwordsDoNotMatchMessage;
	@FindBy(xpath="//div[contains(text(),'Password length must be greater than 8 characters')]")
	WebElement passwordValidationMessage1; 
	
	@FindBy(xpath="//div[normalize-space()='Contact number must contain at least 10 numeric characters']")
	WebElement invalidContactNumberMessage; 
	@FindBy(xpath="//div[normalize-space()='Contact number must contain only numeric characters']")
	WebElement invalidContactNumberMessage2; 
	@FindBy(xpath="//div[contains(text(),'Please enter a valid email id')]")	
	WebElement invalidEmailMessage;
	@FindBy(xpath="//div[contains(text(),'Please enter a valid alternate email id')]")
	WebElement invalidAlternateEmailMessage;
	@FindBy(xpath="//div[normalize-space()='Contact name must start with a letter and contain only alphanumeric characters']")
	WebElement invalidContactNameMessage; 
	@FindBy(xpath="//div[contains(text(),'Company with same name exists')]")
	WebElement CompanyNameExistsMessage; 
	@FindBy(xpath="//div[contains(text(),'User with same Email exists')]")
	WebElement companyEmailExistsMessage;	
	@FindBy(xpath="//div[contains(text(),'User with same Username exists')]")
	WebElement companyUsernameExistsMessage;
	@FindBy(xpath="//span[normalize-space()='Go back']")
	WebElement backButton;


public void clickBackButton() {
		wait.until(ExpectedConditions.elementToBeClickable(backButton));
		backButton.click();
	}


public void clickViewAllPark() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // scroll into view
    js.executeScript("arguments[0].scrollIntoView(true);", ViewAllParks);

    // wait until it becomes clickable
    WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(ViewAllParks));

    try {
        ele.click();
    } catch (ElementClickInterceptedException e) {
        // fallback if something overlaps it (common in headless mode)
        js.executeScript("arguments[0].click();", ele);
    }
}


	

public void clickViewAllPhase() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Scroll the button into view
    js.executeScript("arguments[0].scrollIntoView(true);", ViewAllPhase);

    // Wait for the element to be clickable
    WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(ViewAllPhase));

    try {
        ele.click();
    } catch (ElementClickInterceptedException e) {
        // Fallback: JavaScript click when intercepted
        js.executeScript("arguments[0].click();", ele);
    }
}
	public void clickViewAllBuildings() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(ViewAllBuildings));
	    ViewAllBuildings.click();	
	}

	public boolean viewPark(String parkName) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

	    while (true) {
	        try {
	            // Try to locate the park on the current page
	            By parkTile = By.xpath("//h5[text()='" + parkName + "']");
	            WebElement parkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(parkTile));

	            // When found, click its corresponding view icon
	            By viewIcon = By.xpath("//h5[text()='" + parkName + "']/following::a[contains(@href,'park-details')]");
	            WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(viewIcon));

	            // Scroll + click (safe for headless)
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	            try {
	                ele.click();
	            } catch (ElementClickInterceptedException e) {
	                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
	            }
	            return true;

	        } catch (TimeoutException e) {
	            // Park not found => go to next page
	            if (btn_Next.isEnabled()) {
	                btn_Next.click();
	                // Wait for pagination page load
	                wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.tagName("body"))));
	            } else {
	                break;
	            }
	        }
	    }
	    return false;
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

	public boolean viewBuilding(String buildingName) throws InterruptedException {
		boolean buildingFound = false;

		// loop to handle pagination
		do {
			// Step 1: Locate the park by its name
			List<WebElement> buildingList = driver.findElements(By.xpath("//h5[text()='" + buildingName + "']"));

			// Check if the park is present
			if (buildingList.size() > 0) {
				// Park is found; get the ID from the corresponding edit icon
				WebElement buildingElement = buildingList.get(0); // Assuming the first match is the desired one

				//  Locate the corresponding edit icon using the park's position
				WebElement viewIcon = buildingElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'building-details')]"));

				// Click the edit icon
				viewIcon.click();
				buildingFound = true; // Mark park as found
				break; // Exit the loop if the park is found
			} 
			else {
				// No park found, handle scrolling and pagination

				// Scroll up to the top of the page and check again
				scrollToTop(); // Scroll to the top of the page
				buildingList = driver.findElements(By.xpath("//h5[text()='" + buildingName + "']"));
				if (buildingList.size() > 0) {
					// If found after scrolling up
					WebElement buildingElement = buildingList.get(0);
					WebElement viewIcon = buildingElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'building-details')]"));
					viewIcon.click();
					buildingFound = true;
					break;
				}

				// If still not found, scroll down to check the bottom part of the page
				scrollToBottom();
				buildingList = driver.findElements(By.xpath("//h5[text()='" + buildingName + "']"));
				if (buildingList.size() > 0) {
					// If found after scrolling down
					WebElement buildingElement = buildingList.get(0);
					WebElement viewIcon = buildingElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'building-details')]"));
					viewIcon.click();
					buildingFound = true;
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
		} while (!buildingFound); // Continue until the park is found or no more pages are available

		return buildingFound; // Return whether the park was found
	}


	public boolean viewCompanyInTable(String companyName) throws InterruptedException {
		boolean companyFound = false;
		By nextIcon = By.xpath("//span[@aria-hidden='true' and text()='›']");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		do {
			List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));
			if (rows.isEmpty()) {
				System.out.println("No rows found on current page.");
				break;
			}

			for (WebElement row : rows) {
				if (row.getText().contains(companyName)) {
					// Optionally, click the row or a specific icon here
					companyFound = true;
					System.out.println("✅ Target company found!  -  "+companyName);
					break;
				}
			}

			if (companyFound) {
				break;
			}

			List<WebElement> next = driver.findElements(nextIcon);
			Thread.sleep(2000);
			if (next.isEmpty() || !next.get(0).isEnabled()) {
				System.out.println("❌ Reached last page. Company not found.");
				break;
			}

			js.executeScript("arguments[0].scrollIntoView(true);", next.get(0));
			next.get(0).click();
			Thread.sleep(2000); // Wait for the page to load

		} while (!companyFound);

		return companyFound;
	}
	public void clickCreateCompanyButton() throws InterruptedException {
		Thread.sleep(2000);
		CreateCompanyButton.click();
	}
	public String setCompanyName(String companyName) {
		wait.until(ExpectedConditions.visibilityOf(CompanyName));
		CompanyName.clear();
		CompanyName.sendKeys(companyName);
		return companyName;
	}
	public void setLocation(String location) {
		wait.until(ExpectedConditions.visibilityOf(Location));
		Location.clear();
		Location.sendKeys(location);
	}

	public void parkfoundIndropDown(String parkName) {
		Select dropdown = new Select(dropdownElement);	    
		boolean optionFound = false;

		for (WebElement option : dropdown.getOptions()) {
			if (option.getText().trim().equalsIgnoreCase(parkName)) {
				optionFound = true;
				System.out.println("Option found: " + option.getText());
				break;
			}

		}
	}

	public void phasefoundIndropDown(String phaseName) {
		Select dropdown = new Select(dropdownElement1);	    
		boolean optionFound = false;

		for (WebElement option : dropdown.getOptions()) {
			if (option.getText().trim().equalsIgnoreCase(phaseName)) {
				optionFound = true;
				System.out.println("Option found: " + option.getText());
				break;
			}

		}}

	public void buildingfoundIndropDown(String buildingName) {
		Select dropdown = new Select(dropdownElement2);	    
		boolean optionFound = false;

		for (WebElement option : dropdown.getOptions()) {
			if (option.getText().trim().equalsIgnoreCase(buildingName)) {
				optionFound = true;
				System.out.println("Option found: " + option.getText());
				break;
			}

		}}

	public void setSelectFloor() {
		wait.until(ExpectedConditions.visibilityOf(SelectFloor));
		Select select = new Select(SelectFloor);
		select.selectByValue("2");
	}
	public String setCompanyLoginUsername(String username) {
		wait.until(ExpectedConditions.visibilityOf(CompanyLoginUsername));
		CompanyLoginUsername.clear();
		CompanyLoginUsername.sendKeys(username);
		return username;
	}
	public void setCompanyLoginPassword(String password) {
		wait.until(ExpectedConditions.visibilityOf(CompanyLoginPassword));
		CompanyLoginPassword.clear();
		CompanyLoginPassword.sendKeys(password);
	}
	public void setCompanyLoginRePassword(String rePassword) {
		wait.until(ExpectedConditions.visibilityOf(CompanyLoginRePassword));
		CompanyLoginRePassword.clear();
		CompanyLoginRePassword.sendKeys(rePassword);
	}
	public void setContactName(String contactName) {
		wait.until(ExpectedConditions.visibilityOf(ContactName));
		ContactName.clear();
		ContactName.sendKeys(contactName);
	}
	public String setContactEmail(String contactEmail) {
		wait.until(ExpectedConditions.visibilityOf(ContactEmail));
		ContactEmail.clear();
		ContactEmail.sendKeys(contactEmail);
		return contactEmail;
	}
	public void setContactAlternateEmail(String contactAlternateEmail) {
		wait.until(ExpectedConditions.visibilityOf(ContactAlternateEmail));
		ContactAlternateEmail.clear();
		ContactAlternateEmail.sendKeys(contactAlternateEmail);
	}
	public void setContactNumber(String contactNumber) {
		wait.until(ExpectedConditions.visibilityOf(ContactNumber));
		ContactNumber.clear();
		ContactNumber.sendKeys(contactNumber);
	}
	public void clickSubmitButton() {
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
	}
	public String getSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		return successMessage.getText();
	}
	public String getCompanyNameRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(CompanyNameRequiredMessage));
		return CompanyNameRequiredMessage.getText();
	}
	public String getLocationRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(LocationRequiredMessage));
		return LocationRequiredMessage.getText();
	}
	public String getFloorRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(FloorRequiredMessage));
		return FloorRequiredMessage.getText();
	}
	public String getUsernameRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(UsernameRequiredMessage));
		return UsernameRequiredMessage.getText();
	}
	public String getPasswordRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(PasswordRequiredMessage));
		return PasswordRequiredMessage.getText();
	}
	public String getContactNameRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(ContactNameRequiredMessage));
		return ContactNameRequiredMessage.getText();
	}
	public String getContactEmailRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(ContactEmailRequiredMessage));
		return ContactEmailRequiredMessage.getText();
	}
	public String getContactNumberRequiredMessage() {
		wait.until(ExpectedConditions.visibilityOf(ContactNumberRequiredMessage));
		return ContactNumberRequiredMessage.getText();
	}
	public String getUsernameValidationMessage() {
		wait.until(ExpectedConditions.visibilityOf(UsernameValidationMessage));
		return UsernameValidationMessage.getText();
	}
	public String getUsernameValidationMessage1() {
		wait.until(ExpectedConditions.visibilityOf(UsernameValidationMessage1));
		return UsernameValidationMessage1.getText();
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
	public String getPasswordValidationMessage() {
		wait.until(ExpectedConditions.visibilityOf(passwordValidationMessage));
		return passwordValidationMessage.getText();
	}
	public String getPasswordValidationMessage1() {
		wait.until(ExpectedConditions.visibilityOf(passwordValidationMessage1));
		return passwordValidationMessage1.getText();
	}
	public String getPasswordsDoNotMatchMessage() {
		wait.until(ExpectedConditions.visibilityOf(passwordsDoNotMatchMessage));
		return passwordsDoNotMatchMessage.getText();
	}
	public String getUserExistsMessage() {
		wait.until(ExpectedConditions.visibilityOf(userExistsMessage));
		return userExistsMessage.getText();
	}
	public String getCompanyNameExistsMessage() {
		wait.until(ExpectedConditions.visibilityOf(CompanyNameExistsMessage));
		return CompanyNameExistsMessage.getText();
	}
	public String getCompanyEmailExistsMessage() {
		wait.until(ExpectedConditions.visibilityOf(companyEmailExistsMessage));
		return companyEmailExistsMessage.getText();
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
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(2000);
	}
	public void scrollToBottom1() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 400);");
		Thread.sleep(2000);
	}
	public void scrollTotop1() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -250);");
		Thread.sleep(2000);
	}
	public void scrollToTop() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);"); 
		Thread.sleep(2000); 
	}
	//script for scroll until element found
	public void scrollUntilElementFound () {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = CompanyLoginUsername;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(2000); // Optional: wait for the element to be in view
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
