package utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	  public static List<String> getRunnableTests(String excelPath, String sheetName) {
	        List<String> runnableTests = new ArrayList<>();
	        try (FileInputStream fis = new FileInputStream(excelPath);
	             Workbook workbook = new XSSFWorkbook(fis)) {
	            Sheet sheet = workbook.getSheet(sheetName);
	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	                Row row = sheet.getRow(i);
	                if (row != null) {
	                    String testClass = row.getCell(0).getStringCellValue();
	                    String runMode = row.getCell(1).getStringCellValue();
	                    if ("Yes".equalsIgnoreCase(runMode)) {
	                        runnableTests.add(testClass);
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return runnableTests;
	    }

}

