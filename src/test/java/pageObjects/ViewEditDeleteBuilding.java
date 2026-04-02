package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewEditDeleteBuilding extends BasePage {
	String title="Infopark Cherthala Phase 1";
	private String createdBuildingName;

	public ViewEditDeleteBuilding(WebDriver driver) {
		super(driver);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		// TODO Auto-generated constructor stub
	}
	// Initialize web element path
	@FindBy(xpath="//a[normalize-space()='View all phases']")
	WebElement viewAllPhase;
	@FindBy(xpath="//h5[text()='Infopark Cherthala Phase 1']")
	WebElement phasename;
	@FindBy(xpath="//h4[@class='main-title mb-0']")
	WebElement phaseTitle;
	@FindBy(xpath="//div[@class='py-1']//h4[@class='main-title mb-0' and normalize-space()='Infopark Cherthala Phase 1']")
	WebElement phaseHeader;
	@FindBy(xpath="//div[@class='card-one card']//h6[text()='Building Logs']/ancestor::div[contains(@class,'card')]//a[@href='/dashboard/building-list/?gen']")
	WebElement viewAllBuilding;
	@FindBy(xpath="//div[@class='py-1']//h4[@class='main-title mb-0' and text()='Building List']")
	WebElement buildingHeader;
	@FindBy(xpath="//a[@href='/dashboard/create-building' and contains(@class, 'btn-primary')]")
	WebElement createbuildingButton;
	@FindBy(xpath="//h4[@class='main-title mb-0' and text()='Create Building']")
	WebElement createbuildingheader;
	@FindBy(xpath="//input[@placeholder='Enter building name']")
	WebElement buildingName;
	@FindBy(xpath="//input[@placeholder='Enter number of floors in building ']")
	WebElement numberOfFloor;
	@FindBy(xpath="//label[contains(text(), 'IT Park')]/following-sibling::select/option[3]")
	WebElement itparkDropdown;
	@FindBy(xpath="//label[contains(text(), 'Phase')]/following-sibling::select/option[2]")
	WebElement phaseDropdown;
	@FindBy(xpath="//button[contains(@class, 'btn-primary') and @type='submit']")
	WebElement saveBuildingbtn;
	@FindBy(xpath="//div[contains(@class,'text-primary')]/a[1]")
	WebElement viewIcon;
	@FindBy(xpath="//div[contains(@class,'text-primary')]/a[1]")
	WebElement editIcon;
	@FindBy(xpath="//div[contains(@class,'text-primary')]/a[1]")
	WebElement deleteIcon;
	@FindBy(xpath="//div[@role='alert']//div[contains(text(), 'Building updated successfully')]")
	WebElement editSuccess;
	@FindBy(xpath="//div[@role='alert']//div[contains(text(), 'Building deleted successfully')]")
	WebElement deleteSuccess;

	// Method declaration

	public void viewAllPhaseClick() throws InterruptedException
	{   Thread.sleep(5000);
		scrollToBottom();
		viewAllPhase.click();

	}
	public void selectPhase() 
	{    	
		WebElement eyeIcon = driver.findElement(By.xpath("//h5[normalize-space()='" + title + "']/ancestor::div[contains(@class,'card-wrap')]/following-sibling::div//a[@href='/dashboard/phase-details/?list']"));
		eyeIcon.click();
		//return phasename.getText(); 		    	

	}
	public void scrollToBottom() throws InterruptedException {
		// Create an instance of JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Execute JavaScript to scroll to the bottom of the page
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(2000);
	}

	public String phaseTitleName()
	{			 
		return phaseTitle.getText();
	}
	public String phaseHeaderName()
	{			 
		return phaseHeader.getText();
	}
	public void viewAllBuildingClick() throws InterruptedException
	{    	
		Thread.sleep(5000);
		
		scrollToBottom();
		wait.until(ExpectedConditions.elementToBeClickable(viewAllBuilding)).click();

	}
	public String buildingHeaderName()
	{			 
		return buildingHeader.getText();
	}
	public void createBuildingbtnClick()
	{    	
		wait.until(ExpectedConditions.elementToBeClickable(createbuildingButton)).click();

	}
	public String createBuildingHeaderName()
	{			 
		return createbuildingheader.getText();
	}
	public String randomString() {

		String generatedString = RandomStringUtils.randomAlphabetic(5);
		createdBuildingName= "Building Auto" +  generatedString;
		return createdBuildingName;

	}
	public String randomnumeric()
	{
		String generatedInteger = RandomStringUtils.randomNumeric(2);
		return generatedInteger;
	}
	public WebElement viewTooltipFind()
	{

		//WebElement viewIcon = driver.findElement(By.xpath(""));
		Actions action = new Actions(driver);
		action.moveToElement(viewIcon).perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));			
		WebElement viewTooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tooltip"))); // adjust class
		return viewTooltip;

	}
	public WebElement editTooltipFind()
	{
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// Edit icon
		WebElement editIcon = driver.findElement(By.xpath("//div[contains(@class,'text-primary')]/a[2]"));
		action.moveToElement(editIcon).perform();
		WebElement editTooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tooltip")));
		return editTooltip;
	}
	public WebElement deleteTooltipFind()
	{
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// Delete icon
		WebElement deleteIcon = driver.findElement(By.xpath("//div[contains(@class,'text-primary')]/a[3]"));
		action.moveToElement(deleteIcon).perform();
		WebElement deleteTooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tooltip")));
		return deleteTooltip;
	}
	public void setBuildingName(String buildingNameValid)
	{

		wait.until(ExpectedConditions.visibilityOf(buildingName)).clear();
		wait.until(ExpectedConditions.visibilityOf(buildingName)).sendKeys(buildingNameValid);
	}
	public void setFloors(String buildingFloorValid)
	{

		wait.until(ExpectedConditions.visibilityOf(numberOfFloor)).clear(); // Clear the field before entering a new value
		wait.until(ExpectedConditions.visibilityOf(numberOfFloor)).sendKeys(buildingFloorValid);
	}
	public boolean isBuildingNameDisplayed(String createdBuildingName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		while (true) {
			// Refresh building name elements for current page
			List<WebElement> currentbuildingNames = driver.findElements(By.xpath("//h5"));

			// Check if any building name on this page matches
			boolean found = currentbuildingNames.stream()
					.anyMatch(p -> p.getText().equalsIgnoreCase(createdBuildingName));

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
			if (!currentbuildingNames.isEmpty()) {
				wait.until(ExpectedConditions.stalenessOf(currentbuildingNames.get(0)));
			}

			// Optional: Wait until at least one new <h5> element is present
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5")));
		}
	}
	public void saveBuildingbtnClick()
	{    	
		wait.until(ExpectedConditions.elementToBeClickable(saveBuildingbtn)).click();

	}
	public void clickEditButtonForBuilding(String buildingName) {

		boolean found = false;

		while (!found) {
			List<WebElement> buildingCards = driver.findElements(By.xpath("//div[contains(@class,'card') and .//h5]"));

			for (WebElement card : buildingCards) {
				String name = card.findElement(By.tagName("h5")).getText();
				if (name.equalsIgnoreCase(buildingName)) {
					WebElement editBtn = card.findElement(By.xpath(".//a[starts-with(@href, '/dashboard/create-building/?id=')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editBtn);
					wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
					found = true;
					break;
				}
			}

			if (!found) {
				List<WebElement> nextBtns = driver.findElements(By.xpath("//a[contains(@class,'page-link') and .//span[text()='Next']]"));
				if (nextBtns.isEmpty() || !nextBtns.get(0).isEnabled()) {
					throw new NoSuchElementException("Edit button for '" + buildingName + "' not found.");
				}
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtns.get(0));
				wait.until(ExpectedConditions.stalenessOf(buildingCards.get(0)));
			}
		}
	}
	public String editToasterMessage()
	{
		return editSuccess.getText();
	}

	public void clickBuildingDeleteButton(String buildingName) {
		System.out.println("Looking for name: " + buildingName);
		boolean buildingFound = false;

		while (!buildingFound) {
			List<WebElement> buildingCards = driver.findElements(By.xpath("//div[contains(@class,'card') and .//h5]"));

			for (WebElement card : buildingCards) {
				String cardText = card.getText().toLowerCase();

				if (cardText.contains(buildingName.toLowerCase())) {
					WebElement deleteIcon = card.findElement(By.xpath(".//a[contains(@href, '/dashboard/building-list')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", deleteIcon);
					wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();

					// ✅ Handle Modal Inside Here
					WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//div[@role='dialog' and contains(@class,'modal') and contains(@class,'show')]")
							));

					WebElement yesBtn = modal.findElement(By.xpath(".//button[normalize-space(text())='Yes']"));
					wait.until(ExpectedConditions.elementToBeClickable(yesBtn)).click();

					buildingFound = true;
					break;
				}
			}

			if (!buildingFound) {
				List<WebElement> nextButtonList = driver.findElements(
						By.xpath("//ul[contains(@class,'pagination')]//a[contains(@class,'page-link') and .//span[text()='Next']]")
						);

				if (nextButtonList.isEmpty() || !nextButtonList.get(0).isEnabled()) {
					throw new NoSuchElementException("Building with name '" + buildingName + "' not found.");
				}

				WebElement nextBtn = nextButtonList.get(0);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextBtn);
				wait.until(ExpectedConditions.stalenessOf(buildingCards.get(0)));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5")));
			}
		}
	}		
	public WebElement deleteSuccess() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@role='alert' and contains(@class,'Toastify__toast-body')]//div[contains(text(),'Building deleted successfully')]")
				));
	}


}
