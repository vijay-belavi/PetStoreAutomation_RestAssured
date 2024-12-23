package Torus_1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyNameExtractor {
	public static void main(String[] args) {
		// Test strings
		String input1 = "TECHM-EQ ₹1,621.05 NSE 43.85 (2.78%)";
		// String input2 = "";
		// Define the regex pattern to
		// extract the company name
		String regex = "^([A-Z]+(-EQ)?(\\s+[A-Z]+)?)"; //
		// Matches "TECHM-EQ" or "TECHM BSE"
		Pattern pattern = Pattern.compile(regex);
		extractCompanyName(input1, pattern);
		// extractCompanyName(input2, pattern);
		int i = 0;
		if (i<=5) {
			for (int j = 0; j < args.length; j++) {
				String string = args[j];
				if (i==0) {
					break;
				}
			}
			
			
		}
	}

	private static void extractCompanyName(String input, Pattern pattern) {

		Matcher matcher = pattern.matcher(input); // Find and print the company name
		if (matcher.find()) {
			String companyName = matcher.group(1);
			System.out.println("Company Name: " + companyName);
		} else {
			System.out.println("No company name found.");
		}
	}
}