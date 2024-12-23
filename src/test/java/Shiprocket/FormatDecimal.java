package Shiprocket;

import java.text.DecimalFormat;

public class FormatDecimal {
    public static void main(String[] args) {
        String a = "20.2810251988605";
        
        // Convert the string to a double
        double value = Double.parseDouble(a);
        
        // Use DecimalFormat to format the value to 2 decimal places
        DecimalFormat df = new DecimalFormat("0.000");
        String formattedValue = df.format(value);
        
        System.out.println(formattedValue); // Output: 0.11
    }
}