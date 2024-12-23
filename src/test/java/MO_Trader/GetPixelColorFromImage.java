package MO_Trader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;



public class GetPixelColorFromImage {
	static String color = null;
	public static void colorTest(String filePath, int x, int y) {
		try {
			
			File screenshotFile = new File(filePath);
	        // Load the screenshot as an image
	        BufferedImage image = ImageIO.read(screenshotFile);
	        		
	        // Get the color of the pixel at the specified coordinates
	        Color pixelColor = new Color(image.getRGB(x, y));

	        // Extract RGB components
	        int red = pixelColor.getRed();
	        int green = pixelColor.getGreen();
	        int blue = pixelColor.getBlue();
	      
	        if (green > red && green > blue) {
	        	color = "Green";
	        }
	        else {
	        	color = "White";
	        }
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) throws Throwable {
		String filePath = "C:\\Users\\User\\OneDrive\\Desktop\\New folder\\color.png";
		int x = 10;
		int y = 74;
		colorTest(filePath, x, y);
		System.out.println(color);
	}
}