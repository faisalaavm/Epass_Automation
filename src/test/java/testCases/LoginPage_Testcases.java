package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.Log;

public class LoginPage_Testcases extends BaseClass {

	@Test(priority=1,groups="Negative",description="TC_001_Verify user can able to login with blank username and password")
	public void validation_login() throws InterruptedException {
		Log.info("Starting login test");
		LoginPage lp=new LoginPage(driver);
		Log.info("Submit with blank details");
		lp.setuserName("");
		lp.setpassWord("");
		lp.setSubmit();
		Log.info("Displaying validation message and compare the results");
		String expectedmsg="Username is required";
		String actualmsg=lp.BlankUsernameValidation();
		Assert.assertEquals(actualmsg,expectedmsg);
		String expectedmsg1="Password is required";
		String actualmsg1=lp.BlankPasswordValidation();
		Assert.assertEquals(actualmsg1,expectedmsg1);
		System.out.println("Login with blank details test completed successfully");
		
	}

	@Test(priority=2,groups="Negative",description="TC_002_Verify user can able to login with invalid username and invalid password")
	public void login_page_with_invalid_details() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.setuserName(p.getProperty("username1"));
		lp.setpassWord(p.getProperty("password1"));
		lp.setSubmit();
		String expectedmsg="Invalid Username/Password";
		String actualmsg=lp.checkValidation();
		Assert.assertEquals(actualmsg,expectedmsg);
		System.out.println("Login with invalid details test completed successfully");
		Log.info("Login with invalid details test completed successfully");
		
	}
	
	@Test(priority=3,groups="Negative",description="TC_003_Verify user can able to login with invalid username and a valid password")
	public void login_page_with_invalid_username() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.setuserName(p.getProperty("username1"));
		lp.setpassWord(p.getProperty("password"));
		lp.setSubmit();
		String expectedmsg="Invalid Username/Password";
		String actualmsg=lp.checkValidation();
		Assert.assertEquals(actualmsg,expectedmsg);
		System.out.println("Login with invalid username test completed successfully");
		Log.info("Login with invalid username test completed successfully");
		
	}

	@Test(priority=4,groups="Negative",description="TC_004_Verify user can able to login with a valid username and invalid password")
	public void login_page_with_invalid_password() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.setuserName(p.getProperty("username"));
		lp.setpassWord(p.getProperty("password1"));
		lp.setSubmit();
		String expectedmsg="Invalid Username/Password";
		String actualmsg=lp.checkValidation();
		Assert.assertEquals(actualmsg,expectedmsg);
		System.out.println("Login with invalid password test completed successfully");
		Log.info("Login with invalid password test completed successfully");
		
	}

	@Test(priority=5,groups="Positive",description="TC_005_Verify user can able to login with valid username and password")
	public void login_page_with_valid_inputs() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.setuserName(p.getProperty("username"));
		lp.setpassWord(p.getProperty("password"));
		lp.setSubmit();
		String actualUrl=p.getProperty("dashboardurl");
		Thread.sleep(2000);
		String expectedUrl=lp.getUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		System.out.println("Login with valid details test completed successfully");
		Log.info("Login test completed successfully");
		Thread.sleep(8000);
		
	}
	
	@Test(priority=6,groups="Positive",description="TC_006_Verify user can able to logout from the application")
	public void logout_page() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.ProfileIconClick();
		lp.clickLogout();
		String actualUrl=p.getProperty("loginpageurl");
		String expectedUrl=lp.getUrl();
		Assert.assertEquals(actualUrl, expectedUrl);
		System.out.println("Logout test completed successfully");
		Log.info("Logout test completed successfully");
		

	}
	
}


