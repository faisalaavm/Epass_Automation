package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditNDeleteGate extends BasePage {
	
	public EditNDeleteGate(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	//Finding Locators for the Edit page
	@FindBy(xpath="//h4[@class='main-title mb-0' and text()='Edit Gate']") WebElement editIconPageHeader; // Page header for Edit Gate page
	@FindBy(xpath="//div[contains(text(),'Gate updated sucessfully')]") WebElement gateUpdationSuccessMessage; 
	@FindBy(xpath="//div[contains(text(),'Gate deleted sucessfully')]") WebElement gateDeletionSuccessMessage;
	@FindBy(xpath="//button[@type='submit' and contains(@class, 'btn-sign') and text()='Save']") WebElement saveButton;	
	@FindBy(xpath = "//button[@aria-label='close']//*[name()='svg']")
	WebElement closeButton;
	
	public void closeButton() {
		 //	        wait.until(ExpectedConditions.elementToBeClickable(closeButton));
		 	        closeButton.click();
		 	    }
	
	//Function to click on the edit icon for the created gate
	public void gate_EditIcon_click(String gatenam) throws InterruptedException {
	    String createdGateName = gatenam;
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    boolean gateFound = false;
	    int scrollAmount = 100;
	    int maxScrolls = 50;  // Limit to prevent infinite scrolling
	    int scrollCount = 0;

	    while (!gateFound && scrollCount < maxScrolls) {
	        List<WebElement> gateLists = driver.findElements(By.xpath("//div[contains(@class, 'card-wrap')]//h5"));
	        
	        for (WebElement gate : gateLists) {
	            String gateName = gate.getText();
	            System.out.println("Gate Name: " + gateName);
	            
	            // Check if the current gate name matches the created gate name            
	            if (gateName.equals(createdGateName)) {
	                // Scroll gate into view first
	                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", gate);
	                Thread.sleep(500);

	                // Now locate the icon relative to the *current* gate element
	                WebElement editIcon = driver.findElement(By.xpath("//div[contains(@class, 'card-wrap')]//h5[text()='" 
	                        + gateName + "']//ancestor::div[2]//a[2]//*[name()='svg']"));

	                wait.until(ExpectedConditions.elementToBeClickable(editIcon));
	                js.executeScript("arguments[0].scrollIntoView(true);", editIcon);
	                editIcon.click();
	                Thread.sleep(500);
	                System.out.println("Clicked edit icon for gate: " + createdGateName);

	                gateFound = true;
	                break;
            }
	        }

	        if (!gateFound) {
	            js.executeScript("window.scrollBy(0, " + scrollAmount + ")");       	
	            scrollCount++;
	            System.out.println("Gate not found yet. Scrolling down... (" + scrollCount + ")");
	            Thread.sleep(1000);
	        }
	    }

	    if (!gateFound) {
	        System.out.println("Gate with name '" + createdGateName + "' was not found after scrolling.");
	    }
	}
	
	
	//Function to click on the delete icon for the created gate
			public void DeleteIcon_click(String updatedgate) throws InterruptedException{	
				
				String updatedNameOfGate = updatedgate;
			    JavascriptExecutor js = (JavascriptExecutor) driver;

			    boolean gateFound = false;
			    int scrollAmount = 100;
			    int maxScrolls = 50;  // Limit to prevent infinite scrolling
			    int scrollCount = 0;

			    while (!gateFound && scrollCount < maxScrolls) {
			        List<WebElement> gateLists = driver.findElements(By.xpath("//div[contains(@class, 'card-wrap')]//h5"));
			        
			        for (WebElement gate : gateLists) {
			            String gateName = gate.getText();
			            System.out.println("Gate Name: " + gateName);
			            if (gateName.equals(updatedNameOfGate)) {
			                // Scroll gate into view first
			                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", gate);
			                Thread.sleep(500);

			                // Now locate the icon relative to the *current* gate element
			                WebElement deleteIcon = driver.findElement(By.xpath("//div[contains(@class, 'card-wrap')]//h5[text()='" 
			                        + gateName + "']//ancestor::div[2]//a[3]//*[name()='svg']"));

			                wait.until(ExpectedConditions.elementToBeClickable(deleteIcon));

			                js.executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
			                deleteIcon.click();
			                Thread.sleep(500);
			                System.out.println("Clicked delete icon for gate: " + updatedNameOfGate);
			                
			                // Handle confirmation dialog
			                WebElement yesButton = driver.findElement(By.xpath("//button[normalize-space(text())='Yes']"));
			                wait.until(ExpectedConditions.elementToBeClickable(yesButton));
			                yesButton.click();
			                Thread.sleep(1000); // Wait for the deletion to complete
			                System.out.println("Gate " + updatedNameOfGate + " deleted successfully.");

			                gateFound = true;
			                break;
		            }
			        }

			        if (!gateFound) {
			            js.executeScript("window.scrollBy(0, " + scrollAmount + ")");       	
			            scrollCount++;
			            System.out.println("Gate not found yet. Scrolling down... (" + scrollCount + ")");
			            Thread.sleep(1000);
			        }
			    }

			    if (!gateFound) {
			        System.out.println("Gate with name '" + updatedNameOfGate + "' was not found after scrolling.");
			    }
			}
        
			            
	
	
	//Function to get the page header of the Edit Gate page
	public String getEditIconPageHeader() {
		wait.until(ExpectedConditions.visibilityOf(editIconPageHeader));
		return editIconPageHeader.getText();
	}
	
	//Function to click on the save button in the Edit Gate page
	public void clickSaveButton() {
		wait.until(ExpectedConditions.elementToBeClickable(saveButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
	}
	
	//Function to get the success message after updating the gate
	public String getGateUpdationSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(gateUpdationSuccessMessage));
		return gateUpdationSuccessMessage.getText();
	}
	
	//Function to get the success message after deleting the gate
	public String getGateDeletionSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(gateDeletionSuccessMessage));
		return gateDeletionSuccessMessage.getText();
	}
	
}
