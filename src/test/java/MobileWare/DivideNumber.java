package MobileWare;

import java.util.ArrayList;
import java.util.Random;

public class DivideNumber {
    public static void main(String[] args) {
        int number = 10;
        int[] parts = divideNumberIntoParts(number, 9);
        ArrayList no = new ArrayList();
        // Print the array
        for (int part : parts) {
            System.out.println(part + " ");
            no.add(part);
        }
        System.out.println(no);
    }

    public static int[] divideNumberIntoParts(int number, int partsCount) {
        int[] parts = new int[partsCount];
        Random rand = new Random();
        int sum = 0;

        // Generate random positive parts ensuring the sum is less than the number
        for (int i = 0; i < partsCount - 1; i++) {
            parts[i] = rand.nextInt(number - sum - (partsCount - i - 1)) + 1; // At least 1
            sum += parts[i];
        }

        // Assign the remaining value to the last part
        parts[partsCount - 1] = number - sum;

        // Adjust if the sum is equal to the original number
        if (sumOfArray(parts) == number) {
            parts[0]++;
            parts[1]--;
        }

        // Ensure all parts are positive
        for (int i = 0; i < partsCount; i++) {
            if (parts[i] <= 0) {
                parts[i] = 1;
                parts[(i + 1) % partsCount]--;
            }
        }

        return parts;
    }

    public static int sumOfArray(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}