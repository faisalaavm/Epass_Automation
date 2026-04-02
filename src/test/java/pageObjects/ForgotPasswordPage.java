package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage extends BasePage {

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//a[normalize-space()='Back to Login']")
	WebElement backToLogin;
	@FindBy(xpath="//input[contains(@placeholder,'Enter email id')]")
	WebElement emailInputField;
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitButton;
	@FindBy(xpath="//div[contains(text(),'Email id is required')]")
	WebElement emailRequiredMessage; 
	@FindBy(xpath="//div[normalize-space()='Please enter a valid email id']")
	WebElement invalidEmailMessage; 
	@FindBy(xpath="//div[contains(text(),'Invalid Email ID. This is not a Registered Email.')]")
	WebElement notRegisteredEmailMessage;
	@FindBy(xpath="//div[contains(text(),'Password reset mail has been sent to your register')]")
	WebElement passwordResetSuccessMessage; 
	
	public void clickSubmit() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		submitButton.click();	    
	}
	
	public void clickBackToLogin() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(backToLogin));
		backToLogin.click();
	}
	public void enterEmail(String email) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(emailInputField));
		emailInputField.clear();
		emailInputField.sendKeys(email);
	}
	public String getCurrentUrl() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return driver.getCurrentUrl();
	}

	
	public String getInvalidEmailMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(invalidEmailMessage));
		return invalidEmailMessage.getText();}

	public String getNotRegisteredEmailMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(notRegisteredEmailMessage));
		return notRegisteredEmailMessage.getText();
	}
	public String getPasswordResetSuccessMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(passwordResetSuccessMessage));
		return passwordResetSuccessMessage.getText();}

	public String getEmailRequiredMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(emailRequiredMessage));
		return emailRequiredMessage.getText();
	}

}
