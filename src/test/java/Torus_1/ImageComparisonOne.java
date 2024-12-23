package Torus_1;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ImageComparisonOne {
    public static void main(String[] args) {
        try {
            // Create Screen object for image comparison
            Screen screen = new Screen();
            
            // Define the main and sub images
            Pattern mainImage = new Pattern("C:\\Users\\User\\Downloads\\MainImage.png");
            Pattern subImage = new Pattern("C:\\Users\\User\\Downloads\\SubImage.png");

            // Set the confidence level (e.g., 0.8 for 80% similarity)
            mainImage.similar(0.5);  // Confidence threshold: 80%
            
            // Perform the comparison
            Match match = screen.find(mainImage);
            
            // Check if the sub-image is found within the main image with the specified confidence
            if (match != null) {
                System.out.println("Sub-image is found with confidence score: " + match.getScore());
            } else {
                System.out.println("Sub-image not found.");
            }
        } catch (FindFailed e) {
            System.out.println("Image comparison failed: " + e.getMessage());
        }
    }
}


