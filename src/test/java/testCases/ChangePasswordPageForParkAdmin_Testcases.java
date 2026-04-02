package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ChangePasswordPageForParkAdmin;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class ChangePasswordPageForParkAdmin_Testcases extends BaseClass {

	String createdusername;
	
	@Test(priority=0,groups="Positive",enabled=true, description="TC_001_Login as Park Admin for the first time and verify change password page is displayed")
	public void parkAdminlogin_for_the_firstTime() throws InterruptedException
	{
		createdusername= CreateParkAdmin_Testcases.newusername;
		LoginPage lp2=new LoginPage(driver);
		lp2.setuserName(createdusername);
		lp2.setpassWord(p.getProperty("parkpassword"));
		lp2.setSubmit();
		ChangePasswordPageForParkAdmin cppa=new ChangePasswordPageForParkAdmin(driver);
		String Expectedmsg="Create new password";
		String Actualmsg=cppa.getChangePasswordPageHeader();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=1,groups="Positive",enabled=true, description="TC_002_Click the Back to Login button and verify navigation to login page")
	public void ClickTo_BackToLogin_button() throws InterruptedException
	{
		ChangePasswordPageForParkAdmin cppa=new ChangePasswordPageForParkAdmin(driver);
		cppa.clickBackToLogin();
		String Expectedmsg="Welcome ! Please login to continue.";
		String Actualmsg=cppa.getLoginPageHeader();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
	}
	
	@Test(priority=2,groups="Negative",enabled=true, description="TC_003_Submit password form with blank values and verify validation message")
	public void submit_password_with_blankvalues() throws InterruptedException
	{
		parkAdminlogin_for_the_firstTime();
		ChangePasswordPageForParkAdmin cppa=new ChangePasswordPageForParkAdmin(driver);
		cppa.clickResetButton();
		String Expectedmsg="Password is required";
		String Actualmsg=cppa.getPasswordRequiredvalidation();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
//		Thread.sleep(2000);
	}
	
	@Test(priority=3,groups="Negative",enabled=true, description="TC_004_Submit password with invalid format and verify validation message")
	public void submit_password_with_invalidFormat() throws InterruptedException
	{
		ChangePasswordPageForParkAdmin cppa=new ChangePasswordPageForParkAdmin(driver);
		cppa.setCurrentPassword("Fgfgfh");
		cppa.setNewPassword(p.getProperty("invalidvalue1"));
		String Expectedmsg="Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)";
		String Actualmsg=cppa.getInvalidPasswdFormatValidation();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
//		Thread.sleep(2000);
	}
	
	@Test(priority=4,groups="Negative",enabled=true, description="TC_005_Submit password with less than eight characters and verify validation message")
	public void submit_password_with_lessThanEightCharacters() throws InterruptedException
	{
		ChangePasswordPageForParkAdmin cppa=new ChangePasswordPageForParkAdmin(driver);
		
		cppa.setNewPassword(p.getProperty("passwdlessthan8"));
		String Expectedmsg="Password length must be greater than 8 characters";
		String Actualmsg=cppa.getPasswdLengthValidation();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
//		Thread.sleep(2000);
	}
	
	@Test(priority=5,groups="Negative",enabled=true, description="TC_006_Submit password with non-matching new and confirm password fields and verify validation message")
	public void submit_password_with_notMatchingValues() throws InterruptedException
	{
		ChangePasswordPageForParkAdmin cppa=new ChangePasswordPageForParkAdmin(driver);
		cppa.setNewPassword(p.getProperty("parkAdminNewPasswd"));
		cppa.setConfirmNewPassword(p.getProperty("invalidvalue1"));
		String Expectedmsg="Passwords do not match";
		String Actualmsg=cppa.getPassworddNotMatchingvalidation();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
//		Thread.sleep(2000);
	}	
	
	@Test(priority=6,groups="Negative",enabled=true, description="TC_007_Submit password with incorrect current password and verify validation message")
	public void submit_password_with_incorrectCurrentPassword() throws InterruptedException
	{
		ChangePasswordPageForParkAdmin cppa=new ChangePasswordPageForParkAdmin(driver);
		cppa.setCurrentPassword(p.getProperty("invalidvalue1"));
		cppa.setNewPassword(p.getProperty("parkAdminNewPasswd"));
		cppa.setConfirmNewPassword(p.getProperty("parkAdminNewPasswd"));
		cppa.clickResetButton();
		String Expectedmsg="The current password you provided is incorrect";
		String Actualmsg=cppa.getIncorrectCurrentPasswdValidation();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
//		Thread.sleep(2000);
	}
	
	@Test(priority=7,groups="Negative",enabled=true, description="TC_008_Submit password with new password same as old password and verify validation message")
	public void submit_password_with_NewPasswordSameAsOldPassword() throws InterruptedException
	{
		ChangePasswordPageForParkAdmin cppa=new ChangePasswordPageForParkAdmin(driver);
		cppa.setCurrentPassword(p.getProperty("parkpassword"));
		cppa.setNewPassword(p.getProperty("parkpassword"));
		cppa.setConfirmNewPassword(p.getProperty("parkpassword"));
		cppa.clickResetButton();
		String Expectedmsg="Cannot set old password as new password";
		String Actualmsg=cppa.getNewPasswordSameAsOldPasswordValidation();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
//		Thread.sleep(2000);
	}
	
	@Test(priority=8,groups="Positive",enabled=true, description="TC_009_Submit password with valid values and verify success message")
	public void submit_password_with_validValues() throws InterruptedException
	{
		ChangePasswordPageForParkAdmin cppa=new ChangePasswordPageForParkAdmin(driver);
		cppa.setCurrentPassword(p.getProperty("parkpassword"));
		cppa.setNewPassword(p.getProperty("parkAdminNewPasswd"));
		cppa.setConfirmNewPassword(p.getProperty("parkAdminNewPasswd"));
		cppa.clickResetButton();
		String Expectedmsg="Password changed successfully";
		String Actualmsg=cppa.getPasswordChangeSuccessMessage();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);
//		Thread.sleep(2000);
	}
	
	@Test(priority=9,groups="Positive",enabled=true, description="TC_010_Login as Park Admin with new password and verify login success")
	public void parkAdminLoginWithNewPassword() throws InterruptedException
	{
		LoginPage lp2=new LoginPage(driver);
		lp2.setuserName(createdusername);
		lp2.setpassWord(p.getProperty("parkAdminNewPasswd"));
		lp2.setSubmit();
		Thread.sleep(3000);
		System.out.println("Park Admin login with new password successful");
		lp2.ProfileIconClick();
	    lp2.clickLogout();
		
	}
}
