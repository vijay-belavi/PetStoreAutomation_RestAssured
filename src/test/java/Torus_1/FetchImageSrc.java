package Torus_1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FetchImageSrc {
	public static void main(String[] args) {
		String srcValue = null;
		// Define the base URI of the webpage
		String url = "http://torusapis.cmots.com/AMCLogo/6011"; // Replace with the actual URL

		// Send an HTTP GET request
		Response response = RestAssured.get(url);

		// Check the response status
		if (response.getStatusCode() == 200) {
			// Parse the HTML response using Jsoup
			Document doc = Jsoup.parse(response.getBody().asString());

			// Select the <img> element and fetch the 'src' attribute
			Element imgElement = doc.selectFirst("img"); // Select the first <img> element
			if (imgElement != null) {
				srcValue = imgElement.attr("src");
				System.out.println("Image Source: " + srcValue);
			} else {
				System.out.println("No <img> element found in the HTML.");
			}
		} else {
			System.out.println("Failed to fetch the page. Status code: " + response.getStatusCode());
		}

		String imageUrl = "http://torusapis.cmots.com" + srcValue; // Replace with the image URL

		// Send GET request to download the image
		Response response1 = RestAssured.given().get(imageUrl);

		// Check if the request was successful
		if (response1.getStatusCode() == 200) {
			// Get the input stream of the image data
			InputStream inputStream = response1.asInputStream();

			// Define the local file path where the image will be saved
			File file = new File("C:\\Users\\User\\Downloads\\Image.png"); // Specify your local directory path

			// Save the image data to the local file
			try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, bytesRead);
				}
				System.out.println("Image downloaded successfully.");
			} catch (IOException e) {
				System.out.println("Error saving the image: " + e.getMessage());
			}
		} else {
			System.out.println("Failed to download the image. Status code: " + response1.getStatusCode());
		}
	}
}
