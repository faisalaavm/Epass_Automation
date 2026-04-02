package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.PhaseadminChangePasswordScreenPage;
import testBase.BaseClass;
import utilities.Log;

public class PhaseAdminChangePasswordScreen_Testcases extends BaseClass {
	
@Test(priority=1, groups="Negative", description="TC_001_Verify Validation for Blank fields in Phase Admin Change Password Screen")
	public void Blankfields_in_PhaseAdmin_ChangePassword_Screen () throws InterruptedException {
	LoginPage lp=new LoginPage(driver);
	lp.setuserName(p.getProperty("phaseAdminUsername"));
	lp.setpassWord(p.getProperty("validpassword"));
	lp.setSubmit();
	PhaseadminChangePasswordScreenPage pcpsp=new PhaseadminChangePasswordScreenPage(driver);
	pcpsp.clickSubmitButton();
	String expectedMessage = "Password is required";
	String actualMessage = pcpsp.getBlankCurrentPasswordValidation();
	Assert.assertEquals(actualMessage, expectedMessage);
	String expectedMessage2 = "Password is required";
	String actualMessage2 = pcpsp.getBlankCurrentPasswordValidation();
	Assert.assertEquals(actualMessage2, expectedMessage2);
	String expectedMessage3 = "Password is required";
	String actualMessage3 = pcpsp.getBlankCurrentPasswordValidation();
	Assert.assertEquals(actualMessage3, expectedMessage3);
	Log.info("Blank fields validation in Phase Admin Change Password Screen is successful.");
	
	}
	
	@Test(priority=2, groups="Negative", description="TC_002_Verify Validations for new password field")
	public void NewPasswordFieldValidation() throws InterruptedException {
		PhaseadminChangePasswordScreenPage pcpsp=new PhaseadminChangePasswordScreenPage(driver);
		pcpsp.setNewPassword(p.getProperty("invalidpassword"));
		pcpsp.clickSubmitButton();
		String expectedMessage = "Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)";
		String actualMessage = pcpsp.getPasswordValidationMessage();
		Assert.assertEquals(actualMessage,expectedMessage);
		Log.info("New password field validation in Phase Admin Change Password Screen is successful.");
		
	}
	
	@Test(priority=3, groups="Negative", description="TC_003_Verify Validation for non matching passwords")
	public void NonMatchingPasswordsValidation() throws InterruptedException {
		PhaseadminChangePasswordScreenPage pcpsp=new PhaseadminChangePasswordScreenPage(driver);
		pcpsp.setNewPassword(p.getProperty("NewPassword"));
		pcpsp.setReEnterNewPassword(p.getProperty("invalidpassword"));
		pcpsp.clickSubmitButton();
		String expectedMessage = "Passwords do not match";
		String actualMessage = pcpsp.getPasswordsDoNotMatchMessage();
		Assert.assertEquals(actualMessage,expectedMessage);
		Log.info("Non-matching passwords validation in Phase Admin Change Password Screen is successful.");
		
	}
	
	@Test(priority=4, groups="Negative", description="TC_004_Verify when user enters invalid current password")
	public void CurrentPasswordFieldValidation() throws InterruptedException {
		PhaseadminChangePasswordScreenPage pcpsp=new PhaseadminChangePasswordScreenPage(driver);
		pcpsp.setCurrentPassword(p.getProperty("invalidpassword"));
		pcpsp.setNewPassword(p.getProperty("NewPassword"));
		pcpsp.setReEnterNewPassword(p.getProperty("NewPassword"));
		pcpsp.clickSubmitButton();
		String expectedMessage = "The current password you provided is incorrect";
		String actualMessage = pcpsp.getInvalidCurrentPasswordValidationMessage();
		Assert.assertEquals(actualMessage,expectedMessage);
		Log.info("Current password field validation in Phase Admin Change Password Screen is successful.");
			}
	
	@Test(priority=5, groups="Negative", description="TC_005_Verify when user enters same old password as new password")
	public void SameOldPasswordAsNewPassword() throws InterruptedException {
		PhaseadminChangePasswordScreenPage pcpsp=new PhaseadminChangePasswordScreenPage(driver);
		pcpsp.setCurrentPassword(p.getProperty("oldpassword"));
		pcpsp.setNewPassword(p.getProperty("oldpassword"));
		pcpsp.setReEnterNewPassword(p.getProperty("oldpassword"));
		pcpsp.clickSubmitButton();
		String expectedMessage = "Cannot set old password as new password";
		String actualMessage = pcpsp.getCannotSetoldPasswordAsNewPasswordMessage();
		Assert.assertEquals(actualMessage,expectedMessage);
		Log.info("Same old password as new password validation in Phase Admin Change Password Screen is successful.");
			}
	
	@Test(priority=6, groups="Positive", description="TC_006_Verify successful change of password in Phase Admin Change Password Screen")
	public void SuccessfulChangeOfPassword() throws InterruptedException {
		PhaseadminChangePasswordScreenPage pcpsp=new PhaseadminChangePasswordScreenPage(driver);
		pcpsp.setCurrentPassword(p.getProperty("oldpassword"));
		pcpsp.setNewPassword(p.getProperty("NewPassword"));
		pcpsp.setReEnterNewPassword(p.getProperty("NewPassword"));
		pcpsp.clickSubmitButton();
		String expectedMessage = "Password changed successfully";
		String actualMessage = pcpsp.getSuccessMessage();
		Assert.assertEquals(actualMessage,expectedMessage);
		Log.info("Successful change of password in Phase Admin Change Password Screen is successful.");}
	
	@Test(priority=7, groups="Positive", description="TC_007_Verify successful login with new password")
	public void SuccessfulLoginWithNewPassword() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.setuserName(p.getProperty("phaseAdminUsername"));
		lp.setpassWord(p.getProperty("NewPassword"));
		lp.setSubmit();
		String actualUrl=p.getProperty("dashboardurl");
		Thread.sleep(2000);
		String expectedUrl=lp.getUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		Log.info("Login test completed successfully");
		Thread.sleep(5000);
				
	}
	
}
