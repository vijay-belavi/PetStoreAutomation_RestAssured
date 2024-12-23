package API;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelCellValueFetcher {

    public static void main(String[] args) {
        try {
            // Specify the Excel file path
            String filePath = "C:\\Users\\User\\Desktop\\Excel Sheets\\Singer India.xlsx";

            // Create a FileInputStream to read the Excel file
            FileInputStream fis = new FileInputStream(filePath);

            // Create a workbook instance for the Excel file
            Workbook workbook = new XSSFWorkbook(fis);

            // Get the sheet by name (replace "Sheet1" with your sheet name)
            Sheet sheet = workbook.getSheet("Sheet2");

            // Specify the header and row names
            String columnName = "Name";
            String rowName = "TC002";

            // Find the column index based on the header name
            int columnIndex = -1;
            Row headerRow = sheet.getRow(0); // Assuming header row is the first row
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equals(columnName)) {
                    columnIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (columnIndex != -1) {
                // Find the row index based on the row name
                int rowIndex = -1;
                for (Row row : sheet) {
                    Cell cell = row.getCell(0); // Assuming the first column contains row names
                    if (cell != null && cell.getStringCellValue().equals(rowName)) {
                        rowIndex = row.getRowNum();
                        break;
                    }
                }

                if (rowIndex != -1) {
                    // Get the cell value
                    Row row = sheet.getRow(rowIndex);
                    Cell cell = row.getCell(columnIndex);
                    String cellValue = cell.getStringCellValue();

                    System.out.println("Value in cell (" + rowName + ", " + columnName + "): " + cellValue);
                } else {
                    System.out.println("Row not found: " + rowName);
                }
            } else {
                System.out.println("Column not found: " + columnName);
            }

            // Close the FileInputStream and workbook
            fis.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}