package Torus_1;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCheck {
    public static void main(String[] args) {
        // Define the input date as a string (e.g., "2024-11-30")
        String inputDate = "2024-11-30";

        // Define the date format of the input date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the input date into a LocalDate object
        LocalDate givenDate = LocalDate.parse(inputDate, formatter);

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Check if the given date matches the current date
        if (givenDate.equals(currentDate)) {
            System.out.println("The given date matches the current date.");
        } else {
            System.out.println("The given date does not match the current date.");
        }
    }
}