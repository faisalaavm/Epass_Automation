package pageObjects;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CreateParkAdminPage extends BasePage{
	WebDriverWait wait;

	public CreateParkAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	//Locating all web elements in the Create Park Admin page
	
    @FindBy(xpath="//span[normalize-space()='Go to parks']") WebElement goToPark;
    @FindBy(xpath="(//div[@class='d-flex flex-column gap-0 card-wrap'])[1]//h5") WebElement firstParkInTheList;
	//function to get the name of the first parkk displayed in the list
		public String getFirstParkName() {
			wait.until(ExpectedConditions.visibilityOf(firstParkInTheList));
			return  firstParkInTheList.getText();
		}
    
	//Function to get the eye icon for a specific park name    
//    public WebElement nameofpark(String xparknm) {
//        String parkname = xparknm;
//        String xpath1 = "//h5[normalize-space()='"
//        		 + parkname + "']/ancestor::div[1]/following-sibling::div//a[1]//*[name()='svg']" ;
//        WebElement eyeIcon=driver.findElement(By.xpath(xpath1));
//        return eyeIcon;
//    }
    
    @FindBy(xpath="(//div[@class='d-flex flex-column gap-0 card-wrap'])[1]//following-sibling::div//a[1]//*[name()='svg']")
	WebElement eyeIconForFirstParkInTheList;
	
	@FindBy(xpath="//a[@role='button']") WebElement createAdminButton;	
	
	@FindBy(xpath="//h4[@class='main-title mb-0' and normalize-space()='Create Park Admin']") WebElement createParkAdminPageHeader; //This is the header of the Create Park Admin page	
	
	@FindBy(xpath="//input[@placeholder='Enter username for park admin login']") WebElement parkUsername;
	@FindBy(xpath="//input[@placeholder='Enter password for park admin login']") WebElement parkPassword;
	@FindBy(xpath="//input[@placeholder='Re-enter password for park admin login']") WebElement parkConfirmPassword;
	@FindBy(xpath="//input[@placeholder='Enter contact name for park admin']") WebElement parkContactName;
	@FindBy(xpath="//input[@placeholder='Enter email id for park admin contact']") WebElement parkEmail;
	@FindBy(xpath="//input[@placeholder='Enter alternate email id for park admin contact']") WebElement parkalterEmail;
	@FindBy(xpath="//input[@placeholder='Enter contact number for park admin']") WebElement parkContactNumber;
	@FindBy(xpath="//button[text()='Create Park Admin']") WebElement submitParkAdminButton;
	
	//Field required validations
	@FindBy(xpath="//div[text()='Username is required']") WebElement usernameRequiredvalidation;
	@FindBy(xpath="//div[@class='text-danger mb-3' and normalize-space()='Password is required']") 
	WebElement passwordRequiredvalidation;
	@FindBy(xpath="//div[@class='text-danger' and normalize-space()='Contact name is required']") 
	WebElement contactNameRequiredvalidation;
	@FindBy(xpath="//div[normalize-space()='Email id is required']") WebElement emailIdRequiredvalidation;
	@FindBy(xpath="//div[normalize-space()='Contact number is required']") WebElement contactNumberRequiredvalidation;
		
	//Validation messages for different fields	
	@FindBy(xpath="//div[contains(text(), 'Username should not start')]") WebElement usernamestartswithNumber;
	@FindBy(xpath="//div[text()='Username must contain only alphanumeric characters']") WebElement usernameasSpecialchar;
	@FindBy(xpath="//div[text()='Password length must be greater than 8 characters']") WebElement passwdLessthanEight;
	@FindBy(xpath="//div[text()='Password must contain at least one uppercase letter, one lowercase letter, one numeric digit, and one special character (@, $, !, & etc.)']") 
	WebElement InvalidPassword;
	@FindBy(xpath="//div[text()='Passwords do not match']") WebElement passwdNotMatchingvalidation;
	@FindBy(xpath="//div[text()='Contact name must start with a letter and contain only alphanumeric characters']") 
	WebElement specialCharInContactname;
	@FindBy(xpath="//div[text()='Please enter a valid email id']") WebElement invalidEmail;
	@FindBy(xpath="//div[text()='Please enter a valid alternate email id']") WebElement invalidAlternateEmail;
	@FindBy(xpath="//div[text()='Contact number must contain only numeric characters']") WebElement nonnumericContactNumber;
	@FindBy(xpath="//div[text()='Contact number must contain at least 10 numeric characters']") 
	WebElement contactNumberlessthanTen;
	@FindBy(xpath="//div[text()='Continuous sequence of 10 same numbers is not allowed']") WebElement contactNumberSequence;
	@FindBy(xpath="//div[text()='Park Admin created successfully ']") WebElement parkAdminCreationSuccessMessage;
	
	@FindBy(xpath = "//div[text()='User with same Email exists']")
	WebElement userWithSameEmailExists;
	@FindBy(xpath = "//div[text()='Park Admin with same Username exists']")
	WebElement parkAdminWithSameUsernameExists;	
	@FindBy(xpath="//div[@class='text-danger' and contains(text(), 'Contact name is required')]") 
	WebElement contactnameRequiredvalidation; 	
 
	
	//For random variables
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomNumeric() {
		String generatedNumeric = RandomStringUtils.randomNumeric(10);
		return generatedNumeric;
	}
	
	 //Functions for clicking buttons
	//********************************
	public void goToPark_button() {
		wait.until(ExpectedConditions.elementToBeClickable(goToPark));
		goToPark.click();
	}
	//eyeicon functionality
//	public void eyeIcon_button(String parknam) {
//		String xparknm=parknam;
//		WebElement eyeIcon=nameofpark(xparknm);  //calling the function to get the specific park name
//		wait.until(ExpectedConditions.elementToBeClickable(eyeIcon));
//		eyeIcon.click();
//	}
	public void eyeIcon_button() {
		wait.until(ExpectedConditions.elementToBeClickable(eyeIconForFirstParkInTheList));
		eyeIconForFirstParkInTheList.click();
	}
	
	public void createParkAdmin_button() {
		wait.until(ExpectedConditions.elementToBeClickable(createAdminButton));
		createAdminButton.click();
	}
	
	//Function to get the headers in the Create Park Admin page
	public String getCreateParkAdminPageHeader() {
		wait.until(ExpectedConditions.visibilityOf(createParkAdminPageHeader));
		return createParkAdminPageHeader.getText();
	}
	
	//Function to get the default dropdown value displauyed on the Create Park Admin page
		public String getDropdownValue() {		
			WebElement selectedValue = driver.findElement(By.xpath("//div[contains(@class, 'css-1dimb5e-singleValue')]"));
			wait.until(ExpectedConditions.visibilityOf(selectedValue));
			String defaultDropdownValue = selectedValue.getText();
			return defaultDropdownValue;
		}
	
	//Functions for inputting fields
    //******************************
	public void setParkUserName(String parkusernam) {
		wait.until(ExpectedConditions.visibilityOf(parkUsername)).clear(); // Clear the field before entering a new value
		parkUsername.sendKeys(parkusernam);
	}
    public void setParkPassword(String parkpassw) {
    	wait.until(ExpectedConditions.visibilityOf(parkPassword)).clear(); // Clear the field before entering a new value
    	parkPassword.sendKeys(parkpassw);
	}
    public void setParkConfirmPassword(String parkconfirmpassword) {
    	wait.until(ExpectedConditions.visibilityOf(parkConfirmPassword)).clear(); // Clear the field before entering a new value
    	parkConfirmPassword.sendKeys(parkconfirmpassword);
	}	
    public void setParkContactName(String parkcontactname) {
    	wait.until(ExpectedConditions.visibilityOf(parkContactName)).clear(); // Clear the field before entering a new value
    	parkContactName.sendKeys(parkcontactname);
    }
    public void setParkEmail(String parkemail) {
    	wait.until(ExpectedConditions.visibilityOf(parkEmail)).clear(); // Clear the field before entering a new value
    	parkEmail.sendKeys(parkemail);
    }
    public void setParkAlterEmail(String parkalteremail) {
    	wait.until(ExpectedConditions.visibilityOf(parkalterEmail)).clear(); // Clear the field before entering a new value
    	parkalterEmail.sendKeys(parkalteremail);
    }
    public void setParkContactNumber(String parkcontactnumber) {
    	wait.until(ExpectedConditions.visibilityOf(parkContactNumber)).clear(); // Clear the field before entering a new value
    	parkContactNumber.sendKeys(parkcontactnumber);
    }
    public void setSubmitParkAdminButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(submitParkAdminButton));
    	submitParkAdminButton.click();
    }
    
    //Window scrollbar functions
    //**************************
    public void scrollToBottom() {
    	JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)"); 
	}
    public void scrollToTop() {
    	JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,100)"); 
    }
    
   //Required Validation Functions for different fields
    //*****************************************
    public  String getUsernameBlankvalidation() {
    	wait.until(ExpectedConditions.visibilityOf(usernameRequiredvalidation));
    	return usernameRequiredvalidation.getText();
    }
    public  String getPasswordBlankvalidation() {
		wait.until(ExpectedConditions.visibilityOf(passwordRequiredvalidation));
		return passwordRequiredvalidation.getText();
	}
    public  String getContactNameBlankvalidation() {
		wait.until(ExpectedConditions.visibilityOf(contactNameRequiredvalidation));
		return contactNameRequiredvalidation.getText();
	}
    public  String getEmailIdBlankvalidation() {
    	wait.until(ExpectedConditions.visibilityOf(emailIdRequiredvalidation));
		return emailIdRequiredvalidation.getText();
    }
    public  String getContactNumberBlankvalidation() {
		wait.until(ExpectedConditions.visibilityOf(contactNumberRequiredvalidation));
		return contactNumberRequiredvalidation.getText();
	}
    
    
    
    
    //Validation Functions for different fields
    //*****************************************
    
    public String getUsernameStartsWithNumberValidation() {
    	wait.until(ExpectedConditions.visibilityOf(usernamestartswithNumber));
        return usernamestartswithNumber.getText();
    }
    public String getUsernameAsSpecialCharValidation() {   	
        return usernameasSpecialchar.getText();
    }
	public String getPasswordLessThanEightValidation() {
		return passwdLessthanEight.getText();
	}
	public String getInvalidPasswordValidation() {			
		return InvalidPassword.getText();
	}
	public  String passworddNotMatchingvalidation() {
    	return passwdNotMatchingvalidation.getText();
    } 
