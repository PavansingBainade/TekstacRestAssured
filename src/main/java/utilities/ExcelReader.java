package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
        // Open the Excel file
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // Access the required sheet
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Get total number of rows (zero-based index)
        int rowCount = sheet.getLastRowNum(); // Last row index (zero-based)
        // Assuming first row is header, so data rows = rowCount
        // Get number of columns from header row
        XSSFRow headerRow = sheet.getRow(0);
        int colCount = headerRow.getLastCellNum(); // returns number of cells in the header row

        // Initialize data array excluding header row
        Object[][] data = new Object[rowCount][colCount];

        // Iterate through rows (starting from 1 to skip header)
        for (int i = 1; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) {
                // If row is missing, fill with empty strings
                for (int j = 0; j < colCount; j++) {
                    data[i - 1][j] = "";
                }
                continue;
            }
            for (int j = 0; j < colCount; j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    data[i - 1][j] = "";
                } else {
                    // Set cell type to String and get value
                    cell.setCellType(org.apache.poi.ss.usermodel.CellType.STRING);
                    data[i - 1][j] = cell.getStringCellValue();
                }
            }
        }

        // Close resources
        workbook.close();
        fis.close();

        return data;
    }
}
