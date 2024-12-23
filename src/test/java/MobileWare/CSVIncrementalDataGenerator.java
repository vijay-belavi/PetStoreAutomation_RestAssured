package MobileWare;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVIncrementalDataGenerator {
	public static void main(String[] args) {
		String cellValue = "AG0800";
		String incremental = "1";
		String value = "";
		ArrayList<String> arrayList = new ArrayList();
		for (int i = 0; i < 10; i++) {
			try {
				int incrementalValue = Integer.parseInt(incremental);
				Pattern digitPattern = Pattern.compile(".*\\d.*");
				Pattern upperCasePattern = Pattern.compile(".*[A-Z].*");

				// Matchers for the patterns
				Matcher digitMatcher = digitPattern.matcher(cellValue);
				Matcher upperCaseMatcher = upperCasePattern.matcher(cellValue);

				if (digitMatcher.matches() && upperCaseMatcher.matches()) {
					cellValue = alphanumeric(value, cellValue, incrementalValue);
				} else if (digitMatcher.matches()) {
					cellValue = number(value, cellValue, incrementalValue);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			arrayList.add(cellValue);
		}
		if (cellValue.contains("[A-Z]")) {
			for (String string : arrayList) {
				if (cellValue.replaceAll("[^0-9]", "").length() == string.length()) {
					string = string;
				}
				else {
					string = "0"+string;
				}
				System.out.println(string);
			}
		}
		
    }
	public static String alphanumeric(String value, String cellValue, int incrementalValue) {
		int newValue = Integer.parseInt(cellValue.replaceAll("[^0-9]", "")) + incrementalValue;
		if (cellValue.replaceAll("[^0-9]", "").length() != String.valueOf(newValue).length()) {
			value = "0" + newValue;
		} else {
			value = String.valueOf(newValue);
		}
		return value;
	}

	public static String number(String value, String cellValue, int incrementalValue) {
		long newValue = Long.parseLong(cellValue) + incrementalValue;
		value = String.valueOf(newValue);
		return value;
	}
}