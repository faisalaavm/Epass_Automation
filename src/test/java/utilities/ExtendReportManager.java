package utilities;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtendReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;  // <-- class-level variable

    private ByteArrayOutputStream consoleStream;
    private PrintStream originalConsole;

    @Override
    public void onStart(ITestContext testcontext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "ExtentReport-" + timeStamp + ".html";    // <-- assign to class variable

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        sparkReporter.config().setDocumentTitle("My Reports");
        sparkReporter.config().setReportName("Regression testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Entry Pass");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testcontext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating system", os);

        String browser = testcontext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        consoleStream = new ByteArrayOutputStream();
        originalConsole = System.out;
        System.setOut(new PrintStream(consoleStream));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getDescription();
        if (testName == null || testName.trim().isEmpty()) {
            testName = result.getMethod().getMethodName();
        }

        test = extent.createTest(testName);
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");
        test.log(Status.INFO, "Test passed successfully.");

        try {
            String base64Screenshot = new BaseClass().captureScreenBase64(result.getName());
            if (base64Screenshot != null && !base64Screenshot.isEmpty()) {
                test.addScreenCaptureFromBase64String(base64Screenshot, "Screenshot");
            } else {
                test.log(Status.WARNING, " Screenshot could not be captured.");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, " Error capturing screenshot: " + e.getMessage());
            e.printStackTrace();
        }

        String consoleOutput = consoleStream.toString();
        test.log(Status.INFO, "<pre>" + consoleOutput + "</pre>");
        System.setOut(originalConsole);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getDescription();
        if (testName == null || testName.trim().isEmpty()) {
            testName = result.getMethod().getMethodName();
        }

        test = extent.createTest(testName);
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String base64Screenshot = new BaseClass().captureScreenBase64(result.getName());
            if (base64Screenshot != null && !base64Screenshot.isEmpty()) {
                test.addScreenCaptureFromBase64String(base64Screenshot, "Screenshot");
            } else {
                test.log(Status.WARNING, " Screenshot could not be captured.");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, " Error capturing screenshot: " + e.getMessage());
            e.printStackTrace();
        }

        String consoleOutput = consoleStream.toString();
        test.log(Status.INFO, "<pre>" + consoleOutput + "</pre>");
        System.setOut(originalConsole);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getMethod().getDescription());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContex) {
        extent.flush();

        String pathofExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathofExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    


	try 
	{ 
	URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	ImageHtmlEmail email=new ImageHtmlEmail();
	email.setDataSourceResolver(new DataSourceUrlResolver(url));
	email.setHostName("smtp.googlemail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("faisalapearl@gmail.com","jmhs ojru gpld ztqi"));
	email.setSSLOnConnect(true);
	email.setFrom("faisalapearl@gmail.com");
	email.setSubject("Test Results of Entry Pass Automation");
	email.setMsg("Hi, Please Find Attached Report");
	email.addTo("avmfaisala@gmail.com");
	email.attach(url,"extent report","please check the report");
	
	email.send();
	}
	catch (Exception e) {
		e.printStackTrace();
		}
}}







