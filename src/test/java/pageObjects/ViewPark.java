package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ViewPark extends BasePage {
	 public String title = "Infopark Cherthala";

	public ViewPark(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	// Initialize web element path
	
		@FindBy(xpath="//a[@href='/dashboard/park-list']")
	    WebElement viewPark;
	    @FindBy(xpath = "//a//*[name()='svg'][2]")
	    WebElement eye;    
	    @FindBy(xpath="//ol[@class='breadcrumb fs-sm mb-1']//a[span[text()='Go back']]")
	    WebElement backLink;
	    @FindBy(xpath = "//div[contains(@class, 'circle')]")
	    List<WebElement> items;
	    @FindBy(xpath = "(//div[@class='pb-2 card-wrap']//div[contains(@class, 'd-flex') and contains(@class, 'flex-column')])[1]")
	    List<WebElement> parkAddress;	  
	    @FindBy(xpath="//h4[text()='Infopark Cherthala']")
	    WebElement parkName;
	    @FindBy(xpath="//div[.//small[text()='Passes']]//div[@class='h4 mb-0']/div")
	    WebElement totalpassFromView;
	    @FindBy(xpath="//div[.//small[text()='Visited']]//div[contains(@class,'h4 mb-0')]/span")
	    WebElement visitedpassFromView;
	    @FindBy(xpath="//div[@class='avatar online']/img")
	    WebElement profileicon;
	    @FindBy(xpath="//a[@href='/dashboard/create-park-admin' and contains(text(), 'Create Park Admin')]")
	    WebElement parkAdminbtn;
	    @FindBy(xpath="//div[@class='earning-item d-flex flex-column' and .//label[text()='Phases']]//h4")
	    WebElement phase;
	    @FindBy(xpath="//div[@class='earning-item d-flex flex-column' and .//label[text()='Visited']]//h4")
	    WebElement visitedPhase;
	    @FindBy(xpath="//td[a[text()='Infopark Kochi Phase 1']]/following-sibling::td[1]")
	    WebElement totalpass;
	    @FindBy(xpath="//td[a[text()='Infopark Kochi Phase 1']]/following-sibling::td[2]")
	    WebElement visitedpass;
	    @FindBy(css = "h4.main-title.mb-0")
	    WebElement parkTitle;  
	    @FindBy(xpath="//div[div[text()='Address']]/div/p")
	    WebElement phaseAddress;
	    

	    public void viewParkClick()
	    {    	
	    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	    	    wait.until(ExpectedConditions.elementToBeClickable(viewPark)).click();
	    	
	    }
	    public String parkTitleName()
	    {
	    	return parkTitle.getText();
	    }
	    public List<String> getFirstCardAddressLines() {
	        wait.until(ExpectedConditions.visibilityOfAllElements(parkAddress));
	        return parkAddress.stream()
	                                        .map(WebElement::getText)
	                                        .collect(Collectors.toList());
	    }

	    
    public String selectPark() 
    {    	
    	WebElement eyeIcon = driver.findElement(By.xpath("//h5[normalize-space()='" + title + "']/ancestor::div[contains(@class,'card')]//a[@href='/dashboard/park-details']"));
    	eyeIcon.click();
    	return parkName.getText(); 		    	

    }
    public String ParkNameFromLIst()
    {
    	return parkName.getText();
    }
    //icon fetching from detailed screen
    public WebElement iconPresent()
    {
    	
    	return profileicon;
		
	}
    //Create park Admin button finding
    public WebElement parkAdmin()
    {
    	
    	return parkAdminbtn;
		
	}
    //Getting the phase number from log(inside screen)
    public WebElement parkPhase()
    {
    	return phase;
    }
  //Getting the visited number from log(inside screen)
    public WebElement visitedPhase()
    {
    	return visitedPhase;
    }
    //Getting pass count from selected card
    public String getParkPassesCount() {

        return totalpassFromView.getText();
    }    
    public WebElement totalPass()
    {  
    	
       	return totalpass;
    }
    //Getting visited count from selected card
    public WebElement visitedPassFromPark()
    { 

       return visitedpassFromView;
    }
    public WebElement visitedPass()
    {
    	return visitedpass;
    }
    //Getting the address for selected park from card view
    public List<WebElement> phaseAddressLines() {
        WebElement addressDetail = wait.until(ExpectedConditions.visibilityOf(phaseAddress));
        return addressDetail.findElements(By.tagName("p"));
    }
    public void backLink() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 	    wait.until(ExpectedConditions.elementToBeClickable(backLink)).click();
 	    

	}
    public void scrollToTop() {
    	JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,100)");
    }
	
}
