package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditDeletePark extends BasePage {

	private String createdParkName;

	public EditDeletePark(WebDriver driver) {
		super(driver);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		// TODO Auto-generated constructor stub
	}
	// Initialize web element path
	@FindBy(xpath="//a[@href='/dashboard/create-park' and span[text()='Create Park']]")
	WebElement createParkbtn;
	@FindBy(xpath="//a[@href='/dashboard/park-list' and span[text()='Go back']]")
	WebElement backLink;
	@FindBy(xpath="//li[@class='page-item']/a[@class='page-link'][span[text()='›']]")
	List<WebElement> nextbutton;
	@FindBy(xpath="//a[contains(@href, '/dashboard/create-park/?id=')]")
	WebElement editpark;
	@FindBy(xpath="//div[contains(@class, 'card-wrap')]/h5")
	WebElement parkTitle;
	@FindBy(xpath="//input[@placeholder='Enter park name']")
	WebElement parkName;
	@FindBy(xpath="//input[@placeholder='Enter location of the park']")
	WebElement parklocation;
	@FindBy(xpath="//label[contains(text(),'Street 1')]/parent::div//input")
	WebElement streetone;
	@FindBy(xpath="//label[contains(text(),'Street 2')]/following-sibling::input")
	WebElement streettwo;
	@FindBy(xpath="//label[contains(text(),'City')]/following-sibling::input")
	WebElement city;
	@FindBy(xpath="//label[contains(text(),'District')]/parent::div//input")
	WebElement district;	
	@FindBy(xpath="//label[contains(text(),'State')]/parent::div//input")
	WebElement state;
	@FindBy(xpath="//label[contains(text(),'Pin Code')]/parent::div//input")
	WebElement pincode;	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitpark;
	@FindBy(css="div.park-card")
	List<WebElement> cardName;
	@FindBy(xpath="//a[@class='text-primary' and @href='/dashboard/park-list']")
	WebElement deleteparkbtn;
	@FindBy(xpath="//div[@class='modal-footer']//button[text()='Yes']")
	WebElement confirmYes;
	@FindBy(xpath="//div[@role='alert']//div[text()='Park updated successfully']")
	WebElement  parkSucessMsg;
	@FindBy(xpath="//div[@role='alert']//div[text()='Park deleted successfully']")
	WebElement deleteSuccessMsg;



	// Define function

	public String randomString() {

		String generatedString = RandomStringUtils.randomAlphabetic(5);
		createdParkName= "Park Auto" +  generatedString;
		return createdParkName;

	}
	public String randomnumeric()
	{
		String generatedInteger = RandomStringUtils.randomNumeric(6);
		return generatedInteger;
	}
	public String getCreatedParkName() {
		return createdParkName;
	}
	public void createParkClick()
	{    	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(createParkbtn)).click();

	}
	public void editCreatedPark(String newParkName, String newLocation, String newStreet1, String newStreet2,
			String newCity, String newDistrict, String newState, String newPincode) throws InterruptedException 
	{
		// Step 1: Locate the Edit button for the created park using createdParkName
		WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//td[text()='" + createdParkName + "']/following-sibling::td//button[contains(@class,'edit')]")
				));
		editButton.click();

		// Step 2: Fill in updated values using existing methods
		setparkName(newParkName);
		setLocation(newLocation);
		setStreetOne(newStreet1);
		setStreetTwo(newStreet2);
		city(newCity);
		district(newDistrict);
		state(newState);
		pincode(newPincode);

		scrollToBottom();

		// Step 3: Click Save
		createParkSaveClick();

		// Optional: Update the internal reference to the new name
		createdParkName = newParkName;
	}

	public void setparkName(String parkNameValid)
	{

		wait.until(ExpectedConditions.visibilityOf(parkName)).clear(); // Clear the field before entering a new value
		parkName.sendKeys(parkNameValid);
	}
	public void setLocation(String parkLocationValid)
	{

		wait.until(ExpectedConditions.visibilityOf(parklocation)).clear(); // Clear the field before entering a new value
		parklocation.sendKeys(parkLocationValid);
	}
	public void setStreetOne(String streetOneValid)
	{

		wait.until(ExpectedConditions.visibilityOf(streetone)).clear(); // Clear the field before entering a new value
		streetone.sendKeys(streetOneValid);
	}
	public void setStreetTwo(String streetTwoValid)
	{

		wait.until(ExpectedConditions.visibilityOf(streettwo)).clear(); // Clear the field before entering a new value
		streettwo.sendKeys(streetTwoValid);
	}
	public void city(String cityvalid)
	{

		wait.until(ExpectedConditions.visibilityOf(city)).clear(); // Clear the field before entering a new value
		city.sendKeys(cityvalid);
	}
	public void district(String districtvalid)
	{

		wait.until(ExpectedConditions.visibilityOf(district)).clear(); // Clear the field before entering a new value
		district.sendKeys(districtvalid);
	}
	public void state(String statevalid)
	{

		wait.until(ExpectedConditions.visibilityOf(state)).clear(); // Clear the field before entering a new value
		state.sendKeys(statevalid);
	}
	public void pincode(String pincodevalid)
	{

		wait.until(ExpectedConditions.visibilityOf(pincode)).clear(); // Clear the field before entering a new value
		pincode.sendKeys(pincodevalid);
	}
	public void scrollToBottom() throws InterruptedException {
	
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(2000);
		}
	


	public void createParkSaveClick()
	{    	
		wait.until(ExpectedConditions.elementToBeClickable(submitpark)).click();

	}

	public void deleteParkClick()
	{    	
		
		wait.until(ExpectedConditions.elementToBeClickable(deleteparkbtn)).click();

	}
	public WebElement editSuccess()
	{
		return parkSucessMsg; 
	}
	
	public WebElement deleteSuccess() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//div[@role='alert' and contains(@class,'Toastify__toast-body')]//div[contains(text(),'Park deleted successfully')]")
	    ));
	}
	public void clickEditButtonForPark(String parkName) {
	    boolean found = false;

	    while (!found) {
	        List<WebElement> parkCards = driver.findElements(By.xpath("//div[contains(@class,'card') and .//h5]"));

	        for (WebElement card : parkCards) {
	            String name = card.findElement(By.tagName("h5")).getText();
	            if (name.equalsIgnoreCase(parkName)) {
	                WebElement editBtn = card.findElement(By.xpath(".//a[contains(@href,'/dashboard/create-park/?id=')]"));
	                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editBtn);
	                wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
	                found = true;
	                break;
	            }
	        }

	        if (!found) {
	            List<WebElement> nextBtns = driver.findElements(By.xpath("//a[contains(@class,'page-link') and .//span[text()='Next']]"));
	            if (nextBtns.isEmpty() || !nextBtns.get(0).isEnabled()) {
	                throw new NoSuchElementException("Edit button for '" + parkName + "' not found.");
	            }
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtns.get(0));
	            wait.until(ExpectedConditions.stalenessOf(parkCards.get(0)));
	        }
	    }
	}

	public boolean isParkNameDisplayed(String createdParkName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		while (true) {
			// Refresh phase name elements for current page
			List<WebElement> currentPhaseNames = driver.findElements(By.xpath("//h5"));

			// Check if any phase name on this page matches
			boolean found = currentPhaseNames.stream()
					.anyMatch(p -> p.getText().equalsIgnoreCase(createdParkName));

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

	public void clickParkDeleteButton(String parkName) {
	    boolean parkFound = false;

	    while (!parkFound) {
	        List<WebElement> parkCards = driver.findElements(By.xpath("//div[contains(@class,'card') and .//h5]"));

	        for (WebElement card : parkCards) {
	            String cardText = card.getText().toLowerCase();

	            if (cardText.contains(parkName.toLowerCase())) {
	                WebElement deleteIcon = card.findElement(By.xpath(".//a[@class='text-primary' and contains(@href, 'park-list')]"));
	                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
	                wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();
	                parkFound = true;
	                break;
	            }
	        }

	        if (!parkFound) {
	            List<WebElement> nextButtonList = driver.findElements(
	                By.xpath("//ul[contains(@class,'pagination')]//a[contains(@class,'page-link') and .//span[text()='Next']]")
	            );

	            if (nextButtonList.isEmpty() || !nextButtonList.get(0).isEnabled()) {
	                throw new NoSuchElementException("Park with name '" + parkName + "' not found.");
	            }

	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButtonList.get(0));

	            wait.until(ExpectedConditions.stalenessOf(parkCards.get(0)));
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5")));
	        }
	    }
	}


}
