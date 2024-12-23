package Excel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatConversion {
    public static void main(String[] args) {
        // Given date in the original format
        String originalDateString = "23-12-2022";

        // Defining the original date format
        SimpleDateFormat originalFormat = new SimpleDateFormat("DD-MM-YYYY");
        
        try {
            // Parsing the original date string to obtain a Date object
            Date originalDate = originalFormat.parse(originalDateString);
            
            // Creating a new date format to which you want to convert the original date
            SimpleDateFormat newFormat = new SimpleDateFormat("DD-MM-YYYY");
            
            // Formatting the original Date object to the new format
            String newDateString = newFormat.format(originalDate);
            
            // Outputting the date in the new format
            System.out.println("Date in the new format: " + newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}