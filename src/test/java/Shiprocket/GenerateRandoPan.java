package Shiprocket;

import java.util.Random;

public class GenerateRandoPan {
	private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final Random RANDOM = new Random();

	public static String generateRandomCapitalLetters(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length must be greater than 0");
		}

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = RANDOM.nextInt(CAPITAL_LETTERS.length());
			sb.append(CAPITAL_LETTERS.charAt(index));
		}

		return sb.toString();
	}

	public static String generateRandomNumber(int digits) {
		if (digits <= 0) {
			throw new IllegalArgumentException("Number of digits must be greater than 0");
		}

		// Calculate the minimum and maximum values for the given number of digits
		int min = (int) Math.pow(10, digits - 1);
		int max = (int) Math.pow(10, digits) - 1;

		// Generate a random number in the specified range
		int randomNumber = min + RANDOM.nextInt(max - min + 1);
		return String.valueOf(randomNumber);
	}

	public static void main(String[] args) {
		int length = 3; // Example length
		String randomCapitalLetters = generateRandomCapitalLetters(length);

		int digits = 4;
		String randomNumber = "";
		if (digits <= 0) {
			System.out.println("Number of digits must be greater than 0.");
		} else {
			randomNumber = generateRandomNumber(digits);
		}

		randomCapitalLetters = randomCapitalLetters + "P";
		
		length = 1; // Example length
		String randomCapitalLetters1 = generateRandomCapitalLetters(length);
		
		randomCapitalLetters1 = randomCapitalLetters + randomCapitalLetters1 + randomNumber;
		
		String randomCapitalLetters2 = generateRandomCapitalLetters(length);
		randomCapitalLetters2 = randomCapitalLetters1 + randomCapitalLetters2;
		System.out.println(randomCapitalLetters2);
	}
}
