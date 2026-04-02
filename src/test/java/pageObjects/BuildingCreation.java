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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuildingCreation extends BasePage {
	public BuildingCreation(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//span[normalize-space()='Go to parks']")
	WebElement goToParksButton;
	@FindBy(xpath="//div[@class='g-4 row row-cols-xl-3 row-cols-lg-2 row-cols-md-2 row-cols-1']//div[2]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//a[1]//*[name()='svg']//*[name()='path' and contains(@d,'M572.52 24')]")
	WebElement clickViewIcon;
	@FindBy(xpath="//a[text()=\"Infopark Cherthala Phase 1\"]\r\n")
	WebElement clickonphase;
	@FindBy(xpath="//a[@href='/dashboard/building-list/?gen' and text()='View all buildings']\r\n")
	WebElement viewallbuildings;
	@FindBy(xpath="//span[text()=\"Create Building\"]\r\n")
	WebElement createBuildingButton;
	@FindBy(xpath="//input[@placeholder='Enter building name']\r\n")
	WebElement buildingNameInput;
	@FindBy(xpath="//input[@placeholder='Enter number of floors in building ']\r\n")
	WebElement numberOfFloorsInput;
	@FindBy(xpath="//button[@type='submit' and text()='Create Building']\r\n")
	WebElement createBuildingButton2;
	@FindBy(xpath="//div[@class='text-danger' and text()='Building name is required']")
	WebElement buildingNameMandatoryValidation;
	@FindBy(xpath="//div[@role='alert' and contains(@class, 'Toastify__toast-body')]//div[text()='Building created successfully']\r\n")
	WebElement buildingCreationSuccessMessage;
	@FindBy(xpath="//div[@class='text-danger' and contains(text(), 'Building name must start with a letter')]\r\n")
	WebElement buildingNameInvalidValueValidation;
	@FindBy(xpath="//div[text()=\"Building with the same name already exists\"]\r\n")
	WebElement buildingNameExistsValidation;
	@FindBy(xpath="(//select[@aria-label='Default select example'])[1]\r\n")
	WebElement ITParkDropdown;
	@FindBy(xpath="//select[@aria-label='Default select example' and option[text()='Select Phase']]\r\n")
	WebElement PhaseDropdown;
	@FindBy(xpath = "//a[text()='View all phases']")
	WebElement viewAllPhasesLink;
	@FindBy(xpath = "//button[@aria-label='close']//*[name()='svg']")
	WebElement closeButton;
	
	 public void waitForPageToLoad() throws InterruptedException {
	        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
	            .executeScript("return document.readyState").equals("complete"));
	        Thread.sleep(2000);
	    }

	    public void goToParksButton() throws InterruptedException {
	        waitForPageToLoad();
	         // Optional: wait for the page to load before clicking
	        wait.until(ExpectedConditions.elementToBeClickable(goToParksButton));
	        goToParksButton.click();
	        waitForPageToLoad();
	    }

	    public void clickViewIcon() throws InterruptedException {
	        waitForPageToLoad();
	        wait.until(ExpectedConditions.elementToBeClickable((By) clickViewIcon));
	        clickViewIcon.click();
	        waitForPageToLoad();
	    }

	    public void clickOnPhase() throws InterruptedException {
	        waitForPageToLoad();
	        wait.until(ExpectedConditions.elementToBeClickable(clickonphase));
	        clickonphase.click();
	        waitForPageToLoad();
	    }

	    public void viewAllBuildings() throws InterruptedException {
	        waitForPageToLoad();
	        wait.until(ExpectedConditions.elementToBeClickable(viewallbuildings));
	        viewallbuildings.click();
	        waitForPageToLoad();
	    }

	    public void createBuildingButton() throws InterruptedException {
	        waitForPageToLoad();
	        wait.until(ExpectedConditions.elementToBeClickable(createBuildingButton));
	        createBuildingButton.click();
	        waitForPageToLoad();
	    }

	    public void scrollToBottom() throws InterruptedException {
	        waitForPageToLoad();
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        waitForPageToLoad();
	    }

	    public void scrollToTop() throws InterruptedException {
	        waitForPageToLoad();
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, 0);");
	        waitForPageToLoad();
	    }
	
	public String randomString() {
	    String generatedString = RandomStringUtils.randomAlphabetic(5).toLowerCase();
	    return "Building" + generatedString;
	}
	public void setBuildingNameInput(String buildingName) {
		buildingNameInput.clear();
		buildingNameInput.sendKeys(buildingName);
	}
	public void setNumberOfFloorsInput(String numberOfFloors) {
		numberOfFloorsInput.clear();
		numberOfFloorsInput.sendKeys(numberOfFloors);
	}
	public void setCreateBuildingButton() {
		createBuildingButton2.click();
	}
	public void clickViewAllPhasesLink() {
		wait.until(ExpectedConditions.visibilityOf(viewAllPhasesLink));
		viewAllPhasesLink.click();
	}
	public String validation() {
		// TODO Auto-generated method stub
		return null;
	}
	public String buildingNameMandatoryValidation() {
		wait.until(ExpectedConditions.visibilityOf(buildingNameMandatoryValidation));
		return buildingNameMandatoryValidation.getText();
	}
	public String buildingCreationSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(buildingCreationSuccessMessage));
		return buildingCreationSuccessMessage.getText();
	}
	public String buildingNameInvalidValueValidation() {
		wait.until(ExpectedConditions.visibilityOf(buildingNameInvalidValueValidation));
		return buildingNameInvalidValueValidation.getText();
	}
	public String buildingNameExistsValidation() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(buildingNameExistsValidation));
		return buildingNameExistsValidation.getText();
	}
	 public String getDefaultDropdownValue() {
		 
	        Select select = new Select(ITParkDropdown);
	        return select.getFirstSelectedOption().getText().trim(); // Gets selected option text
	 }
	 public String getPhaseDropdownValue() {
		 
	        Select select = new Select(PhaseDropdown);
	        return select.getFirstSelectedOption().getText().trim(); // Gets selected option text
	 }
	 public boolean isBuildingNameDisplayed(String buildingName) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    while (true) {
		        // Refresh park name elements for current page
		        List<WebElement> currentBuildingNames = driver.findElements(By.xpath("//h5"));

		        // Check if any park name on this page matches
		        boolean found = currentBuildingNames.stream()
		            .anyMatch(p -> p.getText().equalsIgnoreCase(buildingName));

		        if (found) {
		        		
		            return true;
		        }

		        // Try to find the Next button on this page
		        List<WebElement> nextButtonList = driver.findElements(
		            By.xpath("//span[@aria-hidden='true' and text()='›']\r\n")
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
		        if (!currentBuildingNames.isEmpty()) {
		            wait.until(ExpectedConditions.stalenessOf(currentBuildingNames.get(0)));
		        }

		        // Optional: Wait until at least one new <h5> element is present
		        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5")));
		    }
		 
	 }
	 
		 public void closeButton() {
	 //	        wait.until(ExpectedConditions.elementToBeClickable(closeButton));
	 	        closeButton.click();
	 	    }
//	 public void selectITParkDropdown(String value) {
//	        Select select = new Select(ITParkDropdown);
//	        select.selectByVisibleText(value); // Selects the option by visible text
//	    }
//	 public void selectPhaseDropdown(String value) {
//	        Select select = new Select(PhaseDropdown);
//	        select.selectByVisibleText(value); // Selects the option by visible text
//	    }
		
	}
	 
