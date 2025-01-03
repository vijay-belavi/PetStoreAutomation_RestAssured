package Torus_1;

public class PercentageValues {

    public static void main(String[] args) {
        // Define the start and end values
        double startValue = 500.0;
        double endValue = 100000.0;
        int maxPercentage = 100;

        // Calculate the increment per 1%
        double increment = (endValue - startValue) / maxPercentage;

        // Loop through percentages from 0% to 100%
        for (int percentage = 0; percentage <= maxPercentage; percentage++) {
            // Calculate the value for the current percentage
            double value = startValue + (increment * percentage);
            // Print the percentage and its corresponding value
            System.out.printf("%d%%: %.2f%n", percentage, value);
        }
    }
}
