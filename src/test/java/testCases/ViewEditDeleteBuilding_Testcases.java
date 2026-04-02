package testCases;

import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ViewEditDeleteBuilding;
import pageObjects.ViewPark;
import testBase.BaseClass;


public class ViewEditDeleteBuilding_Testcases extends BaseClass{
	String initialBuildingName = "Building Auto";
    String updatedBuildingName = "Building Updated";

	@Test(priority=1,groups="Positive",description="TC_001_Verify user can able to view building")
	public void loginToEpass() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		lp.setuserName(p.getProperty("username"));
		lp.setpassWord(p.getProperty("password"));
		lp.setSubmit();	
		System.out.println("Login Successfully");	
		ViewPark pl = new ViewPark(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	
		pl.viewParkClick();		
		String expectedmsg=p.getProperty("parkscreenTitle");		
		String actualmsg=pl.parkTitleName();
		Assert.assertEquals(actualmsg,expectedmsg);
		System.out.println(actualmsg);			
		ViewPark slct = new ViewPark(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		slct.selectPark();
		String expectedmsg1=p.getProperty("parkName");
		String actualmsg1=slct.ParkNameFromLIst();
		Assert.assertEquals(actualmsg1,expectedmsg1);
		System.out.println(actualmsg1);
		ViewEditDeleteBuilding Vb = new ViewEditDeleteBuilding(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Vb.scrollToBottom();
		Vb.viewAllPhaseClick();		
		String expectedmsg2=p.getProperty("phasescreenTitle");		
		String actualmsg2=Vb.phaseTitleName();
		Assert.assertEquals(actualmsg2,expectedmsg2);
		System.out.println(actualmsg2);			
		ViewEditDeleteBuilding ps = new ViewEditDeleteBuilding(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));		
		ps.selectPhase();		
		String expectedmsg3=p.getProperty("phaseName");
		System.out.println("Expected="+expectedmsg3);
		String actualmsg3=ps.phaseHeaderName();
		System.out.println("Actual="+actualmsg3);
		Assert.assertEquals(actualmsg3,expectedmsg3);
		System.out.println(actualmsg3);			
		ViewEditDeleteBuilding vb = new ViewEditDeleteBuilding(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	
		vb.viewAllBuildingClick();	
		Thread.sleep(5000);
		String expectedmsg4=p.getProperty("buildingScreenTitle");		
		String actualmsg4=vb.buildingHeaderName();
		Assert.assertEquals(actualmsg4,expectedmsg4);
		System.out.println(actualmsg4);			
		}
	@Test(priority=2,groups="Positive",description="TC_002_Verify user can able to find the view button tooltip")
	public void verifyViewButtonTooltip() throws InterruptedException {
		ViewEditDeleteBuilding vt = new ViewEditDeleteBuilding(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		vt.viewTooltipFind();
		String expectedmsg=p.getProperty("viewTooltip");
		String actualmsg=vt.viewTooltipFind().getText();
		Assert.assertEquals(actualmsg,expectedmsg);
		
	}
	@Test(priority=3,groups="Positive",description="TC_003_Verify user can able to find the edit button tooltip")
	public void verifyEditButtonTooltip() throws InterruptedException {
		ViewEditDeleteBuilding vt = new ViewEditDeleteBuilding(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		vt.editTooltipFind();
		String expectedmsg=p.getProperty("editTooltip");
		String actualmsg=vt.editTooltipFind().getText();
		Assert.assertEquals(actualmsg,expectedmsg);
		
	}
	@Test(priority=4,groups="Positive",description="TC_004_Verify user can able to find the delete button tooltip")
	public void verifyDeleteButtonTooltip() throws InterruptedException {
		ViewEditDeleteBuilding vt = new ViewEditDeleteBuilding(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		vt.deleteTooltipFind();
		String expectedmsg=p.getProperty("deleteTooltip");
		String actualmsg=vt.deleteTooltipFind().getText();
		Assert.assertEquals(actualmsg,expectedmsg);
		
	}
	@Test(priority=5,groups="Positive",description="TC_05_Verify user can able to click create building button")
	public void verifyCreateBuildingClick() throws InterruptedException {
		ViewEditDeleteBuilding cb = new ViewEditDeleteBuilding(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	
		cb.createBuildingbtnClick();		
		String expectedmsg=p.getProperty("createBuildingTitle");		
		String actualmsg=cb.createBuildingHeaderName();
		Assert.assertEquals(actualmsg,expectedmsg);
		System.out.println(actualmsg);			
		}
	
	 @Test(priority = 6, groups = "Positive", description = "TC_006_Verify user can edit a building with valid data")
	    public void createParkWithValidValues() throws InterruptedException {
		 ViewEditDeleteBuilding cp= new ViewEditDeleteBuilding(driver);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	        initialBuildingName = cp.randomString();  // internally saves to createdBuildingName
	        cp.setBuildingName(initialBuildingName);
	        cp.setFloors(cp.randomnumeric());	
	        cp.scrollToBottom();	            
	        cp.saveBuildingbtnClick();
	        System.out.println("Created Building"+initialBuildingName);
	    
	        ViewEditDeleteBuilding be= new ViewEditDeleteBuilding(driver);
		   // driver.navigate().refresh();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	        be.clickEditButtonForBuilding(initialBuildingName);	
	        Thread.sleep(3000);
	        updatedBuildingName = "Updated" + be.randomString();
	        be.setBuildingName(updatedBuildingName);
	        be.setFloors(be.randomnumeric());	
	        be.scrollToBottom();
	        Thread.sleep(5000);
	        be.saveBuildingbtnClick();	           
	        //System.out.println("Updated building Name AFter :" +updatedBuildingName);   
	        String currentParkName = initialBuildingName + updatedBuildingName;
	        System.out.println(currentParkName);
	        Thread.sleep(3000);
	        String Expectedmsg=p.getProperty("editSuccessMsg");		
			String Actualmsg=be.editToasterMessage();
			System.out.println("Actual message: " + Actualmsg);
			Assert.assertEquals(Actualmsg, Expectedmsg);       
	                   
	    }
	 @Test(priority = 7, groups = "Positive", description = "TC_008_Verify user can delete a building")
	 public void verifyDeleteBuilding1() throws InterruptedException {
	     ViewEditDeleteBuilding cp = new ViewEditDeleteBuilding(driver);
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	     String currentParkName = updatedBuildingName;
	     System.out.println("Deleting: " + currentParkName);
	   
	     // ✅ Call delete logic – modal handled inside
	     cp.clickBuildingDeleteButton(currentParkName);

	     // ✅ Assert toaster message
	     String Expectedmsg = p.getProperty("deleteSuccessMsg");
	     String Actualmsg = cp.deleteSuccess().getText();

	     System.out.println("Expected message: " + Expectedmsg);
	     System.out.println("Actual message: " + Actualmsg);

	     Assert.assertEquals(Actualmsg, Expectedmsg);
	 }
}
