package Torus_1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Formatter;
import java.util.Scanner;

public class DateCalculator {
	public static void main(String[] args) {

		LocalDate localDate = LocalDate.now();

		Scanner scanner = new Scanner(System.in);
		// Input: Date and duration
		String inputDate = localDate.toString();

		int choice = 100;

		// Parse input date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(inputDate, formatter);

		// Calculate the resulting date
		LocalDate resultDate;
		resultDate = date.minus(choice, ChronoUnit.DAYS);

		System.out.println("Calculated date: " + resultDate.format(formatter));
	}
}
