package Torus_1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DateRangeVerification {
    public static void main(String[] args) {
        // Define the possible date formats
        List<DateTimeFormatter> formatters = new ArrayList<>();
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));  // Format for "2023-12-10"
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));  // Another format, e.g., "10-12-2023"

        // Input the list of dates (could have different formats)
        List<String> dateList = new ArrayList<>();
        dateList.add("2023-12-10");
        dateList.add("15-01-2024");  // Different format
        dateList.add("2024-05-20");

        // Parse the timeframe dates with multiple possible formats
        String startDateStr = "2023-12-01";
        String endDateStr = "01-06-2024";  // Different format

        LocalDate startDate = parseDate(startDateStr, formatters);
        LocalDate endDate = parseDate(endDateStr, formatters);

        // Verify each date
        boolean allDatesWithinRange = true;
        for (String dateStr : dateList) {
            LocalDate date = parseDate(dateStr, formatters);

            if (date == null) {
                allDatesWithinRange = false;
                System.out.println("Invalid date format for: " + dateStr);
            } else if (date.isBefore(startDate) || date.isAfter(endDate)) {
                allDatesWithinRange = false;
                System.out.println("Date " + date + " is out of the range.");
            } else {
                System.out.println("Date " + date + " is within the range.");
            }
        }

        // Final result
        if (allDatesWithinRange) {
            System.out.println("All dates are within the timeframe.");
        } else {
            System.out.println("Some dates are outside the timeframe.");
        }
    }

    // Utility method to parse a date with multiple format options
    private static LocalDate parseDate(String dateStr, List<DateTimeFormatter> formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                // Continue trying the next format
            }
        }
        // Return null if no formats match
        return null;
    }
}
