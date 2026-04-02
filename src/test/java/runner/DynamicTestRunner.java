package runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import utilities.ExcelReader;
import utilities.ExtendReportManager;

public class DynamicTestRunner {
	 public static void main(String[] args) {
	        String excelPath = System.getProperty("user.dir") + "\\testData\\TestSuite.xlsx";
	        List<String> testClasses = ExcelReader.getRunnableTests(excelPath, "RunnerMode");

	        if (testClasses.isEmpty()) {
	            System.out.println("No test classes marked to run.");
	            return;
	        }

	        XmlSuite suite = new XmlSuite();
	        suite.setName("ExcelDrivenSuite");

	        XmlTest test = new XmlTest(suite);
	        test.setName("ExcelDrivenTests");
	        
	        Map<String, String> testParams = new HashMap<>();
	        testParams.put("os", "Windows");
	        testParams.put("browser", "chrome");
	        test.setParameters(testParams);

	        List<XmlClass> classes = new ArrayList<>();
	        for (String className : testClasses) {
	            classes.add(new XmlClass(className));
	        }

	        test.setXmlClasses(classes);

	        TestNG testng = new TestNG();
	        List<XmlSuite> suites = new ArrayList<>();
	        suites.add(suite);
	        testng.setXmlSuites(suites);
	        testng.addListener(new ExtendReportManager());
	        testng.run();
	    }

}

