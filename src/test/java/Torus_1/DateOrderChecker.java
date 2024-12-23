package Torus_1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DateOrderChecker {

    public static void main(String[] args) {
    	
    	try {
            // Sample ArrayList of date strings (you can modify this to test different cases)
            ArrayList<String> dateStrings = new ArrayList<>();
            dateStrings.add("16 Oct, 2024");

            // Define the date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");

            // Parse strings to LocalDate objects and store in ArrayList
            ArrayList<LocalDate> dates = new ArrayList<>();
            for (String dateStr : dateStrings) {
                dates.add(LocalDate.parse(dateStr, formatter));
            }

            // Handle case for single element in the list
            if (dates.size() == 1) {
                System.out.println("Only one date in the list, considered both ascending and descending order.");
                return;
            }

            // Check if dates are in ascending order
            boolean isAscending = true;
            for (int i = 0; i < dates.size() - 1; i++) {
                if (dates.get(i).isAfter(dates.get(i + 1))) {
                    isAscending = false;
                    break;
                }
            }

            // Check if dates are in descending order
            boolean isDescending = true;
            for (int i = 0; i < dates.size() - 1; i++) {
                if (dates.get(i).isBefore(dates.get(i + 1))) {
                    isDescending = false;
                    break;
                }
            }

            // Print the result
            if (isAscending) {
                System.out.println("Dates are in ascending order.");
            } else if (isDescending) {
                System.out.println("Dates are in descending order.");
            } else {
                System.out.println("Dates are not in any specific order.");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}

    }
}