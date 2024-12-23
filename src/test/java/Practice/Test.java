package Practice;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String text = "OTP for Aadhaar (XX7895) is 348729 (valid for 10 mins). To update Aadhaar, Upload documents on myaadhaar.uidai.gov.in or visit Aadhaar Center. Call 1947 for info. -UIDAI";
        String otpDigit = "2";
        // Define the regex pattern to match the OTP
        Pattern pattern = Pattern.compile("\\b\\d{"+otpDigit+"}\\b");

        // Match the pattern against the input text
        Matcher matcher = pattern.matcher(text);

        // Check if the OTP is found and print it
        if (matcher.find()) {
            String otp = matcher.group();
            System.out.println("OTP: " + otp);
        } else {
            System.out.println("No OTP found.");
        }
    }
}