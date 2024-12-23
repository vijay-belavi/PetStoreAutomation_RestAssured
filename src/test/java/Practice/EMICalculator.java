package Practice;

import java.util.Scanner;

public class EMICalculator {

    public static double calculateEMI(double principal, double annualInterestRate, int tenureMonths) {
        double monthlyInterestRate = annualInterestRate / (12 * 100); 

        double emi = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths)) / 
                     (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);

        return emi;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the principal loan amount: ");
        double principal = scanner.nextDouble();

        System.out.print("Enter the annual interest rate (in %): ");
        double annualInterestRate = scanner.nextDouble();

        System.out.print("Enter the loan tenure (in years): ");
        int tenureYears = scanner.nextInt();

        System.out.print("Enter the additional loan tenure (in months): ");
        int tenureMonths = scanner.nextInt();

        scanner.close();

        int totalTenureMonths = (tenureYears * 12) + tenureMonths;

        double emi = calculateEMI(principal, annualInterestRate, totalTenureMonths);

        double totalAmount = emi * totalTenureMonths;

        emi = Math.round(emi); 
        totalAmount = Math.round(totalAmount); 

        System.out.printf("The EMI is: %.2f\n", emi);
        System.out.printf("The total amount to be paid over the loan period is: %.2f\n", totalAmount);
    }
}