//	public  String contactnameBlankvalidation() {
//    	wait.until(ExpectedConditions.visibilityOf(contactnameRequiredvalidation));
//    	return contactnameRequiredvalidation.getText();
//    }
    public String getSpecialCharInContactNameValidation() {
    	wait.until(ExpectedConditions.visibilityOf(specialCharInContactname));
        return specialCharInContactname.getText();
    }
    
    public String getInvalidEmailValidation() {
    	wait.until(ExpectedConditions.visibilityOf(invalidEmail));
        return invalidEmail.getText();
    }
    public String getInvalidAlternateEmailValidation() {
    	wait.until(ExpectedConditions.visibilityOf(invalidAlternateEmail));
        return invalidAlternateEmail.getText();
    }
    public String getNonNumericContactNumberValidation() {
    	wait.until(ExpectedConditions.visibilityOf(nonnumericContactNumber));
        return nonnumericContactNumber.getText();
    }
    public String getContactNumberLessThanTenValidation() {
    	wait.until(ExpectedConditions.visibilityOf(contactNumberlessthanTen));
		return contactNumberlessthanTen.getText();
	}
    public String getContactNumberSequenceValidation() {
    	wait.until(ExpectedConditions.visibilityOf(contactNumberSequence));
        return contactNumberSequence.getText();
    }
    public String getParkAdminCreationSuccessMessage() {
    	wait.until(ExpectedConditions.visibilityOf(parkAdminCreationSuccessMessage));
		return parkAdminCreationSuccessMessage.getText();
    }
    
    public String getUserWithSameEmailExistsMessage() {
    	wait.until(ExpectedConditions.visibilityOf(userWithSameEmailExists));
        return userWithSameEmailExists.getText();
    }
    public String getParkAdminWithSameUsernameExistsMessage() {
    	wait.until(ExpectedConditions.visibilityOf(parkAdminWithSameUsernameExists));
        return parkAdminWithSameUsernameExists.getText();
    }
}

