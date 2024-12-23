package Torus_1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateValidations {
	public static void main(String[] args) {
		System.out.println("Hi");

		String firstDate = "";
		DateTimeFormatter firstformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String secondDate = "";
		DateTimeFormatter secondformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		List<String> dateList = new ArrayList<>();
        dateList.add("2023-12-10");
        dateList.add("2022-01-15");
        dateList.add("2024-05-20");

        // Parse the timeframe dates
        String startDateStr = "2023-12-01";
        String endDateStr = "2024-06-01";

        LocalDate startDate = LocalDate.parse(startDateStr, firstformatter);
        LocalDate endDate = LocalDate.parse(endDateStr, secondformatter);

        // Verify each date
        boolean allDatesWithinRange = true;
        for (String dateStr : dateList) {
            LocalDate date = LocalDate.parse(dateStr, firstformatter);

            if (date.isBefore(startDate) || date.isAfter(endDate)) {
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
}
