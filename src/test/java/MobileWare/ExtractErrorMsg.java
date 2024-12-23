package MobileWare;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractErrorMsg {
    public static void main(String[] args) {
        String inputString = "{\"media_url\":\"media://ed2e3b349e50bb6d681ecb31cb16608a063859c2\"}";
        String msg = "errorCode";
        // Define the regex pattern to extract the errorMsg value
        String regex = "\"media_url\":\"media://([a-f0-9]{40,})";
        //mediaUrl : {"media_url":"media://ed2e3b349e50bb6d681ecb31cb16608a063859c2"}

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);
        
        // Create a matcher to find the pattern in the input string
        Matcher matcher = pattern.matcher(inputString);
        
        // Check if the pattern is found
        if (matcher.find()) {
            // Extract the errorMsg value
            String errorMsg = matcher.group(1);
            System.out.println("Extracted errorMsg: " + errorMsg);
        } else {
            System.out.println("errorMsg not found in the input string.");
        }
    }
}
