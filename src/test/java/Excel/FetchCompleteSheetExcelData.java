package Excel;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FetchCompleteSheetExcelData {

    public static void main(String[] args) throws IOException {
        // Specify the path to your Excel file
        String excelFilePath = "C:\\Users\\User\\Downloads\\MotilalOswal_SuperApp_Mobile.xlsx";
        // Specify the sheet name you want to read data from
        String sheetName = "Login";

        // Create a FileInputStream to read the Excel file
        FileInputStream fis = new FileInputStream(new File(excelFilePath));

        // Create a workbook instance from the Excel file
        Workbook workbook = WorkbookFactory.create(fis);

        // Get the sheet by name
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            System.out.println("Sheet '" + sheetName + "' not found.");
            workbook.close();
            fis.close();
            return;
        }

        // Iterate over all rows in the sheet
        for (Row row : sheet) {
            // Iterate over all cells in the row
        	row.getCell(0);
            for (Cell cell : row) {
                // Print the cell value
                System.out.print(cell.toString() + "\t");
            }
            System.out.println(); // Move to the next line after each row
        }
        
        // Close the workbook and the FileInputStream
        workbook.close();
        fis.close();
        
        
    }
}