package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Log;

public class BaseClass {

	public static WebDriver driver;				
	public Properties p;


	public Properties jj;


	public Properties ss;
	public Properties jo;
	public Properties nds;


	@Parameters({"os","browser"})
	@BeforeClass(groups={"Regression"})

	public void setup(String os ,String br) throws IOException {
		Log.info("Starting WebDriver");
		switch(br.toLowerCase())
		{
		case "chrome" :
			WebDriverManager.chromedriver().setup();      						//WebDriver Initializaton
			ChromeOptions co = new ChromeOptions();	
			co.addArguments("--incognito");     // Incognito mode
		   
//			 co.addArguments("--headless");               // Run in headless mode
//			 co.addArguments("--window-size=1920,1080");  // (optional) Set screen size
//			 co.addArguments("--disable-gpu");            // (sometimes needed for Windows)
//			 co.addArguments("--no-sandbox");  			
			
			     
			driver = new ChromeDriver(co);break;
		case "edge" :
			System.setProperty("webdriver.edge.driver", "C:\\Users\\PearlSoft LT-125.DESKTOP-221H5FN\\eclipse-workspace\\Epass_Automation\\Driver\\msedgedriver.exe");
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--incognito");     // Incognito mode
			driver = new EdgeDriver(edgeOptions);
			break;
		case "firefox" :
			driver= new FirefoxDriver();break;
		default :System.out.println(" Invalid browser name..");return;
		}
		driver.manage().window().maximize();

		Log.info("Navigating to the URL");

		FileReader file= new FileReader("./src//test//resources//config.properties");		
		p=new Properties();
		p.load(file);	

		driver.get(p.getProperty("baseurl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	public String captureScreen(String tname) {
		String timeStamp =new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot= (TakesScreenshot)driver;
		File sourceFile= takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+tname + "_"+ timeStamp+ ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	public String captureScreenBase64(String tname) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            TakesScreenshot ts = (TakesScreenshot) driver;
            return ts.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



	@AfterClass
	public void tearDown() {
		Log.info("Closing Browser");
		driver.quit();
	}


}