package API;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class csm_otp {
public static void main(String[] args) {
	        String inputString = "OTP for Aadhaar (XX0164) is 846562 (valid for 10 mins). To update Aadhaar, Upload documents on myaadhaar.uidai.gov.in or visit Aadhaar Center. Call 1947 for info. -UIDAI";

	        // Regular expression to match numbers
	        String regex = "\\b\\d{6}\\b";

	        // Create a Pattern object
	        Pattern pattern = Pattern.compile(regex);

	        // Create a Matcher object
	        Matcher matcher = pattern.matcher(inputString);

	        // Find and print all numbers in the input string
	        while (matcher.find()) {
	            System.out.println("Found number: " + matcher.group());
	        }
	    }
	}