package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testCases.CreateParkAdmin_Testcases;

public class ChangePasswordPageForParkAdmin extends BasePage {
	
	WebDriverWait wait;

	public ChangePasswordPageForParkAdmin(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	
	//Locating all web elements in the Change Password page for Park Admin
	//****************************************************************
	@FindBy(xpath="//p[@class='card-text' and normalize-space()='Welcome ! Please login to continue.']") 
	WebElement loginPageHeader;
	@FindBy(xpath="//div[@class='card-title h5' and normalize-space()='Create new password']") 
	WebElement changePasswordPageHeader;
	@FindBy(xpath="//input[@placeholder='Enter current password']") WebElement currentPassword;
	@FindBy(xpath="//input[@placeholder='Enter new password']") WebElement newPassword;
	@FindBy(xpath="//input[@placeholder='Re-enter new password']") WebElement confirmnewPassword;
	@FindBy(xpath="//button[@type='submit']") WebElement resetbutton;
	@FindBy(xpath="//a[normalize-space()='Back to Login']") WebElement backtologin;
	@FindBy(xpath="//div[@class='text-danger password-error' and normalize-space()='Password is required']") WebElement passwdRequiredValidation;
	@FindBy(xpath="//div[normalize-space()='Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)']") 
	WebElement invalidPasswdFormat;
	@FindBy(xpath="//div[normalize-space()='Password length must be greater than 8 characters']") WebElement passwdLengthValidation;
	@FindBy(xpath="//div[@class='text-danger password-error' and normalize-space()='Passwords do not match']") WebElement passworddNotMatchingValidation;
	@FindBy(xpath="//div[@class='text-danger api-error' and normalize-space()='The current password you provided is incorrect']") 
	WebElement incorrectCurrentPasswd;
	@FindBy(xpath="//div[@class='text-danger api-error' and normalize-space(text())='Cannot set old password as new password']") 
	WebElement NewPasswordSameAsOldPasswordValidation;
	@FindBy(xpath="//div[normalize-space(text())='Password changed successfully']") WebElement passwdChangeSuccessMessage;
	@FindBy(xpath="//div[normalize-space(text())='Login Success']") WebElement parkAdminLoginSuccessWithNewPasswd;
	
	//Function to click the submit button for resetting password
	public void clickResetButton() {
	    wait.until(ExpectedConditions.elementToBeClickable(resetbutton)).click();
	}
	//Function to click Back to Login button
	public void clickResetButtonAndBackToLogin() {
	    wait.until(ExpectedConditions.elementToBeClickable(backtologin)).click();
	}
	
	//Function to locate the Login page and Change Password page label-headers
	public String getLoginPageHeader() {
	    wait.until(ExpectedConditions.visibilityOf(loginPageHeader));
	    return loginPageHeader.getText();
	}
	public String getChangePasswordPageHeader() {
	    wait.until(ExpectedConditions.visibilityOf(changePasswordPageHeader));
	    return changePasswordPageHeader.getText();
	}
	
	
	//Functions for inputting fields
    //******************************
	public void setCurrentPassword(String currentPasswd) {
	    wait.until(ExpectedConditions.visibilityOf(currentPassword));
	    currentPassword.clear();
	    currentPassword.sendKeys(currentPasswd);
	}
	public void setNewPassword(String newPasswd) {
	    wait.until(ExpectedConditions.visibilityOf(newPassword));
	    newPassword.clear();
	    newPassword.sendKeys(newPasswd);
	}
	public void setConfirmNewPassword(String confirmPasswd) {
	    wait.until(ExpectedConditions.visibilityOf(confirmnewPassword));
	    confirmnewPassword.clear();
	    confirmnewPassword.sendKeys(confirmPasswd);
	}
	public void clickBackToLogin() {
	    wait.until(ExpectedConditions.elementToBeClickable(backtologin)).click();
	}
	
	
	//Validation Functions for different fields
    //*****************************************
	public  String getPasswordRequiredvalidation() {
    	wait.until(ExpectedConditions.visibilityOf(passwdRequiredValidation));
    	return passwdRequiredValidation.getText();
    }
	public String getInvalidPasswdFormatValidation() {
	    wait.until(ExpectedConditions.visibilityOf(invalidPasswdFormat));
	    return invalidPasswdFormat.getText();
	}
	public String getPasswdLengthValidation() {
	    wait.until(ExpectedConditions.visibilityOf(passwdLengthValidation));
	    return passwdLengthValidation.getText();
	}
	public String getPassworddNotMatchingvalidation() {
	    wait.until(ExpectedConditions.visibilityOf(passworddNotMatchingValidation));
	    return passworddNotMatchingValidation.getText();
	}
	public String getIncorrectCurrentPasswdValidation() {
	    wait.until(ExpectedConditions.visibilityOf(incorrectCurrentPasswd));
	    return incorrectCurrentPasswd.getText();
	}
	public String getNewPasswordSameAsOldPasswordValidation() {
	    wait.until(ExpectedConditions.visibilityOf(NewPasswordSameAsOldPasswordValidation));
	    return NewPasswordSameAsOldPasswordValidation.getText();
	}
	public String getPasswordChangeSuccessMessage() {
	    wait.until(ExpectedConditions.visibilityOf(passwdChangeSuccessMessage));
	    return passwdChangeSuccessMessage.getText();
	}
	public String getParkAdminLoginSuccessWithNewPasswd() {
	    wait.until(ExpectedConditions.visibilityOf(parkAdminLoginSuccessWithNewPasswd));
	    return parkAdminLoginSuccessWithNewPasswd.getText();
	}

}
