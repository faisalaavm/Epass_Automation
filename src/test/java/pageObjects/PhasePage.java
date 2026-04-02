package pageObjects;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Model class for the Phase page.
 * Encapsulates all web elements and actions related to the Phase page.
 */
public class PhasePage extends BasePage {
	WebDriverWait wait;
	/**
     * Constructor for PhasePage.
     * @param driver The WebDriver instance to use.
     */
	public PhasePage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	// ------------------- Web Elements -------------------
	@FindBy(xpath = "//span[text()=' Go to parks']")
	WebElement goToParkButton;

	@FindBy(xpath = "(//*[name()='svg' and contains(@class, 'h6')])[1]")
	WebElement parkEyeIcon;

	@FindBy(xpath = "//a[text()='View all phases']")
	WebElement viewAllPhasesLink;

	@FindBy(xpath = "//span[text()='Create Phase']")
	WebElement createPhaseButton;

	@FindBy(xpath = "//input[@placeholder='Enter phase name']")
	WebElement phaseNameInput;

	@FindBy(xpath = "//input[@placeholder='Enter location of the phase']")
	WebElement phaseLocationInput;

	@FindBy(xpath = "//input[@placeholder='Enter the primary street address of the phase ']")
	WebElement phasePrimaryStreetAddressInput;

	@FindBy(xpath = "//input[@placeholder='Enter the additional street address of the phase ']")
	WebElement phaseAdditionalStreetAddressInput;

	@FindBy(xpath = "//input[@placeholder='Enter the city where the phase is situated ']")
	WebElement phaseCityInput;

	@FindBy(xpath = "//input[@placeholder='Enter the district where the phase is situated']")
	WebElement phaseDistrictInput;

	@FindBy(xpath = "//input[@placeholder='Enter the state where the phase is situated']")
	WebElement phaseStateInput;

	@FindBy(xpath = "//input[@placeholder=\"Enter the pin code of the phase's location\"]")
	WebElement phasePinCodeInput;

	@FindBy(xpath = "//button[text()='Create Phase']")
	WebElement createPhaseSubmitButton;
	
	@FindBy(xpath = "//div[@class='text-danger' and text()='Phase name is required']")
	WebElement blankPhaseNameValidation;
	
	@FindBy(xpath = "//div[@class='text-danger' and contains(text(),'Phase name must start with a letter')]")
    WebElement phaseNameMustStartWithLetterValidation;

	@FindBy(xpath = "//div[contains(@class, 'text-danger') and contains(text(), 'Pin code must contain only numbers')]")
	WebElement pinCodeMustContainOnlyNumbersValidation;
	
	@FindBy(xpath = "//h4[contains(@class, 'main-title') and contains(text(), 'Create Phase')]")
	WebElement createPhasePageTitle;
	
	@FindBy(xpath = "//h6[@class='card-title']/b[text()='Phase Details']")
	WebElement phasePageDetailsTitle;
	
	@FindBy(xpath = "//div[@class='text-muted' and text()='Provide the necessary information to create a phase.']")
	WebElement createPhaseHelpText;
	
	@FindBy(xpath = "//img[@alt='logo' and contains(@src, 'Logo_pearlsoft')]")
	WebElement pearlsoftLogo;

	@FindBy(xpath = "//img[@class='img-fluid' and contains(@src, 'logo')]")
     WebElement infoparkLogo;
	
	@FindBy(xpath = "//div[contains(text(),'Phase created successfully')]")
	WebElement phaseCreatedSuccessMessage;
	
	@FindBy(xpath = "//div[contains(text(),'Phase with same name exists')]")
	WebElement phaseWithSameNameExistsMessage;
	
	@FindBy(xpath = "//span[contains(text(),'Go back')]")
	WebElement goBackButton;
	
	@FindBy(xpath = "//h4[contains(text(),'Phase List')]")
	WebElement phaseListHeader;
	
	@FindBy(xpath = "(//div[contains(@class,'card')])[1]//h5")
	WebElement parkNameElement;
	
