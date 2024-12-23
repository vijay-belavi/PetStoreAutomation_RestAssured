package MobileWare;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVModifierOnlyNumber {
    public static void main(String[] args) throws Throwable{
        String csvFilePath = "C:\\Users\\User\\Downloads\\agentFile.csv"; // Path to your CSV file
        String headerValue = "MOBILENO"; //MINCASHHANDLINGLIMIT // Header to search for
        int rowIndex = 5; // Row index (0-based) where the cell is located
        int newValue = 0;
        String value = "";
        
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
            
            Pattern digitPattern = Pattern.compile(".*\\d.*");
            Pattern upperCasePattern = Pattern.compile(".*[A-Z].*");

            // Matchers for the patterns
            Matcher digitMatcher = digitPattern.matcher(cellValue);
            Matcher upperCaseMatcher = upperCasePattern.matcher(cellValue);
            
            if (digitMatcher.matches() && upperCaseMatcher.matches()) {
            	value = alphanumeric(value, cellValue);
            	allRows.get(rowIndex)[columnIndex] = String.valueOf(value);
			}
            else if (digitMatcher.matches()) {
            	value = number(value, cellValue);
            	allRows.get(rowIndex)[columnIndex] = String.valueOf(value);
            }
            // Write the updated data back to the CSV file
            CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath));
            writer.writeAll(allRows);
            writer.close();

            System.out.println("CSV file updated successfully.");
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String alphanumeric(String value, String cellValue) {
    	System.out.println("Entered Alphanumeric Method");
    	int newValue = Integer.parseInt(cellValue.replaceAll("[^0-9]", "")) + 1;
    	if (cellValue.replaceAll("[^0-9]", "").length() != String.valueOf(newValue).length()) {
			value = "0"+newValue;
		}
    	else {
    		value = String.valueOf(newValue);
    	}
    	return value;
    }
    public static String number(String value, String cellValue) {
    	System.out.println("Entered Numeric Method");
    	long newValue = Long.parseLong(cellValue) + 1;
    	value = String.valueOf(newValue);
		return value;
    }
}