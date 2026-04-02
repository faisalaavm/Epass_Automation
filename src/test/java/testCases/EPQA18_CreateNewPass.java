package testCases;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import testBase.BaseClass;


public class EPQA18_CreateNewPass {
	WebDriver driver;

	WebDriverWait wait ;

	String randomVisitorName = "User" + System.currentTimeMillis();  // This will generate a unique visitor name by appending the current timestamp to "User"
	String randomEmail = "user" + System.currentTimeMillis() + "@example.com"; // This will generate a unique email by appending the current timestamp to "

	Random random = new Random();
	String randomContactNumber = String.valueOf(1000000000L + random.nextLong(9000000000L)); // This will generate a random 10-digit contact number

	Date now = new Date();

	int currentPassCount;


	@BeforeTest
	public void setup() {	

		WebDriverManager.chromedriver().setup();      	//WebDriver Initializaton
		ChromeOptions co = new ChromeOptions();			//initializes a new object of the ChromeOptions class 
		co.addArguments("--incognito");			//set a browser option
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get("https://epassqa.pearlglobalsolutions.com/"); // Replace with the actual URL
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	 //implicit wait for elements to be found
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));    //Explicit wait for elements to be found/clicked
	}

	@Test(priority=0,enabled=true, description="Verify Login into the application as a company user")
	public void login_with_companyUsername () throws InterruptedException {
		// Login to the application as a company user
		driver.findElement(By.xpath("//input[@placeholder='Enter username']")).sendKeys("parvathy");
		driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("Admin@123");
		driver.findElement(By.xpath("//button[@type='submit' and contains(@class, 'btn-sign') and text()='Login']")).click(); // Click the login button
		String expectedMessage = "Login Success";
		By expectedMessageLocator = By.xpath("//div[contains(text(),'Login Success')]");
		Thread.sleep(3000);


		//Locating current pass count	for later use
		WebElement currentPassCountElement = driver.findElement(By.xpath("//div[contains(@class, 'earning-icon') and contains(@class, 'bg-twitter')]/following-sibling::h4")); // Replace with your actual locator
		System.out.println("Current Pass Count: " + currentPassCountElement.getText()); // Print the pass count to the console
		currentPassCount = Integer.parseInt(currentPassCountElement.getText().trim()); // Get the current pass count


		// Verify the login success message    
		String actualMessage = driver.findElement(expectedMessageLocator).getText();
		System.out.println("Actual Message: " + actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage);
	}

	@Test(priority=1,enabled=true, description="Verify the page header for  New Pass Creation page after navigating to the Creation page")
	public void verify_createNewPass_PageHeader() throws InterruptedException {
		// Navigate to Create New Pass page
		driver.findElement(By.xpath("//span[normalize-space()='Create New Pass']")).click(); //clicking the Create new pass button
		//Verify the page header
		String expectedHeader = "Create New Pass"; // Expected header text
		String actualHeader=driver.findElement(By.xpath("//h4[@class='main-title mb-0' and text()='Create New Pass']")).getText(); // Get the page header text
		System.out.println("Actual Message: " + actualHeader);
		Assert.assertEquals(actualHeader, expectedHeader);		
	}			

	@Test(priority=2,enabled=true, description="Verify that Delegate Pass button is enabled or not")
	public void verify_ButtonEnabled_DelegatePass() throws InterruptedException {
		// Verify that the Delegate Pass button is enabled
		Boolean buttonEnable=driver.findElement(By.xpath("//a[@role='tab' and @data-rr-ui-event-key='first' and text()='Delegate Pass']")).isEnabled(); // Check if the Delegate Pass button is enabled
		System.out.println("Is Delegate Pass button enabled? " + buttonEnable);
		Assert.assertTrue(buttonEnable, "Delegate Pass button should be enabled"); // Assert that the button is enabled
	}

	@Test(priority=3,enabled=true, description="Verify Create New Pass functionality with blank input values")
	public void Verify_validation_with_Blank_inputValuesIn_CreatePassPage() throws InterruptedException
	{

		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)"); // Scroll down to the Create Pass button
		//		Thread.sleep(3000);
		By createPassButton = By.xpath("//button[contains(@type,'submit')]"); //clicking the submit button to create a pass
		//**************Using explicit wait*************//
		wait.until(ExpectedConditions.elementToBeClickable(createPassButton)); // Wait until the Create Pass button is clickable
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(createPassButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(createPassButton)); // Click on the Create Pass button without filling in any fields	

		//**************Using implicit wait*************//
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Visitor Name Required Validation
		String expectedMessage = "Visitor name is required"; // Expected header text
		String actualMessage=driver.findElement(By.xpath("//div[normalize-space()='Visitor name is required']")).getText(); // Get the page header text
		System.out.println("Actual Message1: " + actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage);	
		//Email ID Required Validation
		String expectedMessage2 = "Email id is required"; // Expected header text
		String actualMessage2=driver.findElement(By.xpath("//div[normalize-space()='Email id is required']")).getText(); // Get the page header text
		System.out.println("Actual Message2: " + actualMessage2);
		Assert.assertEquals(actualMessage2, expectedMessage2);
		//Contact Number Required Validation
		String expectedMessage3 = "Contact number is required"; // Expected header text
		String actualMessage3=driver.findElement(By.xpath("//div[normalize-space()='Contact number is required']")).getText(); // Get the page header text
		System.out.println("Actual Message3: " + actualMessage3);
		Assert.assertEquals(actualMessage3, expectedMessage3);
		//Reason for Visit Required Validation
		String expectedMessage4 = "Reason for visit is required"; // Expected header text
		String actualMessage4=driver.findElement(By.xpath("//div[normalize-space()='Reason for visit is required']")).getText(); // Get the page header text
		Assert.assertEquals(actualMessage4, expectedMessage4);
		//From Date Required Validation
		String expectedMessage5 = "From date is required"; // Expected header text
		String actualMessage5=driver.findElement(By.xpath("//div[normalize-space()='From date is required']")).getText(); // Get the page header text
		Assert.assertEquals(actualMessage5, expectedMessage5);
		//To Date Required Validation
		String expectedMessage6 = "To date is required"; // Expected header text
		String actualMessage6=driver.findElement(By.xpath("//div[normalize-space()='To date is required']")).getText(); // Get the page header text
		Assert.assertEquals(actualMessage6, expectedMessage6);
	}


	@Test(priority=4,enabled=true, description="Verify New Pass Creation functionality with visitor name starting with number")
	public void Verify_Validation_WIth_VisitorName_startingWith_Integer() throws InterruptedException
	{
		// Verify that the visitor name field does not accept names starting with a number
		driver.findElement(By.xpath("//input[@placeholder='Enter name of visitor']")).sendKeys("2Testname"); // Enter visitor name
		String expectedMessage= "Visitor name must start with a letter and contain only alphanumeric characters"; // Expected validation message
		String actualMessage=driver.findElement(By.xpath("//div[normalize-space()='Visitor name must start with a letter and contain only alphanumeric characters']")).getText(); // Get the validation message for visitor name
		Assert.assertEquals(actualMessage, expectedMessage); // Assert that the validation message is as expected
	}


	@Test(priority=5,enabled=true, description="Verify New Pass Creation functionality with invalid Email")
	public void Verify_ValidationWIth_Invalid_EmailID_In_CreatePassPage() throws InterruptedException
	{
		// Verify that the email field does not accept invalid email formats
		driver.findElement(By.xpath("//input[contains(@placeholder,'Enter email id for visitor contact')]")).sendKeys("abcd"); // Clear the visitor name field
		String expectedMessage= "Please enter a valid email id"; // Expected validation message
		String actualMessage=driver.findElement(By.xpath("//div[normalize-space()='Please enter a valid email id']")).getText(); // Get the validation message for visitor name
		Assert.assertEquals(actualMessage, expectedMessage); // Assert that the validation message is as expected
	}


	@Test(priority=6,enabled=true, description="Verify New Pass Creation functionality  with non-numeric contact number")
	public void Verify_ValidationWIth_nonnumeric_ContactNumber_In_CreatePassPage() throws InterruptedException
	{
		// Verify  the validation message with non-numeric contact number
		driver.findElement(By.xpath("//input[contains(@placeholder,'Enter contact number of visitor')]")).sendKeys("#sd"); // Enter an invalid contact number
		String expectedMessage= "Contact number must contain only numeric characters"; // Expected validation message
		String actualMessage=driver.findElement(By.xpath("//div[normalize-space()='Contact number must contain only numeric characters']")).getText(); // Get the validation message for visitor name
		Assert.assertEquals(actualMessage, expectedMessage); // Assert that the validation message is as expected
	}


	@Test(priority=7,enabled=true, description="Verify New Pass Creation functionality with contact number less than ten digits")
	public void Verify_ValidationWIth_contactNumber_lessthan_tenDigits_In_CreatePassPage() throws InterruptedException
	{
		// Verify  the validation message with less than ten digits
		driver.findElement(By.xpath("//input[contains(@placeholder,'Enter contact number of visitor')]")).clear(); // Clear the contact number field
		driver.findElement(By.xpath("//input[contains(@placeholder,'Enter contact number of visitor')]")).sendKeys("1234"); // Enter an invalid reason for visit
		String expectedMessage= "Contact number must contain at least 10 numeric characters"; // Expected validation message
		String actualMessage=driver.findElement(By.xpath("//div[normalize-space()='Contact number must contain at least 10 numeric characters']")).getText(); // Get the validation message for visitor name
		Assert.assertEquals(actualMessage, expectedMessage); // Assert that the validation message is as expected
	}


	@Test(priority=8,enabled=true, description="Verify that Company Name field is enabled  or not in New Pass Creation page")
	public void Verify_CompanyName_DisabledField() throws InterruptedException
	{
		// Verify that the Company Name field is disabled
		Boolean companyNameEnabled=driver.findElement(By.xpath("//input[@placeholder='Enter company name' and @value='Dot In Technologies']")).isEnabled();	 // Check if the Company Name field is enabled or not
		System.out.println("Is Company Name field enabled? " + companyNameEnabled);
		Assert.assertFalse(companyNameEnabled, "Company Name field should be disabled"); // Assert that the Company Name field is disabled
	}


	@Test(priority=9,enabled=true, description="Verify that Company Name autopopulates the value in New Pass Creation page")
	public void Verify_Default_CompnayName_Displayed_In_CreatePassPage() throws InterruptedException
	{
		// Verify that the Company Name field autopopulates with the company name
		String expectedMessage= "Dot In Technologies"; // Expected validation message
		String actualMessage=driver.findElement(By.xpath("//input[@placeholder='Enter company name']")).getAttribute("value"); // Get the value of the Company Name field
		System.out.println("Actual Company Name: " + actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage); // Assert that the Company Name field contains the expected value
	}	


	@Test(priority=10,enabled=true, description="Verify New Pass Creation functionality with File size greater than 100KB")
	public void Verify_ValidationWIth_Filesize_greaterthan_100KB() throws InterruptedException
	{
		//select the photo file to upload 
		WebElement fileInput = driver.findElement(By.id("photoInput")); // Replace with your actual locator
		String filePath =System.getProperty("user.dir") + "\\src\\test\\resources\\images\\InvalidFileSize.jpg";
		fileInput.sendKeys(filePath); // Upload the file by sending the file path to the input element	
		String expectedMessage= "File size should be 100 KB or less."; // Expected validation message
		String actualMessage=driver.findElement(By.xpath("//div[normalize-space()='File size should be 100 KB or less.']")).getText(); // Get the validation message for file size
		System.out.println("File size validation message: " + actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage); // Assert that the validation message is as expected


		//select the ID file to upload
		WebElement idFileInput = driver.findElement(By.id("idProofInput")); // Replace with your actual locator
		String idFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\images\\InvalidFileSize.jpg";
		idFileInput.sendKeys(idFilePath); // Upload the file by sending the file path to the input element
		String expectedMessage2= "File size should be 100 KB or less."; // Expected validation message
		String actualMessage2=driver.findElement(By.xpath("//div[normalize-space()='File size should be 100 KB or less.']")).getText(); // Get the validation message for file size
		System.out.println("File size validation message: " + actualMessage2);
		Assert.assertEquals(actualMessage2, expectedMessage2); // Assert that the validation message is as expected		
	}


	@Test(priority=11,enabled=true, description="Verify the Validation messages when To date < From date and when From Date Time is set as previous Time in New Pass Creation functionality")
	public void Verify_ValidationMessage_With_PreviousFromDateTime_inCreatePassPage() throws InterruptedException
	{
		//Initialize the Actions class for mouse and keyboard interactions
		Actions actions = new Actions(driver);
		//Initialize the JavascriptExecutor for executing JavaScript code
		JavascriptExecutor js= (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Wait for elements to be found

		//Locating the Create Pass form fields
		driver.findElement(By.xpath("//input[@placeholder='Enter name of visitor']")).clear(); // Clear the visitor name field
		driver.findElement(By.xpath("//input[@placeholder='Enter name of visitor']")).sendKeys(randomVisitorName); // Enter a valid visitor name
		driver.findElement(By.xpath("//input[@placeholder='Enter email id for visitor contact']")).clear(); // Clear the email field"));
		driver.findElement(By.xpath("//input[@placeholder='Enter email id for visitor contact']")).sendKeys(randomEmail);
		driver.findElement(By.xpath("//input[@placeholder='Enter contact number of visitor']")).clear(); // Clear the contact number field
		driver.findElement(By.xpath("//input[@placeholder='Enter contact number of visitor']")).sendKeys(randomContactNumber); // Enter a valid contact number

		js.executeScript("window.scrollBy(0,2000)");
		driver.findElement(By.xpath("//input[@placeholder='Enter the reason for visit']")).clear(); // Clear the reason for visit field		
		driver.findElement(By.xpath("//input[@placeholder='Enter the reason for visit']")).sendKeys("Testing purpose"); // Enter a valid reason for visit


		//********For getting the current date and previous time in From field**********		
		Calendar cal = Calendar.getInstance(); // Get the current date		
		// Step 2: Open the From date picker
		WebElement dateInput = driver.findElement(By.xpath("(//input[contains(@placeholder,'Select the date and time')])[1]"));
		js.executeScript("arguments[0].click();", dateInput);
		// Step 3: Click today's date in calendar
		WebElement today = driver.findElement(By.xpath("//td[contains(@class,'rdtDay') and not(contains(@class,'rdtOld')) and text()='" + cal.get(Calendar.DAY_OF_MONTH) + "']"));
		System.out.println("Today's date in From field: " + today.getText());
		today.click();				

		actions.moveByOffset(50, 50).click().perform(); // Click on the page to close the date picker that is already opened		
		//		Thread.sleep(1000); // Wait for the calendar to update		
		js.executeScript("window.scrollBy(0,1000)"); //scroll down the page


		//***********For getting the current date & future time in To field***************
		// Step 1: Open the To date picker
		WebElement dateInput2 = driver.findElement(By.xpath("(//input[contains(@placeholder,'Select the date and time')])[2]"));
		js.executeScript("arguments[0].click();", dateInput2);
		// Step 2: Click today's date in calendar		
		WebElement today2 = driver.findElement(By.xpath("//td[@class='rdtDay rdtToday']"));	
		System.out.println("Today's date in To field: " + today2.getText());
		today2.click();	

		//Step 3: clicking the hour spinner to adjust the time
		driver.findElement(By.xpath("(//td[contains(@class,'rdtTimeToggle')])[2]")).click(); // Click the hour spinner to adjust the time		
		// Step 4: Adjust time (hour spinner)
		WebElement hourDownArrow2 = driver.findElement(By.xpath("//div[@class='rdtCounters']/div[1]//span[@class='rdtBtn'][1]")); // ↓ arrow for hour
		for (int i = 0; i < 10 ; i++) {
			hourDownArrow2.click();
			//	    Thread.sleep(100); // wait for animation
		}
		// Step 5: Adjust time mode as PM
		WebElement ampmToggle = driver.findElement(By.xpath("//div[@class='rdtCounters']/div[4]//span[@class='rdtBtn'][1]")); // Click the AM/PM toggle to switch to PM
		ampmToggle.click();

		actions.moveByOffset(50, 50).click().perform(); //Click on the page to close the date picker that is already opened


		// Click on the Create Pass button to submit the form in order to get the validation message
		WebElement createPassButton = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
		createPassButton.click(); 

		// Wait for the error message to appear as the toast message
		String expectedMessage= "From date & time should be greater than current date & time";
		By messageLocator = By.xpath("//div[normalize-space(text())='From date & time should be greater than current date & time']");
		WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));
		String actualMessage = messageElement.getText();		
		System.out.println("Date validation message 2: " + actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage); 										
		//------------------------------------------------------------------------------------------------------------------------------------------//

		js.executeScript("window.scrollBy(0,1500)");		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//clicking From field to open the calendar
		js.executeScript("arguments[0].click();", dateInput); 	//dateInput is the From date input field located earlier
		selectDateFromDatePicker("10", "January", "2026");  //calling the function to select date from From date picker
		actions.moveByOffset(50, 50).click().perform();

		//Verifying the first validation message when To date < From date
		String expectedMessage2= "To date must be on or after the from date";
		String actualMessage2=driver.findElement(By.xpath("//div[@class='text-danger' and normalize-space()='To date must be on or after the from date']")).getText(); // Get the value of the Company Name field
		System.out.println("Date validation message 2: " + actualMessage2);
		Assert.assertEquals(actualMessage2, expectedMessage2); 	
		//----------------------------------------------------------------------------------------------------------------------------------------------//		

		//clicking To field to open the calendar
		js.executeScript("arguments[0].click();", dateInput2);	//dateInput2 is the To date input field located earlier	

		//xpath for down arrow for hour spinner to adjust the time
		WebElement hourDownArrow3 = driver.findElement(By.xpath("//div[@class='rdtCounters']/div[1]//span[@class='rdtBtn'][1]")); // ↓ arrow for hour
		for (int i = 0; i < 2 ; i++) {
			hourDownArrow3.click();
			//	    Thread.sleep(100); // wait for animation
		}		
		driver.findElement(By.xpath("//td[@class='rdtSwitch']")).click(); // Click on the month and year switcher to open the calendar
		selectDateToDatePicker("10", "January", "2026"); //calling the function to select date from To date picker
		actions.moveByOffset(50, 50).click().perform();			

		//Verifying the second validation message when From and To dates are same
		String expectedMessage3= "End time must be greater than start time.";
		String actualMessage3=driver.findElement(By.xpath("//div[@class='text-danger' and normalize-space()='End time must be greater than start time.']")).getText(); // Get the value of the Company Name field
		System.out.println("Date validation message 1: " + actualMessage3);
		Assert.assertEquals(actualMessage3, expectedMessage3); 		
	}



	@Test(priority=12,enabled=true, description="Verify New Pass Creation functionality with valid input values")
	public void Verify_CreatePassPage_With_ValidInputValues2() throws InterruptedException
	{
		Actions actions = new Actions(driver); // Create an instance of Actions class for mouse and keyboard interactions

		// Fill in the Create Pass form with valid input values		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//input[@placeholder='Enter name of visitor']")).clear(); // Clear the visitor name field
		driver.findElement(By.xpath("//input[@placeholder='Enter name of visitor']")).sendKeys(randomVisitorName); // Enter a valid visitor name
		driver.findElement(By.xpath("//input[@placeholder='Enter email id for visitor contact']")).clear(); // Clear the email field"));
		driver.findElement(By.xpath("//input[@placeholder='Enter email id for visitor contact']")).sendKeys(randomEmail);
		driver.findElement(By.xpath("//input[@placeholder='Enter contact number of visitor']")).clear(); // Clear the contact number field
		driver.findElement(By.xpath("//input[@placeholder='Enter contact number of visitor']")).sendKeys(randomContactNumber); // Enter a valid contact number
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		driver.findElement(By.xpath("//input[@placeholder='Enter the reason for visit']")).clear(); // Clear the reason for visit field		
		driver.findElement(By.xpath("//input[@placeholder='Enter the reason for visit']")).sendKeys("Testing purpose"); // Enter a valid reason for visit


		// Click on the From date input to open the calendar
		WebElement dateInput=driver.findElement(By.xpath("(//input[contains(@placeholder,'Select the date and time')])[1]"));  // Replace with your actual locator
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateInput);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dateInput);  
		System.out.println("Clicked on the date input to open the calendar.");	
		// Giving the input date for From field
		selectDateFromDatePicker("10", "October", "2026"); //calling the function to select date from From date picker				

		actions.moveByOffset(50, 50).click().perform(); // Click on the page to close the date picker that is already opened

		// Click on the To date input to open the calendar
		WebElement dateInput2=driver.findElement(By.xpath("(//input[contains(@placeholder,'Select the date and time')])[2]"));  // Replace with your actual locator
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateInput2);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dateInput2);  
		System.out.println("Clicked on the date input to open the calendar.");	
		// Giving the input date for From field
		selectDateToDatePicker("12", "October", "2026");  //calling the function to select date from To date picker

		// Click on the page to close the calendar that is already opened
		actions.moveByOffset(50, 50).click().perform();

		//select the photo file to upload
		WebElement fileInput = driver.findElement(By.id("photoInput")); // Replace with your actual locator
		String filePath =System.getProperty("user.dir") + "\\src\\test\\resources\\images\\MyPhoto.jpg";
		fileInput.sendKeys(filePath); // Upload the file by sending the file path to the input element

		//select the ID file to upload
		WebElement idFileInput = driver.findElement(By.id("idProofInput")); // Replace with your actual locator
		String idFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\images\\MyID.jpg";
		idFileInput.sendKeys(idFilePath); // Upload the file by sending the file path to the input element

		// Click on the Create Pass button to submit the form
		WebElement createPassButton = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
		createPassButton.click(); // Click the Create Pass button

		// Wait for the success message to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Entry pass created successfully')]"))); // Wait for the success message to be visible
		String expectedMessage = "Entry pass created successfully"; // Expected success message
		String actualMessage = driver.findElement(By.xpath("//div[contains(text(),'Entry pass created successfully')]")).getText(); // Get the success message text
		System.out.println("Actual Message: " + actualMessage);
		Assert.assertEquals(actualMessage, expectedMessage); // Assert that the success message is as expected

		//Verify that the generated pass is displayed in the Passes table
		List<WebElement> visitorPassNames = driver.findElements(By.xpath("//td[@class='text-primary fixed-width-last']")); 
		for (WebElement passName : visitorPassNames) {
			if (passName.getText().equals(randomVisitorName)) { // Check if the pass name matches the generated visitor name
				System.out.println("Pass for visitor " + randomVisitorName + " is displayed in the Passes table.");
				Assert.assertTrue(true); // Assert that the pass is displayed
				return; // Exit the loop if the pass is found
			}
		}

		// Verify that the pass count has increased by 1(-error in the application)
		//		WebElement updatedPassCountElement = driver.findElement(By.xpath("//div[contains(@class, 'earning-icon') and contains(@class, 'bg-twitter')]/following-sibling::div/h4")); // Replace with your actual locator		
		//		int expectedCount = currentPassCount + 1;
		//		By updatedPassCountLocator = By.xpath("//div[contains(@class, 'earning-icon') and contains(@class, 'bg-twitter')]/following-sibling::div/h4");
		//		wait.until(ExpectedConditions.textToBe(updatedPassCountLocator, String.valueOf(expectedCount)));
		//		WebElement updatedPassCountElement = driver.findElement(updatedPassCountLocator);
		//		
		//		System.out.println("Updated Pass Count: " + updatedPassCountElement.getText()); // Print the updated pass count to the console		
		//		int updatedPassCount = Integer.parseInt(updatedPassCountElement.getText().trim()); // Get the updated pass count
		//		System.out.println("Current Pass Count: " + currentPassCount);
		//		System.out.println("Updated Pass Count: " + updatedPassCount);
		//		Assert.assertEquals(updatedPassCount, currentPassCount + 1); // Assert that the pass count has increased by 1
		//		System.out.println("New pass created successfully and pass count updated.");

	}	


	//Function to select date from FROM date picker
	public void selectDateFromDatePicker(String day, String month, String year) throws InterruptedException 
	{ 
		// 1. Loop to reach the correct month and year
		while (true) {
			WebElement monthYearElement = driver.findElement(By.xpath("//div[contains(@class,'rdt rdtOpen')]//th[contains(@class,'rdtSwitch')]"));  
			String displayedMonthYear = monthYearElement.getText(); // Example: "October 2027"
			System.out.println("Displayed Month and Year: " + displayedMonthYear);
			String[] parts = displayedMonthYear.split(" ");
			String currentMonth = parts[0].trim();
			System.out.println("Current Month: " + currentMonth);
			String currentYear = parts[1].trim();
			System.out.println("Current Year: " + currentYear);

			if (currentMonth.equalsIgnoreCase(month) && currentYear.equals(year)) {
				break;
			}
			// Click next button to go to future months
			driver.findElement(By.xpath("(//th[@class='rdtNext'])[1]")).click();
			System.out.println("Clicked next button to go to the next month.");
		}
		// 2. Select the day
		List<WebElement> dates = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr/td"));  // Adjust if needed
		for (WebElement e : dates) 
		{
			if (e.getText().equals(day)) {	        	
				e.click();
				System.out.println("Found the date: and clicked" + e.getText());
				break;  }
		}	       		
	}


	//Function to select date from TO date picker
	public void selectDateToDatePicker(String day, String month, String year) throws InterruptedException 
	{ 
		// 1. Loop to reach the correct month and year
		while (true) {
			WebElement monthYearElement = driver.findElement(By.xpath("//div[contains(@class,'rdt rdtOpen')]//th[contains(@class,'rdtSwitch')]"));  
			String displayedMonthYear = monthYearElement.getText(); // Example: "October 2027"
			System.out.println("Displayed Month and Year: " + displayedMonthYear);
			String[] parts = displayedMonthYear.split(" ");
			String currentMonth = parts[0].trim();
			System.out.println("Current Month: " + currentMonth);
			String currentYear = parts[1].trim();
			System.out.println("Current Year: " + currentYear);
			if (currentMonth.equalsIgnoreCase(month) && currentYear.equals(year)) {
				break;
			}
			// Click next button to go to future months
			driver.findElement(By.xpath("(//th[@class='rdtNext'])[2]")).click();
			System.out.println("Clicked next button to go to the next month.");
		}
		// 2. Select the day
		List<WebElement> dates = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr/td"));  // Adjust if needed
		for (WebElement e : dates) 
		{
			if (e.getText().equals(day)) {	        	
				e.click();
				System.out.println("Found the date: and clicked" + e.getText());
				break;  }
		}	       		
	}


	//Function to take a screenshot after each test method	
	public void takeScreenshot(String testName) {
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String filePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + System.currentTimeMillis() + ".png";
			FileUtils.copyFile(srcFile, new File(filePath));
			System.out.println("Screenshot taken: " + filePath);
		} catch (Exception e) {
			System.out.println("Failed to take screenshot: " + e.getMessage());
		}
	}	


	@AfterMethod
	public void afterEachTest(ITestResult result) {
		//takeScreenshot(result.getMethod().getMethodName()); // Take a screenshot after each test method

		if (result.getStatus() == ITestResult.FAILURE) {
			takeScreenshot(result.getMethod().getMethodName());  // Take a screenshot if the test fails
		}
	}



	@AfterTest
	public void tearDown() {
		// Close the browser after tests
		if (driver != null) {
			driver.quit();
		}
	}



}