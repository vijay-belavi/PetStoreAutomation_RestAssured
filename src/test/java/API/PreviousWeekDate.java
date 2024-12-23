package API;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PreviousWeekDate {
    public static void main(String[] args) {
        // Get the current date
        Date currentDate = new Date();

        // Create a Calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        
        // Subtract 7 days to get the date of the previous week
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        // Get the date of the previous week
        Date previousWeekDate = calendar.getTime();

        // Format the date as per your requirement
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
        String formattedDate = dateFormat.format(previousWeekDate);

        // Print the formatted date
        System.out.println("One week ago, the date was: " + formattedDate);
    }
}