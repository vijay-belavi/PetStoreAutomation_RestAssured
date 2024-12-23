package Practice;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DecodeDate {
    public static void main(String[] args) {
    	// Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the format for displaying date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time as per the defined format
        String formattedDateTime = currentDateTime.format(formatter);

        // Prompt the user to enter the first date and time
        String firstDateTimeStr = formattedDateTime;

        // Prompt the user to enter the second date and time
        String secondDateTimeStr = "2024-04-22 16:17:47";

        // Define the format for parsing date and time strings
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // Parse the input strings into LocalDateTime objects
            LocalDateTime firstDateTime = LocalDateTime.parse(firstDateTimeStr, formatter1);
            LocalDateTime secondDateTime = LocalDateTime.parse(secondDateTimeStr, formatter1);

            // Compare the two LocalDateTime objects to find the lesser and greater dates
            LocalDateTime lesserDateTime;
            LocalDateTime greaterDateTime;
            
            if (firstDateTime.compareTo(secondDateTime) < 0) {
                lesserDateTime = firstDateTime;
                greaterDateTime = secondDateTime;
                System.out.println(greaterDateTime.format(formatter1)+" is greater than "+lesserDateTime.format(formatter1));
            } else {
                lesserDateTime = secondDateTime;
                greaterDateTime = firstDateTime;
                System.out.println(lesserDateTime.format(formatter1)+" is lesser than "+greaterDateTime.format(formatter1));
                
            }

        } catch (Exception e) {
            System.out.println("Invalid date and time format. Please enter date and time in the format yyyy-MM-dd HH:mm:ss.");
        }
    }
}