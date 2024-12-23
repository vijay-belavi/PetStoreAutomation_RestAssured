package MobileWare;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CsvToXlsxConverter {

    public static void main(String[] args) throws CsvException {
        String csvFilePath = "C:\\Users\\User\\Downloads\\putaway-import-format.csv";  // Path to your CSV file
        String xlsxFilePath = "C:\\Users\\User\\Downloads\\putaway-import-format.xlsx"; // Path to your new XLSX file

        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath));
             XSSFWorkbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Sheet1");
            List<String[]> rows = csvReader.readAll();

            // Write data to XLSX
            for (int i = 0; i < rows.size(); i++) {
                Row row = sheet.createRow(i);
                String[] cells = rows.get(i);

                for (int j = 0; j < cells.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(cells[j]);
                }
            }

            // Write the XLSX file
            try (FileOutputStream fileOut = new FileOutputStream(xlsxFilePath)) {
                workbook.write(fileOut);
            }

            System.out.println("CSV file has been successfully converted to XLSX.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}