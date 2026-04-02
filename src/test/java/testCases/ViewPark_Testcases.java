package testCases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import pageObjects.LoginPage;
import pageObjects.ViewPark;
import testBase.BaseClass;

public class ViewPark_Testcases extends BaseClass {


	@Test(priority=0,groups="Positive",description="TC_001_Verify user can able login")
	public void loginToEpass() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		lp.setuserName(p.getProperty("username"));
		lp.setpassWord(p.getProperty("password"));
		lp.setSubmit();	
		System.out.println("Login Successfully");

	}

	@Test(priority=1,groups="Positive",description="TC_002_Verify user can able to view Park list page")
	public void VerifyViewAllParkClick() throws InterruptedException {
		ViewPark pl = new ViewPark(driver);

		pl.viewParkClick();
		Thread.sleep(5000);
		System.out.println("Just working");
		String expectedmsg=p.getProperty("parkscreenTitle");
		System.out.println(expectedmsg);
		String actualmsg=pl.parkTitleName();
		Assert.assertEquals(actualmsg,expectedmsg);
		System.out.println(actualmsg);			
	}
	@Test(priority = 2, groups = "Positive", description = "TC_003_Verify user can view park address lines")
	public void verifyPhaseAddressLines() {
		ViewPark vp = new ViewPark(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));		    	    

		// Actual address lines from the UI
		List<String> addressLines = vp.getFirstCardAddressLines();
		List<String> actualAddressLines = Arrays.asList(addressLines.get(0).split("\\r?\\n"));
		System.out.println("Actual address " +addressLines);		   
		List<String> expectedAddressLines = Arrays.asList(
				p.getProperty("expected.address.lines").split("\\s*,\\s*")
				);
		System.out.println("Expeced address " +expectedAddressLines);		  
		Assert.assertEquals(actualAddressLines, expectedAddressLines, 
				"Mismatch:\nActual: " + String.join(", ", addressLines) + 
				"\nExpected: " + String.join(", ", expectedAddressLines));

		System.out.println("All phase address lines are displayed correctly.");
	}

	@Test(priority = 3,groups = "Positive", description = "TC_004_Calculate total issued passes from all rows in detailed screen")
	public void calculateTotalPassesIssuedFromDetailedScreen() {
		ViewPark tp = new ViewPark(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		int expected = Integer.parseInt(tp.getParkPassesCount().trim());
		System.out.println(expected);
		tp.selectPark();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody")));

		List<WebElement> totalPassCells = driver.findElements(By.xpath("//tbody/tr/td[2]"));

		int totalPasses = 0;
		for (WebElement cell : totalPassCells) {
			String value = cell.getText().trim();
			if (!value.isEmpty()) {
				totalPasses += Integer.parseInt(value);
			}
		}


		System.out.println("Total Passes Issued: " + totalPasses);  


		Assert.assertEquals(totalPasses, expected, "Mismatch in total issued passes.");

	}

	@Test(priority = 4,groups = "Positive",description = "TC_005_Calculate total visited passes from all rows in detailed screen")
	public void calculateVisitedPassesSumFromDetailedScreen() {
		ViewPark vp = new ViewPark(driver);	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		vp.backLink();
		int expectedVisited = Integer.parseInt(vp.visitedPassFromPark().getText().trim());
		System.out.println(expectedVisited);
		vp.selectPark();	    

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody")));

		List<WebElement> visitedCells = driver.findElements(By.xpath("//tbody/tr/td[3]"));

		int totalVisited = 0;
		for (WebElement cell : visitedCells) {
			String text = cell.getText().trim();
			if (!text.isEmpty()) {
				totalVisited += Integer.parseInt(text);
			}
		}

		System.out.println("Total Visited Passes: " + totalVisited);

		Assert.assertEquals(totalVisited, expectedVisited, "Visited passes total mismatch!");
	}

	@Test(priority=5,groups="Positive",description="TC_006_Verify user can able select park")
	public void verifySelectparkFromView() throws InterruptedException 
	{
		ViewPark slct = new ViewPark(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//slct.selectPark();
		String expectedmsg=p.getProperty("parkName");
		String actualmsg=slct.ParkNameFromLIst();
		Assert.assertEquals(actualmsg,expectedmsg);
		System.out.println(actualmsg);
	}
	@Test(priority=6,groups="Positive",description="TC_007_Verify user can able see profile Icon")
	public void verifyProfileIconFoundInDetailPage() throws InterruptedException 
	{
		ViewPark icon = new ViewPark(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOf(icon.iconPresent()));

		Assert.assertTrue(avatarIcon.isDisplayed(), "Avatar icon should be visible on the page");

	}
	@Test(priority=7,groups="Positive",description="TC_008_Verify user can able see Create Park Admin button")
	public void parkAdmin() throws InterruptedException 
	{
		ViewPark pa = new ViewPark(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement adminButton = wait.until(ExpectedConditions.visibilityOf(pa.parkAdmin()));

		Assert.assertTrue(adminButton.isDisplayed(), "Create Park Admin");
		System.out.println(adminButton.getText());

	}	

			@Test(priority=8,groups="Positive",description="TC_009_Verify user can able see Total phase count in park detail page")
			public void parkPhase() throws InterruptedException 
			{
				ViewPark ph = new ViewPark(driver);
				 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				 WebElement parkDetail = wait.until(ExpectedConditions.visibilityOf(ph.parkPhase()));
				 System.out.println(parkDetail.getText());
	
			}
			@Test(priority=9,groups="Positive",description="TC_010_Verify user can able see visited phase count in park detail page")
			public void visitedPhase() throws InterruptedException 
			{
				ViewPark vp = new ViewPark(driver);
				 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				 WebElement visitedDetail = wait.until(ExpectedConditions.visibilityOf(vp.visitedPhase()));
				 System.out.println(visitedDetail.getText());
				    
	
			}


}