	@FindBy(xpath = "//select[@class='form-select']")
	WebElement parkDropdownElement;
	
  
	// ---------- Actions with Explicit Wait ----------
	/**
     * Clicks the "Go to parks" button.
	 * @throws InterruptedException 
     */
	public void clickGoToParkButton() throws InterruptedException {
		Thread.sleep(5000); // Wait for the page to load
		wait.until(ExpectedConditions.elementToBeClickable(goToParkButton)).click();
	}
	/**
     * Clicks the park eye icon.
     */
	public void clickParkEyeIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(parkEyeIcon)).click();
	}
	/**
     * Clicks the "View all phases" link.
     */
	public void clickViewAllPhasesLink() {
		wait.until(ExpectedConditions.elementToBeClickable(viewAllPhasesLink)).click();
	}
	 /**
     * Clicks the "Create Phase" button.
     */
	public void clickCreatePhaseButton() {
	    WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(createPhaseButton));

	    // Scroll into view (important for headless browsers)
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

	    try {
	        ele.click();
	    } catch (ElementClickInterceptedException e) {
	        // Fallback to JavaScript click if something overlaps the button
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
	    }
	}
	/**
     * Sets the phase name input field.
     * @param phaseName The phase name to enter.
     */
	public void setPhaseName(String phaseName) {
		wait.until(ExpectedConditions.visibilityOf(phaseNameInput)).clear();
		phaseNameInput.sendKeys(phaseName);
	}
	  /**
     * Sets the phase location input field.
     * @param phaseLocation The location to enter.
     */
	public void setPhaseLocation(String phaseLocation) {
		wait.until(ExpectedConditions.visibilityOf(phaseLocationInput)).clear();
		phaseLocationInput.sendKeys(phaseLocation);
	}
	/**
     * Sets the primary street address input field.
     * @param primaryStreetAddress The address to enter.
     */
	public void setPhasePrimaryStreetAddress(String primaryStreetAddress) {
		wait.until(ExpectedConditions.visibilityOf(phasePrimaryStreetAddressInput)).clear();
		phasePrimaryStreetAddressInput.sendKeys(primaryStreetAddress);
	}
	 /**
     * Sets the additional street address input field.
     * @param additionalStreetAddress The address to enter.
     */
	public void setPhaseAdditionalStreetAddress(String additionalStreetAddress) {
		wait.until(ExpectedConditions.visibilityOf(phaseAdditionalStreetAddressInput)).clear();
		phaseAdditionalStreetAddressInput.sendKeys(additionalStreetAddress);
	}
	 /**
     * Sets the city input field.
     * @param phaseCity The city to enter.
     */
	public void setPhaseCity(String phaseCity) {
		wait.until(ExpectedConditions.visibilityOf(phaseCityInput)).clear();
		phaseCityInput.sendKeys(phaseCity);
	}

    /**
     * Sets the district input field.
     * @param phaseDistrict The district to enter.
     */
	public void setPhaseDistrict(String phaseDistrict) {
		wait.until(ExpectedConditions.visibilityOf(phaseDistrictInput)).clear();
		phaseDistrictInput.sendKeys(phaseDistrict);
	}
    /**
     * Sets the state input field.
     * @param phaseState The state to enter.
     */
	public void setPhaseState(String phaseState) {
		wait.until(ExpectedConditions.visibilityOf(phaseStateInput)).clear();
		phaseStateInput.sendKeys(phaseState);
	}
	 /**
     * Sets the pin code input field.
     * @param phasePinCode The pin code to enter.
     */
	public void setPhasePinCode(String phasePinCode) {
		wait.until(ExpectedConditions.visibilityOf(phasePinCodeInput)).clear();
		phasePinCodeInput.sendKeys(phasePinCode);
	}
	 /**
     * Clicks the "Create Phase" submit button using JavaScript.
     */
	
	public void clickCreatePhaseSubmitButton() {
	    wait.until(ExpectedConditions.elementToBeClickable(createPhaseSubmitButton));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createPhaseSubmitButton);
	}

	/**
     * Gets the validation message for blank phase name.
     * @return The validation message text.
     */
	public String blankPhaseNameValidation() {
	    wait.until(ExpectedConditions.visibilityOf(blankPhaseNameValidation));
	    return blankPhaseNameValidation.getText();
	}
	/**
     * Gets the validation message when phase name does not start with a letter.
     * @return The validation message text.
     */
	 public String getPhaseNameMustStartWithLetterValidation() {
		    wait.until(ExpectedConditions.visibilityOf(phaseNameMustStartWithLetterValidation));
		    return phaseNameMustStartWithLetterValidation.getText();
		}
	 /**
	     * Gets the validation message when pin code contains non-numeric characters.
	     * @return The validation message text.
	     */
	 public String getPinCodeMustContainOnlyNumbersValidation() {
		    wait.until(ExpectedConditions.visibilityOf(pinCodeMustContainOnlyNumbersValidation));
		    return pinCodeMustContainOnlyNumbersValidation.getText();
		}
	 /**
	     * Gets the title text of the Create Phase page.
	     * @return The page title text.
	     */
	 public String getCreatePhasePageTitleText() {
		    wait.until(ExpectedConditions.visibilityOf(createPhasePageTitle));
		    return createPhasePageTitle.getText();
		}
	 /**
	     * Gets the Phase Details title text of the Create Phase page..
	     * @return The section title text.
	     */
	 public String getPhasePageDetailsTitleText() {
		    wait.until(ExpectedConditions.visibilityOf(phasePageDetailsTitle));
		    return phasePageDetailsTitle.getText();
		}
	 /**
	     * Gets the help text for creating a phase.
	     * @return The help text.
	     */
	 public String getCreatePhaseHelpText() {
		    wait.until(ExpectedConditions.visibilityOf(createPhaseHelpText));
		    return createPhaseHelpText.getText();
		}
	 /**
	     * Checks if the Pearlsoft logo is displayed.
	     * @return true if the logo is displayed, false otherwise.
	     */
	 
	 public boolean isPearlsoftLogoDisplayed() {
		    wait.until(ExpectedConditions.visibilityOf(pearlsoftLogo));
		    return pearlsoftLogo.isDisplayed();
		}
	 /**
	     * Checks if the Infopark logo is displayed.
	     * @return true if the logo is displayed, false otherwise.
	     */
	 public boolean isInfoparkLogoDisplayed() {
		    wait.until(ExpectedConditions.visibilityOf(infoparkLogo));
		    return infoparkLogo.isDisplayed();
		}
	 /**
	     * Gets the success message after creating a phase.
	     * @return The success message text.
	     */
	 public String getPhaseCreationSuccessMessage() {
		    wait.until(ExpectedConditions.visibilityOf(phaseCreatedSuccessMessage));
		    return phaseCreatedSuccessMessage.getText();
		}
	 /**
	     * Gets the error message when a phase with the same name exists.
	     * @return The error message text.
	     */
	 public String getPhaseWithSameNameExistsMessage() {
		    wait.until(ExpectedConditions.visibilityOf(phaseWithSameNameExistsMessage));
		    return phaseWithSameNameExistsMessage.getText();
		}
	 /**
	     * Generates a random phase name.
	     * @return A random phase name string.
	     */
	 public String randomString() {
		    String generatedString = RandomStringUtils.randomAlphabetic(5).toLowerCase();
		    return "Phase" + generatedString;
		}
		

	    /**
	     * Checks if a phase name is displayed in the paginated phase list.
	     * Navigates through pages if necessary.
	     * @param phaseName The phase name to search for.
	     * @return True if the phase name is found, false otherwise.
	     */

	 public boolean isPhaseNameDisplayed(String phaseName) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    while (true) {
		        // Refresh phase name elements for current page
		        List<WebElement> currentPhaseNames = driver.findElements(By.xpath("//h5"));

		        // Check if any phase name on this page matches
		        boolean found = currentPhaseNames.stream()
		            .anyMatch(p -> p.getText().equalsIgnoreCase(phaseName));

		        if (found) {
		            return true;
		        }

		        // Try to find the Next button on this page
		        List<WebElement> nextButtonList = driver.findElements(
		            By.xpath("//ul[contains(@class,'pagination')]//a[contains(@class,'page-link') and .//span[text()='Next']]")
		        );

		        if (nextButtonList.isEmpty()) {
		            return false; // No next button — means no more pages
		        }

		        WebElement nextBtn = nextButtonList.get(0);
		       
                if (!nextBtn.isEnabled()) {
		            return false; // Button is present but disabled
		        }

		       
		        // Click next using JavaScript
		        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);

		        // Wait for old content to become stale (i.e., page is updated)
		        if (!currentPhaseNames.isEmpty()) {
		            wait.until(ExpectedConditions.stalenessOf(currentPhaseNames.get(0)));
		        }

		        // Optional: Wait until at least one new <h5> element is present
		        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5")));
		    }
		}

	 /**
	     * Clicks the "Go back" button.
	     */

	 public void clickGoBackButton() {
		    wait.until(ExpectedConditions.elementToBeClickable(goBackButton)).click();
		}

	    /**
	     * Gets the header text of the Phase List page.
	     * @return The header text.
	     */
	 public String getPhaseListHeaderText() {
		    wait.until(ExpectedConditions.visibilityOf(phaseListHeader));
		    return phaseListHeader.getText();
		}
	 /**
	     * Gets the park name displayed on the Phase page.
	     * @return The park name text.
	     */	 
	 public String getParkName() {
		    wait.until(ExpectedConditions.visibilityOf(parkNameElement));
		    return parkNameElement.getText().trim();
		}
	 /**
	     * Gets the name of the park from the dropdown after it has been autofilled.
	     * Waits until a valid park name is selected.
	     * @return The selected park name text.
	     */
	 public String getAutofilledParkNameFromDropdown() {
		    wait.until(driver -> {
		        Select parkDropdown = new Select(parkDropdownElement);
		        return !parkDropdown.getFirstSelectedOption().getText().equals("Select Park");
		    });

		    Select parkDropdown = new Select(parkDropdownElement);
		    return parkDropdown.getFirstSelectedOption().getText().trim();
		}

	
	 
 
}

