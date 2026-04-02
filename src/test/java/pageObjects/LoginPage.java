package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
			}
	
	@FindBy(xpath="//input[@placeholder='Enter username']")
	WebElement userName;
	@FindBy(xpath="//input[@placeholder='Enter password']")
	WebElement Password;
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitButton;
	@FindBy(xpath="//div[@class='text-danger api-error']")
	WebElement validation;
	@FindBy(xpath="//div[contains(text(),'Username is required')]")
	WebElement blankusernamevalidation;
	@FindBy(xpath="//div[contains(text(),'Password is required')]")
	WebElement blankpasswordvalidation;
	@FindBy(xpath="//a[normalize-space()='Forgot password?']")
	WebElement forgotPasswordLink;
	@FindBy(xpath="//a[normalize-space()='Back to Login']")
	WebElement backToLogin;
	@FindBy(xpath="//div[@class='avatar online']//img")
	WebElement ProfileIcon; // This is the xpath for the logout icon in the dropdown menu
	@FindBy(xpath="//div[@class='dropdown-menu-body']//a[normalize-space()='Log Out']")
	WebElement logoutIcon; 
	//div[@class='text-danger']


	public void setuserName( String username) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(userName));
		userName.clear();
		userName.sendKeys(username);// Adding a sleep to ensure the element is ready before sending keys
	}

	public void setpassWord( String password) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Password));
		Password.clear();
		Password.sendKeys(password);	
	}

	public void setSubmit() throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		    wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
		    Thread.sleep(1000);
		     // Optional: let UI settle
		    submitButton.click();
		    Thread.sleep(3000);
	}

	public String getUrl() throws InterruptedException {
		return driver.getCurrentUrl();	
	}

	public String checkValidation() {
		return validation.getText();	
	}

	public String BlankUsernameValidation() {
		return blankusernamevalidation.getText();	
	}

	public String BlankPasswordValidation() {
		return blankpasswordvalidation.getText();	
	}

	public void clickForgotPassword() throws InterruptedException {
		forgotPasswordLink.click();	
	}

	public void backLogin() {
		backToLogin.click();	
	}
	
	public void ProfileIconClick() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ProfileIcon));
		ProfileIcon.click();	
	}
	
	public void clickLogout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		logoutIcon.click();	
	}


}
