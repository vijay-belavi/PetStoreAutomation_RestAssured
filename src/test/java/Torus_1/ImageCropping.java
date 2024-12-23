package Torus_1;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageCropping {
	public static void main(String[] args) {
		try {
			// Load the original image
			BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\User\\Downloads\\OriginalImage.png"));

			// Define the coordinates and dimensions for cropping (x, y, width, height)
			int x = 100; // starting x-coordinate
			int y = 50; // starting y-coordinate
			int width = 200; // width of the cropped area
			int height = 100; // height of the cropped area

			// Crop the image using the defined coordinates
			BufferedImage croppedImage = originalImage.getSubimage(x, y, width, height);

			// Save the cropped image to a new file
			ImageIO.write(croppedImage, "png", new File("C:\\Users\\User\\Downloads\\CroppedImage.png"));

			System.out.println("Cropped image saved successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
