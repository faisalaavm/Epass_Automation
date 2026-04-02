package utilities;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.IOException;

public class ExcelUtils {
	 public static Object[][] getCompanyData(String sheetName) throws IOException {
	        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\testData\\TestSuite.xlsx");
	        Workbook workbook = new XSSFWorkbook(fis);
	        Sheet sheet = workbook.getSheet(sheetName);
	        int rowCount = sheet.getPhysicalNumberOfRows();
	        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

	        Object[][] data = new Object[rowCount - 1][colCount];

	        for (int i = 1; i < rowCount; i++) {
	            Row row = sheet.getRow(i);
	            for (int j = 0; j < colCount; j++) {
	                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	                data[i - 1][j] = new DataFormatter().formatCellValue(cell);
	            }
	        }
	        workbook.close();
	        fis.close();
	        return data;
	    }
}
