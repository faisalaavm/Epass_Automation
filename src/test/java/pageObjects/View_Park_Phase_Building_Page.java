package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class View_Park_Phase_Building_Page extends BasePage{

	public View_Park_Phase_Building_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//span[contains(text(),'›')]")
	WebElement btn_Next;
	
	public void clickViewButtonForPark(String parkName) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
	    String xpath = "//div[h5[text()='" + parkName + "']]//following-sibling::div//a[@href='/dashboard/park-details']";

	    // wait for element to be clickable
	    WebElement viewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

	    // scroll to the element (helps more in headless mode)
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewButton);

	    try {
	        // normal click
	        viewButton.click();
	    } catch (ElementClickInterceptedException e) {
	        // fallback JS click if intercepted
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewButton);
	    }
	}

//Method to check if the newly created park is present in the list
	public boolean isParkPresentInList(String parkName) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    try {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//h5[text()='" + parkName + "']")));
	        return true;     // Found within the timeout
	    } catch (TimeoutException e) {
	        return false;    // Not found in the current page
	    }
	}

	    // Method to navigate through pages and find the park
	public boolean searchForParkInPaginatedList(String parkName) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

	    while (true) {
	        // Check if the park is visible on the current page
	        if (isParkPresentInList(parkName)) {
	            return true;
	        }

	        // If NOT found → go to next page, if enabled
	        if (btn_Next.isEnabled()) {
	            btn_Next.click();

	            // Wait for page content to refresh
	            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.tagName("body"))));
	        } else {
	            break; // no more pages
	        }
	    }
	    return false;
	}
	    
	 public void clickViewButtonForPhase(String phaseName) {
		 String xpath = "//div[h5[text()='" + phaseName + "']]//following-sibling::div//a[@href='/dashboard/phase-details']";
		    WebElement viewButton = driver.findElement(By.xpath(xpath));
		    viewButton.click();
		}

