package testCases;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sprint3taskJittu {
WebDriver driver;
	
	@BeforeTest
	public void initialize() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get("https://epassqa.pearlglobalsolutions.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));}

	@Test
	public void testLogin() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Enter username' and @type='text']\r\n"
				+ "")).sendKeys("superadmin");
	
		driver.findElement(By.xpath("//input[@placeholder='Enter password' and @type='password']\r\n"
				+ "")).sendKeys("Admin@1234");
	driver.findElement(By.xpath("//button[@type='submit' and contains(@class, 'btn-sign') and text()='Login']"
				+ "")).click();
	driver.findElement(By.xpath("//a[text()='Infopark Cherthala']\r\n"
			+ "")).click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,500)");
	driver.findElement(By.xpath("//a[@href='/dashboard/phase-details/?gen' and text()='Infopark Cherthala Phase 1']"
			+ "")).click();
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	jse.executeScript("window.scrollBy(0,500)");
	driver.findElement(By.xpath("//a[@href='/dashboard/building-details/?gen' and text()='Chaithanya']\r\n"
			+ "")).click();
	driver.findElement(By.xpath("//h6[@class='mb-0' and text()='Dot In Technologies']\r\n"
			+ "")).click();
	driver.findElement(By.xpath("//input[@name='name']\r\n"
						+ "")).sendKeys("Testname");
	driver.findElement(By.xpath("//input[@name='phone']\r\n"
				+ "")).sendKeys("12423423535");
	driver.findElement(By.xpath("//input[@name='email']\r\n"
			+ "")).sendKeys("test@gmail.com");
//	Thread.sleep(5000);
	driver.findElement(By.xpath("//td[contains(@class, 'text-primary') and contains(@class, 'fixed-width-last') and normalize-space(text())='Testname']\r\n"
			+ "")).getText();	
//	Thread.sleep(5000);
	
	String expectedValue = "Testname";
	String actualValue = driver.findElement(By.xpath("//td[contains(@class, 'text-primary') and contains(@class, 'fixed-width-last') and normalize-space(text())='Testname']\r\n"
			+ "")).getText();
	assert actualValue.equals(expectedValue) : "The actual value does not match the expected value.";
System.out.println("The actual value matches the expected value: " + actualValue);
	//Assert.assertEquals(actualValue, expectedValue, "The actual value does not match the expected value.");	
	
}}
	
