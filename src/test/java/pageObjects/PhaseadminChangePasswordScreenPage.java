package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PhaseadminChangePasswordScreenPage extends BasePage{

	public PhaseadminChangePasswordScreenPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitButton;
	@FindBy(xpath="//input[@placeholder='Enter current password']")
	WebElement currentPassword;
	@FindBy(xpath="//input[@placeholder='Enter new password']")
	WebElement newPassword;
	@FindBy(xpath="//input[@placeholder='Re-enter new password']")
	WebElement reEnterNewPassword;
	@FindBy(xpath="//a[normalize-space()='Back to Login']")
	WebElement backToLoginLink;
	@FindBy(xpath="//div[contains(text(),'Password is required')][1]")
	WebElement blankcurrentpasswordvalidation;
	@FindBy(xpath="//div[contains(text(),'Password is required')][2]")
	WebElement blanknewpasswordvalidation;
	@FindBy(xpath="//div[contains(text(),'Password is required')][3]")
	WebElement blankreenternewpasswordvalidation;
	@FindBy(xpath="//div[contains(text(),'Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)')]")
	WebElement passwordValidationMessage;
	@FindBy(xpath="//div[contains(text(),'Passwords do not match')]")
	WebElement passwordsDoNotMatchMessage;
	@FindBy(xpath="//div[contains(text(),'The current password you provided is incorrect')]")
	WebElement invalidCurrentPasswordValidationMessage; 
	@FindBy(xpath="//div[contains(text(),'Cannot set old password as new password')]")
	WebElement cannotSetOldPasswordAsNewPasswordMessage;
	@FindBy(xpath="//div[normalize-space(text())='Password changed successfully']")
	WebElement passwordChangedSuccessfullyMessage;



	public void clickSubmitButton() {
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		submitButton.click();
	}
	public void setCurrentPassword(String currentPass) {
		wait.until(ExpectedConditions.visibilityOf(currentPassword));
		currentPassword.clear(); 
		currentPassword.sendKeys(currentPass);
	}
	public void setNewPassword(String newPass) {
		wait.until(ExpectedConditions.visibilityOf(newPassword));
		newPassword.clear();
		newPassword.sendKeys(newPass);
	}
	public void setReEnterNewPassword(String reEnterNewPass) {
		wait.until(ExpectedConditions.visibilityOf(reEnterNewPassword));
		reEnterNewPassword.clear(); 
		reEnterNewPassword.sendKeys(reEnterNewPass);
	}
	public void clickBackToLoginLink() {
		wait.until(ExpectedConditions.visibilityOf(backToLoginLink));
		backToLoginLink.click();
	}
	public String getBlankCurrentPasswordValidation() {
		wait.until(ExpectedConditions.visibilityOf(blankcurrentpasswordvalidation));
		return blankcurrentpasswordvalidation.getText();
	}
	public String getBlankNewPasswordValidation() {
		wait.until(ExpectedConditions.visibilityOf(blanknewpasswordvalidation));
		return blanknewpasswordvalidation.getText();
	}
	public String getBlankReEnterNewPasswordValidation() {
		wait.until(ExpectedConditions.visibilityOf(blankreenternewpasswordvalidation));
		return blankreenternewpasswordvalidation.getText();
	}
	public String getPasswordValidationMessage() {
		wait.until(ExpectedConditions.visibilityOf(passwordValidationMessage));
		return passwordValidationMessage.getText();
	}
	public String getPasswordsDoNotMatchMessage() {
		wait.until(ExpectedConditions.visibilityOf(passwordsDoNotMatchMessage));
		return passwordsDoNotMatchMessage.getText();
	}
	public String getInvalidCurrentPasswordValidationMessage() {
		wait.until(ExpectedConditions.visibilityOf(invalidCurrentPasswordValidationMessage));
		return invalidCurrentPasswordValidationMessage.getText();
	}

	public String getCannotSetoldPasswordAsNewPasswordMessage() {
		wait.until(ExpectedConditions.visibilityOf(cannotSetOldPasswordAsNewPasswordMessage));
		return cannotSetOldPasswordAsNewPasswordMessage.getText();}

	public String getSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(passwordChangedSuccessfullyMessage));
		return passwordChangedSuccessfullyMessage.getText();
	}

	public String getUrl() throws InterruptedException {
		return driver.getCurrentUrl();	
	}




}
