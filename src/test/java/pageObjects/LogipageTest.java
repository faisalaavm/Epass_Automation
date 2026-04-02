package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogipageTest {
	WebDriver driver;
	public LogipageTest(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath="//input[@placeholder='Enter username']")
	WebElement userName;
	@FindBy(xpath="//input[@placeholder='Enter password']")
	WebElement passWord;
	
	
	public void setUsername( String usrName) {
		userName.clear();
		userName.sendKeys(usrName);
	}
	

}
