package testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import io.github.bonigarcia.wdm.WebDriverManager;

public class CompanyUserCreation_Testcases {
	public String CompanyuserName;
	WebDriver driver;

	@BeforeMethod
	public void initialize() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions co=new ChromeOptions();
		co.addArguments("--incognito");
		driver=new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get("https://epassqa.pearlglobalsolutions.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test(priority=1)
	public void company_user_creation_page() throws InterruptedException {
		//login as a company admin
		driver.findElement(By.xpath("//input[@placeholder='Enter username']")).sendKeys("parvathy");
		driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//redirect to user list page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//span[normalize-space()='Go To Company Users']")).click();
		//clicking create company user button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Create Company User']")));
		driver.findElement(By.xpath("//span[normalize-space()='Create Company User']")).click();
		//filling the company user creation form with invalid data
		WebElement username=driver.findElement(By.xpath("//input[@placeholder='Enter username for company user login']"));
		username.clear();
		username.sendKeys("");
		WebElement password=driver.findElement(By.xpath("//input[@placeholder='Enter password for company user login']"));
		password.clear();
		password.sendKeys("");
		WebElement confirmPassword=driver.findElement(By.xpath("//input[@placeholder='Re-enter password for company user login']"));
		confirmPassword.sendKeys("");
		WebElement contactName=driver.findElement(By.xpath("//input[@placeholder='Enter contact name for company user']"));
		contactName.clear();
		contactName.sendKeys("");
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter email id for company user contact']")));
		WebElement Email=driver.findElement(By.xpath("//input[@placeholder='Enter email id for company user contact']"));
		Email.sendKeys("");
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter alternate email id for company user contact']")));
		WebElement altEmail=driver.findElement(By.xpath("//input[@placeholder='Enter alternate email id for company user contact']"));
		altEmail.sendKeys("");
		WebElement contactNumber=driver.findElement(By.xpath("//input[@placeholder='Enter contact number for company user']"));
		contactNumber.sendKeys("");
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//asserting the error messages( currently one message is taken)
		Assert.assertTrue(driver.findElement(By.xpath("//div[normalize-space()='HR email id is required']")).getText().contains("HR email id is required"));
		//scrolling down to the bottom of the page
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);"); 
		//validating password field
		password.sendKeys("Admin");
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)')]")).getText().contains("Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)"));
		password.clear();
		password.sendKeys("Admin@123");
		confirmPassword.clear();
		confirmPassword.sendKeys("Admin@1234");
		//validating confirm password field
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='text-danger mb-3']")).getText().contains("Passwords do not match"));
		contactName.clear();
		contactName.sendKeys("RE#$");
		//validating contact name field
		Assert.assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Contact name must start with a letter and contain only alphanumeric characters']")).getText().contains("Contact name must start with a letter and contain only alphanumeric characters"));
		js.executeScript("window.scrollTo(100, document.body.scrollHeight)");
		Email.clear();
		Email.sendKeys("abcgetnad.com");
		//validating email field
		Assert.assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Please enter a valid email id']")).getText().contains("Please enter a valid email id"));
		altEmail.clear();
		altEmail.sendKeys("abcgetnad.com");
		//validating alternate email field
		Assert.assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Please enter a valid alternate email id']")).getText().contains("Please enter a valid alternate email id"));
		contactNumber.clear();
		contactNumber.sendKeys("1234");
		//validating contact number field
		Assert.assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Contact number must contain at least 10 numeric characters']")).getText().contains("Contact number must contain at least 10 numeric characters"));
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		//Validating already existing username
		username.clear();
		username.sendKeys("parvathy");
		password.clear();
		password.sendKeys("Admin@123");
		confirmPassword.clear();
		confirmPassword.sendKeys("Admin@123");
		contactName.clear();
		contactName.sendKeys(randomString());
		Email.clear();
		Email.sendKeys(randomString()+"@getnada.com");
		altEmail.clear();
		altEmail.sendKeys(randomString()+"@getnada.com");
		contactNumber.clear();
		contactNumber.sendKeys("65657657776");
		//scrolling down to the bottom of the page
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String expectedErrorMessage = "User with same Username exists";
		String actualErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'User with same Username exists')]")).getText();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message for existing username does not match");
		js.executeScript("window.scrollTo(0,0);");

		//validating the error message for already existing email
		username.clear();
		username.sendKeys("parvathi");
		Email.clear();
		Email.sendKeys("parvathy@getnada.com");
		altEmail.clear();
		altEmail.sendKeys(randomString()+"@getnada.com");
		contactNumber.clear();
		contactNumber.sendKeys("987654321567");
		
		//scrolling down to the bottom of the page
		js.executeScript("window.scrollTo(100, document.body.scrollHeight)");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String expectedEmailErrorMessage = "User with same Email exists";
		String actualEmailErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'User with same Email exists')]")).getText();
		Assert.assertEquals(actualEmailErrorMessage, expectedEmailErrorMessage, "Error message for existing email does not match");
		js.executeScript("window.scrollTo({ top: document.body.scrollHeight, left: 100, behavior: 'smooth' });");
		//Valid company user creation
		String CompanyuserName= randomString();
		username.sendKeys(CompanyuserName);
		password.clear();
		password.sendKeys("Admin@123");
		confirmPassword.clear();
		confirmPassword.sendKeys("Admin@123");
		contactName.clear();
		contactName.sendKeys(randomString());
		Email.clear();
		Email.sendKeys(randomString()+"@getnada.com");
		altEmail.clear();
		altEmail.sendKeys(randomString()+"@getnada.com");
		contactNumber.clear();
		contactNumber.sendKeys("98765432167");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Successfully created Company User and credentials ')]")));
		// Asserting the success message
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Successfully created Company User and credentials ')]")).getText().contains("Successfully created Company User and credentials sent to email."));


	}
		 @AfterMethod
		    public void tearDown(ITestResult result) {
		        if (ITestResult.FAILURE == result.getStatus()) {
		            takeScreenshot(result.getName());
		        }
		        driver.quit();
		    }


	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public void takeScreenshot(String testName) {
		// Create timestamp for unique filename
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/ScreenShts/" + testName + "_" + timestamp + ".png";

		try {
			FileUtils.copyFile(srcFile, new File(path));
			System.out.println("Screenshot saved to: " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}










