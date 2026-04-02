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

public class ParkCreation extends BasePage {

	public ParkCreation(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy (xpath ="//input[@placeholder='Enter park name']")
	WebElement parkNameInput;
	@FindBy (xpath ="//input[@placeholder='Enter location of the park']")
	WebElement parkLocationInput;
	@FindBy (xpath ="//input[@placeholder='Enter the primary street address of the park ']")
	WebElement parkStreetAddressInput;
	@FindBy (xpath ="//input[@placeholder='Enter the additional street address of the park ']")
	WebElement parkAdditionalStreetAddressInput;
	@FindBy (xpath ="//input[@placeholder='Enter the city where the park is situated ']")
	WebElement parkCityInput;
	@FindBy (xpath ="//input[@placeholder='Enter the district where the park is situated']")
	WebElement parkDistrictInput;
	@FindBy (xpath ="//input[@placeholder='Enter the state where the park is situated']")
	WebElement parkStateInput;
	@FindBy (xpath ="//input[@placeholder=\"Enter the pin code of the park's location\"]")
	WebElement parkPinCodeInput;
	@FindBy(xpath="//button[@type='submit']")
	WebElement createParkButton;
	@FindBy(xpath="//div[normalize-space()='Park name is required']")
	WebElement ParkNameValidation;
	@FindBy(xpath="//div[normalize-space()='Park name must start with a letter and contain only alphanumeric characters']")
	WebElement ParkNameValidation2;
	@FindBy(xpath="//div[normalize-space()='Location must start with a letter and contain only alphanumeric characters']")
	WebElement LocationNameValidation;
	@FindBy(xpath="//div[normalize-space()='City must contain only letters']")
	WebElement ParkCityValidation;
	@FindBy(xpath="//div[normalize-space()='District must contain only letters']")
	WebElement ParkDistrictValidation;
	@FindBy(xpath="//div[normalize-space()='Pin code must contain only numbers']")
	WebElement ParkPinValidation;
	@FindBy(xpath="//div[contains(text(),'Park created successfully')]")
	WebElement ParkCreationSuccessMessage;
	@FindBy(xpath="//span[normalize-space()='Go to parks']")
	WebElement goToParksButton;
	@FindBy(xpath="//span[normalize-space()='Create Park']")
	WebElement createParkButton2;
	@FindBy(xpath="//div[contains(text(),'Park with same name exists')]")
	WebElement ParkNameExistsValidation;
	@FindBy(xpath="//span[normalize-space()='Go back']")
	WebElement GoBackButton;
	@FindBy(xpath="//h4[@class='main-title mb-0']")
	WebElement ParklistTitle;
	@FindBy(xpath="//a[normalize-space()='2']")
	WebElement paginationButton;
	@FindBy(xpath="//h5[normalize-space()='Test Park']")
	WebElement clickViewIcon;
	@FindBy(xpath="//div[@class='avatar online']//img[contains(@src, 'img1')]")
	WebElement getProfileIcon;
	@FindBy(xpath="//div[@class='dropdown-menu-body']//a[normalize-space()='Log Out']")
	WebElement logOutButton;

	public String getUrl() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return driver.getCurrentUrl();
	}
	public void setparkName( String parkName){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parkNameInput.clear();
		parkNameInput.sendKeys(parkName);	
	}
	public void setparkLocation( String parkLocation){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parkLocationInput.clear();
		parkLocationInput.sendKeys(parkLocation);	
	}
	public void setparkStreetAddress( String parkStreetAddress){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parkStreetAddressInput.clear();
		parkStreetAddressInput.sendKeys(parkStreetAddress);	
	}
	public void setparkAdditionalStreetAddress( String parkAdditionalStreetAddress){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parkAdditionalStreetAddressInput.clear();
		parkAdditionalStreetAddressInput.sendKeys(parkAdditionalStreetAddress);	
	}
	public void setparkCity( String parkCity){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parkCityInput.clear();
		parkCityInput.sendKeys(parkCity);	
	}
	public void setparkDistrict( String parkDistrict){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parkDistrictInput.clear();
		parkDistrictInput.sendKeys(parkDistrict);	
	}
	public void setparkState( String parkState){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parkStateInput.clear();
		parkStateInput.sendKeys(parkState);	
	}
	public void setparkPinCode( String parkPinCode){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		parkPinCodeInput.clear();
		parkPinCodeInput.sendKeys(parkPinCode);	
	}
	public String validation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return ParkNameValidation.getText();	
	}
	public String validation2() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return ParkNameValidation2.getText();	
	}
	public void setCreateParkButton() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		    wait.until(ExpectedConditions.elementToBeClickable(createParkButton));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createParkButton);
		    Thread.sleep(1000); // Optional: let UI settle
		createParkButton.click();	
		
	}
	public void GoToParksButton() throws InterruptedException {
			Thread.sleep(3000);
		goToParksButton.click();
	}
	public void CreateParkButton2() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		createParkButton2.click();
		Thread.sleep(5000);
	}
	//	function for scroll to bottom	
	public void scrollToBottom() throws InterruptedException {
		// Create an instance of JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Execute JavaScript to scroll to the bottom of the page
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(2000);
	}
	public void scrollToTop() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);"); // Scroll to the top of the page
		Thread.sleep(2000);
	}
	public String validation3() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return LocationNameValidation.getText();	
	}
	public String validation4() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return ParkCityValidation.getText();	
	}
	public String validation5() 
	
	{WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ParkDistrictValidation));
		return ParkDistrictValidation.getText();	
	}
	public String validation6() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return ParkPinValidation.getText();	
	}
	public String get_ParkCreationSuccessMessage() 
		{WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ParkCreationSuccessMessage));
		return ParkCreationSuccessMessage.getText();	
		
	
	}

	public String ParkNameExistsValidation() throws InterruptedException 
	{WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ParkNameExistsValidation));
	return ParkNameExistsValidation.getText();
	}
	public String GoBackButton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		GoBackButton.click();
		return null;
	}
	public String ParklistTitle() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ParklistTitle));
		return ParklistTitle.getText();
	}
	public void clickPaginationButton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(paginationButton));
		paginationButton.click();
		Thread.sleep(2000);
	}
	public String clickViewIcon() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(clickViewIcon));
		clickViewIcon.click();
		Thread.sleep(2000);
		return null;
	}
	
	public String getProfileIcon() throws InterruptedException {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(getProfileIcon));
		getProfileIcon.click();
		Thread.sleep(5000);
		return null;
	}
	public String logOutButton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOf(logOutButton));
		logOutButton.click();
		Thread.sleep(5000);
		return null;
	}
	public boolean isParkNameDisplayed(String parkName) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    while (true) {
	        // Refresh park name elements for current page
	        List<WebElement> currentParkNames = driver.findElements(By.xpath("//h5"));

	        // Check if any park name on this page matches
	        boolean found = currentParkNames.stream()
	            .anyMatch(p -> p.getText().equalsIgnoreCase(parkName));

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
	        if (!currentParkNames.isEmpty()) {
	            wait.until(ExpectedConditions.stalenessOf(currentParkNames.get(0)));
	        }

	        // Optional: Wait until at least one new <h5> element is present
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5")));
	    }
	}
	public String randomString() {
	    String generatedString = RandomStringUtils.randomAlphabetic(5).toLowerCase();
	    return "Park" + generatedString;
	}
	public class YourPage {
	    WebDriver driver;

	    By logo = By.xpath("(//img[@alt='logo'])[1]\r\n"
	    		+ "");

	    public YourPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public boolean isLogoDisplayed() {
	        return driver.findElement(logo).isDisplayed();
	    }
	}
	public class YourParkPage {
	    WebDriver driver;

	    By headertext = By.xpath("//div[@class='text-muted' and text()='Provide the necessary information to create a park.']\r\n"
	    		+ "");

	    public YourParkPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public boolean isTextDisplayed() {
	        return driver.findElement(headertext).isDisplayed();
	    }
	}
	// TODO Auto-generated method stub
		
	
	}
	
