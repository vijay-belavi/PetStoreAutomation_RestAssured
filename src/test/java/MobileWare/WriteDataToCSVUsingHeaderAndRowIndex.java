package MobileWare;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class WriteDataToCSVUsingHeaderAndRowIndex {
    public static void main(String[] args) throws Throwable{
        String csvFilePath = "C:\\Users\\User\\Downloads\\agentFile.csv"; // Path to your CSV file
        String headerValue = "AGENTID"; // The header of the column
        int rowIndex = 5; // Row index (0-based) where the cell is located
        String newValue = "Test123"; // The new value to write

        try {
            // Read the CSV file
            CSVReader reader = new CSVReader(new FileReader(csvFilePath));
            List<String[]> allRows = reader.readAll();
            reader.close();

            if (allRows.isEmpty()) {
                System.out.println("CSV file is empty.");
                return;
            }

            // Get headers
            String[] headers = allRows.get(0);
            int columnIndex = -1;

            // Find the column index for the given header value
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equalsIgnoreCase(headerValue)) {
                    columnIndex = i;
                    break;
                }
            }

            if (columnIndex == -1) {
                System.out.println("Header not found.");
                return;
            }

            if (rowIndex >= allRows.size()) {
                System.out.println("Row index out of bounds.");
                return;
            }

            // Set the new value in the specified cell
            allRows.get(rowIndex)[columnIndex] = newValue;

            // Write the updated data back to the CSV file
            CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath));
            writer.writeAll(allRows);
            writer.close();

            System.out.println("CSV file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}