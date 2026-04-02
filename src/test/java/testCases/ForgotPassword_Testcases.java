package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ForgotPasswordPage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.Log;

public class ForgotPassword_Testcases extends BaseClass {
	@Test (priority=1,groups="Positive",description="TC_001_Verify clicking on forgot password link redirects to forgot password page")
	public void forgotpassword_redirection() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.clickForgotPassword();
		String actualUrl=p.getProperty("forgotpasswordpageurl");
		String expectedUrl=lp.getUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		System.out.println("Forgot password page redirection test completed successfully");
		Log.info("Forgot password page redirection test completed successfully");
	}
	@Test (priority=2,groups="Negative",description="TC_002_Verify email Id required validation on forgot password page")
	public void email_Id_required_validation() throws InterruptedException {
		ForgotPasswordPage fp=new ForgotPasswordPage(driver);
		fp.clickSubmit();
		String actualmsg="Email id is required";
		String expectedmsg=fp.getEmailRequiredMessage();
		Assert.assertEquals(actualmsg, expectedmsg);
		System.out.println("Forgot password email required validation test completed successfully");
		Log.info("Forgot password email required validation test completed successfully");
	}
	
	@Test (priority=3,groups="Negative",description="TC_003_Verify email Id validation on forgot password page")
	public void email_Id_validation() throws InterruptedException {
		ForgotPasswordPage fp=new ForgotPasswordPage(driver);
		fp.enterEmail(p.getProperty("forgotpasswordemailvalidation"));
		fp.clickSubmit();
		String actualmsg="Please enter a valid email id";
		String expectedmsg=fp.getInvalidEmailMessage();
		Assert.assertEquals(actualmsg, expectedmsg);
		System.out.println("Forgot password email validation test completed successfully");
		Log.info("Forgot password email validation test completed successfully");
	}

	@Test (priority=4,groups="Negative",description="TC_004_Verify validation message displayed for a not registered email ID")
	public void forgotpassword_notregisteredemail() throws InterruptedException {
		ForgotPasswordPage fp=new ForgotPasswordPage(driver);
		fp.enterEmail(p.getProperty("forgotpasswordnotregisteredemail"));
		fp.clickSubmit();
		String actualmsg="Invalid Email ID. This is not a Registered Email.";
		String expectedmsg=fp.getNotRegisteredEmailMessage();
		Assert.assertEquals(actualmsg, expectedmsg);
		System.out.println("Forgot password not registered email test completed successfully");
		Log.info("Forgot password not registered email test completed successfully");}
	
	@Test (priority=5,groups="Positive",description="TC_005_Verify user can submit a valid email ID for password reset")
		public void forgotpassword_submit() throws InterruptedException {
			ForgotPasswordPage fp=new ForgotPasswordPage(driver);
			fp.enterEmail(p.getProperty("forgotpasswordregisteredemail"));
			fp.clickSubmit();
			String actualmsg="Password reset mail has been sent to your registered email.";
			String expectedmsg=fp.getPasswordResetSuccessMessage();
			Assert.assertEquals(actualmsg, expectedmsg);
			System.out.println("Forgot password submit test completed successfully");
			Log.info("Forgot password submit test completed successfully");}
		
		
	@Test (priority=6,groups="Positive",description="TC_006_Verify clicking on back to login link redirects to login page")
	public void forgotpassword_backtologin() throws InterruptedException {
		ForgotPasswordPage fp=new ForgotPasswordPage(driver);
		fp.clickBackToLogin();
		String actualUrl=p.getProperty("loginpageurl");
		String expectedUrl=fp.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		System.out.println("Forgot password back to login test completed successfully");
		Log.info("Forgot password back to login test completed successfully");}

}
