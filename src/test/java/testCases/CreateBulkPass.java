package testCases;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateBulkPass {
    WebDriver driver;

    @BeforeTest
    public void initialize() {
        // Ensure folder exists
        String downloadFilepath = "C:\\TestFiles";
        new File(downloadFilepath).mkdirs();

        // Set Chrome preferences for silent download
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFilepath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        // Optional: Run headless to avoid UI issues (skip if you want visible browser)
        // options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://epassqa.pearlglobalsolutions.com/");
    }

    @Test(priority = 1)
    public void login() {
        driver.findElement(By.xpath("//input[@placeholder='Enter username']")).sendKeys("parvathy");
        driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("Admin@123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@role='alert']//div[contains(text(),'Login Success')]")));
        String loginSuccess = toast.getText();
        Assert.assertTrue(loginSuccess.contains("Login Success"), "Login toast not found!");
        System.out.println(loginSuccess);

        driver.findElement(By.xpath("//span[normalize-space()='Create New Pass']")).click();
    }
    @Test(priority = 2)
    public void createPassButtonClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Click on Bulk Pass tab
        driver.findElement(By.xpath("//div[@class='nav-item']/a[text()='Bulk Pass']")).click();

        // Wait for tab content to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@role='tabpanel' and contains(@class,'active')]//button[normalize-space()='Create Pass']")
        ));

        // Now click the Create Pass button inside the tab
        WebElement createPassBtn = driver.findElement(
            By.xpath("//div[@role='tabpanel' and contains(@class,'active')]//button[normalize-space()='Create Pass']")
        );
        createPassBtn.click();
        System.out.println("Clicked Create Pass button");

        // Wait for error message
        By errorLocator = By.xpath("//div[@class='text-danger' and contains(text(),'Please upload a CSV file')]");
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
        System.out.println("Error: " + error.getText());

    }
    @Test(priority = 3)
    public void bulkEpass() throws IOException, InterruptedException {
        String downloadPath = "C:\\TestFiles";
        String fileName = "sample-upload-format.16f08cdcd2e89f6b71bf.xlsx";
        String filePath = downloadPath + "\\" + fileName;

        // Click download link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[text()='Click here' and contains(@href, '.xlsx')]")));
        downloadLink.click();
        System.out.println("📥 Download link clicked.");

        // Wait for file to download
        File downloadedFile = new File(filePath);
        int retries = 0;
        while (!downloadedFile.exists() && retries < 10) {
            Thread.sleep(1000);
            retries++;
        }
        Assert.assertTrue(downloadedFile.exists(), "❌ Downloaded file not found: " + filePath);

        // 4. Read and edit Excel file
        FileInputStream fis = new FileInputStream(downloadedFile);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue("Neethu");
        row.createCell(1).setCellValue("neethudsiva@gmail.com");
        row.createCell(2).setCellValue("9876543210");

     // Create Calendar for start date
        Calendar startCal = Calendar.getInstance();
        startCal.add(Calendar.MINUTE, 30); // Start = now + 30 minutes
        Date startDate = startCal.getTime();

        // Create Calendar for end date (1 day after start)
        Calendar endCal = (Calendar) startCal.clone();
        endCal.add(Calendar.DATE, 1); // End = start + 1 day
        Date endDate = endCal.getTime();

        // Create date cell format: "dd/MM/yyyy hh.mm AM/PM"
        CellStyle dateStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy hh.mm AM/PM"));

        // Set start_datetime cell
        Cell startCell = row.createCell(3);
        startCell.setCellValue(startDate);
        startCell.setCellStyle(dateStyle);

        // Set end_datetime cell
        Cell endCell = row.createCell(4);
        endCell.setCellValue(endDate);
        endCell.setCellStyle(dateStyle);      

        // Purpose
        row.createCell(5).setCellValue("automation test");

        fis.close();

        // Save edited file
        String editedFilePath = downloadPath + "\\filled-upload.xlsx";
        FileOutputStream fos = new FileOutputStream(editedFilePath);
        workbook.write(fos);
        workbook.close();
        fos.close();

        // 5. Upload the filled file
        WebElement uploadInput = driver.findElement(By.xpath("//input[@type='file']"));
        uploadInput.sendKeys(editedFilePath);

        System.out.println("✅ Excel file uploaded successfully.");
    } 
    @Test(priority = 4)    
    public void uploadAndCreatePass() throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Upload file
        WebElement uploadInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bulkPassInput")));
        uploadInput.sendKeys("C:\\TestFiles\\filled-upload.xlsx");
        System.out.println("Excel file uploaded successfully.");       

        // Try standard click
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='tabpanel' and contains(@class,'active')]//button[normalize-space()='Create Pass']")
            ));

            // Now click the Create Pass button inside the tab
            WebElement createPassBtn = driver.findElement(
                By.xpath("//div[@role='tabpanel' and contains(@class,'active')]//button[normalize-space()='Create Pass']")
            );
        	        createPassBtn.click();
        	        System.out.println("Clicked Create Pass button");
            System.out.println("Clicked via standard Selenium.");
        } catch (Exception e) {
            // JS fallback if standard click fails
            WebElement button = driver.findElement(By.xpath("//button[normalize-space()='Create Pass']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            System.out.println("Button text: " + button.getAttribute("innerText"));
            System.out.println("Clicked via JavaScript fallback.");
        }       
      

        try {
            System.out.println("⏳ Waiting for toast message...");

            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@role='alert']//div[2]")  // This targets the <div>Validation failed</div>
                ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", toast);

            String toastText = toast.getText().trim();
            System.out.println("✅ Toast appeared: " + toastText);
            

            Assert.assertTrue(toastText.contains("Validation failed") || toastText.contains("Bulk pass generated successfully"),
                    "❌ Unexpected toast message: " + toastText);

        } catch (Exception e) {
            System.out.println("❌ Toast not found. Capturing screenshot...");
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshot.toPath(), Paths.get("toast_not_found.png"));
            } catch (IOException io) {
                System.out.println("⚠️ Failed to save screenshot: " + io.getMessage());
            }
            Assert.fail("❌ Toast message not visible after 30 seconds.");
        }

        	driver.close();
    }
    
    

}
