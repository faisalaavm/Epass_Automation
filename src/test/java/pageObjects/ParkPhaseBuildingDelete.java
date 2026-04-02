package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParkPhaseBuildingDelete extends BasePage {

	public ParkPhaseBuildingDelete(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath ="//span[normalize-space()='Go to parks']") 
	WebElement btn_GoToPark; 
	@FindBy(xpath = "//span[contains(text(),'›')]")
	WebElement btn_Next;
	@FindBy(xpath = "//div[@id='root']//div[1]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//a[2]//*[name()='svg']")
	WebElement edit_click;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveEdit;
	@FindBy(xpath="//div[contains(text(),'Park updated successfully')]")
	WebElement alert;
	@FindBy(xpath="//div[contains(text(),'Park with same name exists')]")
	WebElement alert2;
	@FindBy(xpath= "//input[@placeholder='Enter park name']") 
	WebElement txt_parkName;
	@FindBy(xpath= "//button[normalize-space()='Yes']") 
	WebElement deleteConfirm;
	@FindBy(xpath="//div[contains(text(),'Park deleted successfully')]")
	WebElement deleteAlert;

	public boolean deletePark(String parkName) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    while (true) {
	        try {
	            // Check if park is present on the current page
	            By tile = By.xpath("//h5[text()='" + parkName + "']");
	            WebElement parkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(tile));

	            // Find the delete icon next to it
	            By deleteIconBy = By.xpath("//h5[text()='" + parkName + "']/following::a[contains(@href,'park-list')]");
	            WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(deleteIconBy));

	            // Scroll into view and click safely
	            js.executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
	            try {
	                deleteIcon.click();
	            } catch (ElementClickInterceptedException e) {
	                js.executeScript("arguments[0].click();", deleteIcon);
	            }
	            return true;

	        } catch (TimeoutException e) {
	            // Not found → go to next page if possible
	            if (btn_Next.isEnabled()) {
	                btn_Next.click();
	                wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.tagName("body"))));
	            } else {
	                return false; // no more pages
	            }
	        }
	    }
	}

	public void clickSaveEdit() {
		saveEdit.click();
	}
	public String getAlertMsg() throws InterruptedException {
		Thread.sleep(3000);
		return alert.getText();	
	}
	public String getLoginPageUrl() throws InterruptedException {
		Thread.sleep(5000);
		return driver.getCurrentUrl();	
	}
	public String getAlreadyExistalertMsg() throws InterruptedException {
		Thread.sleep(3000);
		return alert2.getText();	
	}
	public void confirmdelete() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(deleteConfirm));

	    try {
	        ele.click();
	    } catch (ElementClickInterceptedException e) {
	        js.executeScript("arguments[0].click();", ele);
	    }
	}
	
	public String getDeleteAlertMsg() throws InterruptedException {
		
		return deleteAlert.getText();	
	}

	public void editParkName(String park_name1) throws InterruptedException {
		Thread.sleep(3000);
		txt_parkName.clear();
		txt_parkName.sendKeys(park_name1);;   ///click create park button
	}

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
	
	public void confirmDelete() {
		deleteConfirm.click();
	}
	
	

}
