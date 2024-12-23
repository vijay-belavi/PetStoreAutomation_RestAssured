package Torus_1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageDownloaderWithBufferedImage {

	public static void main(String[] args) {

		String imageUrl = "http://torusapis.cmots.com/complogodata/mf/6011.png";
		String FilePath = "C:\\Users\\User\\Downloads\\www.fireflink.com_ (3).png";

		/*
		 * String imageUrl = "http://torusapis.cmots.com/complogodata/mf/21271.png";
		 * String FilePath = "C:\\Users\\User\\Downloads\\amcLogo.png";
		 */
		ToGetImage(imageUrl, FilePath);
	}

	public static void ToGetImage(String imageUrl, String FilePath) {

		try {
			URL url = new URL(imageUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setConnectTimeout(5000);
			BufferedImage image = ImageIO.read(connection.getInputStream());
			if (image != null) {
				File outputFile = new File(FilePath);
				ImageIO.write(image, "PNG", outputFile);
				System.out.println("Image successfully downloaded and saved as " + FilePath);
			} else {
				System.out.println("Failed to read image from URL");
			}

		} catch (IOException e) {
			System.out.println("Error downloading the image: " + e.getMessage());
		}
	}
}
