package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import testCases.CreateParkAdmin_Testcases;

public class EditNDeleteParkAdminPage extends BasePage {
	
	WebDriverWait wait;

	public EditNDeleteParkAdminPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
		
	//Locating all web elements in the Edit and Delete Park Admin page
	@FindBy(xpath="//h4[@class='main-title mb-0' and normalize-space()='Edit Park Admin']") 
	WebElement editIconPageHeader; 
	
		
	@FindBy(xpath="//span[normalize-space()='Go back']") WebElement goBackButton; // Assuming there's a back button to go back to the previous page
	@FindBy(xpath="//button[@type='submit' and normalize-space(text())='Save']") WebElement saveButton;
	@FindBy(xpath="//div[contains(text(),'Email ID exists')]") WebElement emailExistsValidation;
	@FindBy(xpath="//div[normalize-space(text())='Park Admin updated successfully']") WebElement parkAdminUpdationSuccessMessage;
	@FindBy(xpath="//div[normalize-space(text())='Park admin deleted successfully']") WebElement parkAdminDeletionSuccessMessage;
		
	//Page header functions
	public String getEditIconPageHeader() {
		wait.until(ExpectedConditions.visibilityOf(editIconPageHeader));
		return editIconPageHeader.getText();
	}
	
	//Function to click on the go back  button for the created park admin
	public void clickGoBackButton() {
		wait.until(ExpectedConditions.elementToBeClickable(goBackButton));
		goBackButton.click();
	}		
	
	//Function to click on the edit icon for the created park admin
	public void EditIcon_click(String parkadm) throws InterruptedException{	
		
		String givenparkadminName=parkadm;			
		By parkadminnames=By.xpath("//div[@class='contact-column']"); //To locate all the park admins in the page
		List<WebElement> parkadminList=driver.findElements(parkadminnames); //Storing all the park admin names in a list
		for(int i=0;i<parkadminList.size();i++) 
		{
			String parkadminName=parkadminList.get(i).getText();
			System.out.println("Park Admin Name: " + parkadminName);
			Thread.sleep(1000);
			if(parkadminName.contains(givenparkadminName))
			{
				{ String xpath1="//div[@class='contact-column' and normalize-space(text())='"
				   		 + givenparkadminName + "']/following-sibling::div[1]//a[1]//*[name()='svg']";
				WebElement editIcon=driver.findElement(By.xpath(xpath1));
				editIcon.click();
			     break;  
				}
			}
			else
			{ JavascriptExecutor js= (JavascriptExecutor) driver;
			//Scroll down the admin window box to find the park admin
			js.executeScript("document.querySelector('.d-flex.flex-column.gap-2.flex-grow-1.scrollable-content').scrollTop += 100"); //scrolling inside web element
				System.out.println("Park Admin Name not found, scrolling down...");
				js.executeScript("window.scrollBy(0,100)"); //	scrolling down the main window			
			Thread.sleep(1000); //Wait for a second before next iteration
				continue; }			
		   }
	}	
	
	
	//Function to click on the delete icon for the created park admin
		public void DeleteIcon_click(String parkadm) throws InterruptedException{	
			
			String givenparkadminName=parkadm;			
			By parkadminnames=By.xpath("//div[@class='contact-column']"); //To locate all the park admins in the page
			List<WebElement> parkadminList=driver.findElements(parkadminnames); //Storing all the park admin names in a list
			for(int i=0;i<parkadminList.size();i++) 
			{
				String parkadminName=parkadminList.get(i).getText();
				System.out.println("Park Admin Name: " + parkadminName);
				Thread.sleep(1000);
				if(parkadminName.contains(givenparkadminName))
				{
					{ String xpath1="//div[@class='contact-column' and normalize-space(text())='"
					   		 + givenparkadminName + "']/following-sibling::div[1]//a[2]//*[name()='svg']";
					WebElement deleteIcon=driver.findElement(By.xpath(xpath1));
					deleteIcon.click();
					
					System.out.println("delete button clicked ");
					Thread.sleep(1000); //Wait for the confirmation dialog to appear
					WebElement yesButton = driver.findElement(By.xpath("//button[normalize-space(text())='Yes']"));
			        yesButton.click();	
	//		        Thread.sleep(2000); //Wait for the deletion to complete					
					System.out.println("Park Admin " + givenparkadminName + " deleted successfully.");					
				    break; 	 
					}
				}
				else
				{ JavascriptExecutor js= (JavascriptExecutor) driver;
				  //Scroll down the admin window box to find the park admin
				  js.executeScript("document.querySelector('.d-flex.flex-column.gap-2.flex-grow-1.scrollable-content').scrollTop += 100"); //scrolling inside web element
				  System.out.println("Park Admin Name not found, scrolling down...");
				  js.executeScript("window.scrollBy(0,100)"); //	scrolling down the main window			
	    		  Thread.sleep(1000); //Wait for a second before next iteration
				  continue; }			
			      }
		     }	
		
	
	
	
	//Function to click on the save button after editing the park admin details
	public void clickSaveButton() {
		wait.until(ExpectedConditions.elementToBeClickable(saveButton));	
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
	}
	
	//Function to get the validation message when the email already exists
	public String getEmailExistsValidationMessage() {
		wait.until(ExpectedConditions.visibilityOf(emailExistsValidation));
		return emailExistsValidation.getText();
	}
	
	//Function to get the success message after updating the park admin details
	public String getParkAdminUpdationSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(parkAdminUpdationSuccessMessage));
		return parkAdminUpdationSuccessMessage.getText();
	}	
	
	//Function to get the success message after deleting the park admin
	public String getParkAdminDeletionSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(parkAdminDeletionSuccessMessage));
		return parkAdminDeletionSuccessMessage.getText();
	}
}
