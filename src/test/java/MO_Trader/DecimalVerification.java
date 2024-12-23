package MO_Trader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalVerification {
    public static void main(String[] args) {
        // Sample texts
        String text1 = "123.4567";
        int num = 3;// Should match, contains 2 decimal places
           // Should not match, contains 4 decimal places

        // Define the regex pattern for 2 or 4 decimal places
        String regexPattern = "\\d+(\\.\\d{"+num+"})";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regexPattern);

        // Verify text1
        Matcher matcher1 = pattern.matcher(text1);
        if (matcher1.matches()) {
            System.out.println(text1 + " contains "+num+" decimal places.");
        } else {
            System.out.println(text1 + " does not contain "+num+" decimal places.");
        }

       
    }
}