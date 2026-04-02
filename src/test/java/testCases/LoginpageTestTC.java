package testCases;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LogipageTest;
import testBase.BaseClass;

public class LoginpageTestTC  {
	WebDriver driver;
	@BeforeTest
	public void initialize() {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions co= new ChromeOptions();
		co.addArguments("--incognito");

		driver=new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get("https://epassqa.pearlglobalsolutions.com/")	;
	}
	
	@Test
	public void login() {
		
		
	LogipageTest LT= new LogipageTest(driver);
	LT.setUsername("abc");
	
}

}
