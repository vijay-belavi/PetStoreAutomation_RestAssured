package Torus_1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTPExtractor {

    // Method to extract OTP based on the specified digit count
    public static String extractOTP(String message, int otpLength) {
        // Create a regex pattern for the OTP based on the required length (4, 5, or 6 digits)
        String regex = "\\b\\d{" + otpLength + "}\\b";
        
        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);
        
        // Match the pattern in the message
        Matcher matcher = pattern.matcher(message);
        
        // Check if we found the OTP
        if (matcher.find()) {
            return matcher.group();
        }
        
        //\ Return a message if no OTP is found
        return "OTP not found";
    }

    public static void main(String[] args) {
        // Sample message with OTP
        String message = "795929 is your OTP for validating your mobile number. Valid for 3 minutes only.";
        
        // Extract OTP based on user input (4, 5, or 6 digits)
        int otpLength = 6;  // Can be 4, 5, or 6 based on the user's input
        String otp = extractOTP(message, otpLength);
        
        // Output the OTP
        System.out.println("Extracted OTP: " + otp);
    }
}