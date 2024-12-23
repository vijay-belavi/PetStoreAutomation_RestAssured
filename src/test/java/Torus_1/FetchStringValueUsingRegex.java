package Torus_1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchStringValueUsingRegex {
	public static void main(String[] args) throws InterruptedException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter String Value");
		String input = sc.nextLine(); //ICICIBANK NSE ₹1,249.05 -7.30 (-0.58%)
		
		// Define the regex pattern to capture the price value (₹ followed by digits,
		// commas, and a decimal)
		System.out.println("Enter Regex Pattern");
		String regex = "₹([\\d,]+\\.\\d{2})"; //\\(([^)]+)\\) // ₹([\\d,]+\\.\\d{2})
		// Compile the pattern
		Pattern pattern = Pattern.compile(regex);

		// Create a matcher for the input string
		Matcher matcher = pattern.matcher(input);
		String price = "";
		
		// Check if the pattern is found and print the matched value
		if (matcher.find()) {
			// Group 1 will contain the numeric value after the ₹ symbol
			price = matcher.group(1);
			System.out.println("The stock price is: ₹" + price);
		} else {
			price = null;
			System.out.println("Stock price not found!");
		}
	}
}