//Method to check if the newly created park is present in the list
	 public boolean isPhasePresentInList(String phaseName) {
	        List<WebElement> phaseList = driver.findElements(By.xpath("//h5[text()='" + phaseName + "']"));
	        return phaseList.size() > 0;
	    }

	    // Method to navigate through pages and find the park
	 public boolean searchForPhaseInPaginatedList(String phaseName) throws InterruptedException {
	        do {
	            if (isPhasePresentInList(phaseName)) {
	                return true;
	            }
	            
	            scrollToBottom();
	            Thread.sleep(2000);
	            // If park not found, click the Next button (pagination)
	            if (btn_Next.isEnabled()) {
	                btn_Next.click();
	            } else {
	                break;
	            }
	        } while (true);
	        return false;
	    }
	    

	
	
	
	
	
	public boolean viewPark(String parkName) throws InterruptedException {
		boolean parkFound = false;

		// loop to handle pagination
		do {
			// Step 1: Locate the park by its name
			List<WebElement> parkList = driver.findElements(By.xpath("//h5[text()='" + parkName + "']"));

			// Check if the park is present
			if (parkList.size() > 0) {
				// Park is found; get the ID from the corresponding edit icon
				WebElement parkElement = parkList.get(0); // Assuming the first match is the desired one

				//  Locate the corresponding edit icon using the park's position
				WebElement editIcon = parkElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'park-details')]"));

				// Click the edit icon
				editIcon.click();
				parkFound = true; // Mark park as found
				break; // Exit the loop if the park is found
			} 
			else {
				// No park found, handle scrolling and pagination

				// Scroll up to the top of the page and check again
				scrollToTop(); // Scroll to the top of the page
				parkList = driver.findElements(By.xpath("//h5[text()='" + parkName + "']"));
				if (parkList.size() > 0) {
					// If found after scrolling up
					WebElement parkElement = parkList.get(0);
					WebElement editIcon = parkElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'park-details')]"));
					editIcon.click();
					parkFound = true;
					break;
				}

				// If still not found, scroll down to check the bottom part of the page
				scrollToBottom();
				parkList = driver.findElements(By.xpath("//h5[text()='" + parkName + "']"));
				if (parkList.size() > 0) {
					// If found after scrolling down
					WebElement parkElement = parkList.get(0);
					WebElement editIcon = parkElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'park-details')]"));
					editIcon.click();
					parkFound = true;
					break;
				}

				// Step 6: If the park is still not found, go to the next page
				if (btn_Next.isEnabled()) {
					btn_Next.click(); // Click the Next button to go to the next page
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0, 0);"); // Scroll to the top of the page
					Thread.sleep(2000);// Wait for the page to load
				} else {
					// Next button is not enabled, no more pages to check
					break; // Exit the loop
				}
			}
		} while (!parkFound); // Continue until the park is found or no more pages are available

		return parkFound; // Return whether the park was found
	}
	
	public boolean viewPhase(String phaseName) throws InterruptedException {
		boolean phaseFound = false;

		// loop to handle pagination
		do {
			// Step 1: Locate the park by its name
			List<WebElement> phaseList = driver.findElements(By.xpath("//h5[text()='" + phaseName + "']"));

			// Check if the park is present
			if (phaseList.size() > 0) {
				// Park is found; get the ID from the corresponding edit icon
				WebElement phaseElement = phaseList.get(0); // Assuming the first match is the desired one

				//  Locate the corresponding edit icon using the park's position
				WebElement editIcon = phaseElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'phase-details')]"));

				// Click the edit icon
				editIcon.click();
				phaseFound = true; // Mark park as found
				break; // Exit the loop if the park is found
			} 
			else {
				// No park found, handle scrolling and pagination

				// Scroll up to the top of the page and check again
				scrollToTop(); // Scroll to the top of the page
				phaseList = driver.findElements(By.xpath("//h5[text()='" + phaseName + "']"));
				if (phaseList.size() > 0) {
					// If found after scrolling up
					WebElement parkElement = phaseList.get(0);
					WebElement editIcon = parkElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'phase-details')]"));
					editIcon.click();
					phaseFound = true;
					break;
				}

				// If still not found, scroll down to check the bottom part of the page
				scrollToBottom();
				phaseList = driver.findElements(By.xpath("//h5[text()='" + phaseName + "']"));
				if (phaseList.size() > 0) {
					// If found after scrolling down
					WebElement phaseElement = phaseList.get(0);
					WebElement editIcon = phaseElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'phase-details')]"));
					editIcon.click();
					phaseFound = true;
					break;
				}

				// Step 6: If the park is still not found, go to the next page
				if (btn_Next.isEnabled()) {
					btn_Next.click(); // Click the Next button to go to the next page
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0, 0);"); // Scroll to the top of the page
					Thread.sleep(2000);// Wait for the page to load
				} else {
					// Next button is not enabled, no more pages to check
					break; // Exit the loop
				}
			}
		} while (!phaseFound); // Continue until the park is found or no more pages are available

		return phaseFound; // Return whether the park was found
	}
	
	public boolean viewBuilding(String buildingName) throws InterruptedException {
		boolean buildingFound = false;

		// loop to handle pagination
		do {
			// Step 1: Locate the park by its name
			List<WebElement> buildingList = driver.findElements(By.xpath("//h5[text()='" + buildingName + "']"));

			// Check if the park is present
			if (buildingList.size() > 0) {
				// Park is found; get the ID from the corresponding edit icon
				WebElement buildingElement = buildingList.get(0); // Assuming the first match is the desired one

				//  Locate the corresponding edit icon using the park's position
				WebElement viewIcon = buildingElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'building-details')]"));

				// Click the edit icon
				viewIcon.click();
				buildingFound = true; // Mark park as found
				break; // Exit the loop if the park is found
			} 
			else {
				// No park found, handle scrolling and pagination

				// Scroll up to the top of the page and check again
				scrollToTop(); // Scroll to the top of the page
				buildingList = driver.findElements(By.xpath("//h5[text()='" + buildingName + "']"));
				if (buildingList.size() > 0) {
					// If found after scrolling up
					WebElement buildingElement = buildingList.get(0);
					WebElement viewIcon = buildingElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'building-details')]"));
					viewIcon.click();
					buildingFound = true;
					break;
				}

				// If still not found, scroll down to check the bottom part of the page
				scrollToBottom();
				buildingList = driver.findElements(By.xpath("//h5[text()='" + buildingName + "']"));
				if (buildingList.size() > 0) {
					// If found after scrolling down
					WebElement buildingElement = buildingList.get(0);
					WebElement viewIcon = buildingElement.findElement(By.xpath("./following::a[contains(@class, 'text-primary') and contains(@href, 'building-details')]"));
					viewIcon.click();
					buildingFound = true;
					break;
				}

				// Step 6: If the park is still not found, go to the next page
				if (btn_Next.isEnabled()) {
					btn_Next.click(); // Click the Next button to go to the next page
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0, 0);"); // Scroll to the top of the page
					Thread.sleep(2000);// Wait for the page to load
				} else {
					// Next button is not enabled, no more pages to check
					break; // Exit the loop
				}
			}
		} while (!buildingFound); // Continue until the park is found or no more pages are available

		return buildingFound; // Return whether the park was found
	}

	public void scrollToBottom() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(2000);
	}
	public void scrollToTop() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);"); 
		Thread.sleep(2000); 
	}
}
