package MobileWare;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvModifier {
	public static void main(String[] args) throws Throwable {
		String csvFilePath = "C:\\Users\\User\\Downloads\\agentFile.csv"; // Path to your CSV file
		String headerValue = "MINCASHHANDLINGLIMIT"; // Header to search for
		int rowIndex = 5; // Row index (0-based) where the cell is located
		int newValue = 0;

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
			// Increment the value in the specified cell
			String cellValue = allRows.get(rowIndex)[columnIndex];
			System.out.println(cellValue);
			newValue = Integer.parseInt(cellValue) + 1;
			allRows.get(rowIndex)[columnIndex] = String.valueOf(newValue);
			// Write the updated data back to the CSV file
			
			CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath));
			writer.writeAll(allRows);
			writer.close();

			System.out.println("CSV file updated successfully.");
			System.out.println(newValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}