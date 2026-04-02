package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.EditDeletePark;
import pageObjects.LoginPage;
import pageObjects.ViewPark;
import testBase.BaseClass;

public class EditDeletePark_Testcases extends BaseClass {

    String initialParkName = "Park Auto";
    String updatedParkName = "Park Updated";

    @Test(priority = 1, groups = "Positive", description = "TC_001_Verify user can edit an existing park")
    public void login() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        lp.setuserName(p.getProperty("username"));
        lp.setpassWord(p.getProperty("password"));
        lp.setSubmit();
        ViewPark vp = new ViewPark(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        vp.viewParkClick();
        EditDeletePark cp = new EditDeletePark(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        cp.createParkClick();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        initialParkName = cp.randomString();  // internally saves to createdParkName
        cp.setparkName(initialParkName);
        cp.setLocation(cp.randomString());
        cp.setStreetOne(cp.randomString());
        cp.setStreetTwo(cp.randomString());
        cp.city(cp.randomString());
        cp.district(cp.randomString());
        cp.state(cp.randomString());
        cp.pincode(cp.randomnumeric());
        cp.scrollToBottom();
        cp.createParkSaveClick();
        System.out.println("Created Park"+initialParkName);
        Assert.assertTrue(cp.isParkNameDisplayed(initialParkName), "Park not found after creation");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        System.out.println("Created Park in Update module:" +initialParkName);
        //cp.editParkSaveClick();  
        cp.clickEditButtonForPark(initialParkName);
        Thread.sleep(3000);
        updatedParkName = "Updated" + cp.randomString();
        cp.setparkName(updatedParkName);
        cp.setLocation(cp.randomString());
        cp.setStreetOne(cp.randomString());
        cp.setStreetTwo(cp.randomString());
        cp.city(cp.randomString());
        cp.district(cp.randomString());
        cp.state(cp.randomString());
        cp.pincode(cp.randomnumeric());
        cp.scrollToBottom();
        cp.createParkSaveClick();   
        System.out.println("Updated park Name AFter :" +updatedParkName);   
        String currentParkName = initialParkName + updatedParkName;
        System.out.println(currentParkName);
        Thread.sleep(3000);
        String Expectedmsg="Park updated successfully";		
		String Actualmsg=cp.editSuccess().getText();
		System.out.println("Actual message: " + Actualmsg);
		Assert.assertEquals(Actualmsg, Expectedmsg);       
                   
    }
    
   @Test(priority = 2, groups = "Positive", description = "TC_002_Verify user can delete a park")
   public void deleteCreatedPark() {
       EditDeletePark cp = new EditDeletePark(driver);
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

       // Combine both names
       String currentParkName = updatedParkName;
       System.out.println("Deleting: " + currentParkName);
       cp.clickParkDeleteButton(currentParkName);
//       cp.isParkNameDisplayed(currentParkName);
//       cp.deleteParkClick();

       WebElement deleteModal = driver.findElement(
    		    By.xpath("//div[contains(@class, 'modal-dialog') and .//div[contains(text(), 'Are you sure')]]")
    		);

    		WebElement yesBtn = deleteModal.findElement(
    		    By.xpath(".//button[normalize-space(text())='Yes']")
    		);
    		yesBtn.click();
           System.out.println("Park deleted successfully");
           String expectedMsg ="Park deleted successfully";
           String actualMsg = cp.deleteSuccess().getText();
           System.out.println("Actual Message Delete"+actualMsg);
           Assert.assertEquals(actualMsg, expectedMsg);
   }

 }