package MobileWare;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class CSVUpdater {
    public static void main(String[] args) throws CsvException {
        String filePath = "C:\\Users\\User\\Downloads\\agentFile (1).csv";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> allRows = reader.readAll();

            // Find the last row index
            int lastRowIndex = allRows.size() - 1;

            // Get the value of the last row (assuming the first column is of interest)
            String lastRowValue = allRows.get(lastRowIndex)[0];

            // Increment the value (assuming it's a number, otherwise adjust as necessary)
            int newValue = Integer.parseInt(lastRowValue) + 1;

            // Update rows 5 to the last row with the new value
            for (int i = 4; i <= lastRowIndex; i++) {
                allRows.get(i)[0] = String.valueOf(newValue); // Updating the first column, change the index if needed
            }

            // Write the updated data back to the CSV file
            try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
                writer.writeAll(allRows);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}