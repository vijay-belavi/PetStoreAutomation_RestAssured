package Torus_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFileWithString {
	public static void main(String[] args) {
		// Define file name and extension
		String fileName = "example"; // Name of the file (without extension)
		String fileExtension = ".docx"; // Desired extension
		String filePath = "C://Users//User//"; // Specify the desired path

		// Create the full file path
		String fullFilePath = filePath + fileName + fileExtension;

		// The string to be written to the file
		String a = "4";
		String content = "a = \"" + a + "\";";

		// Create a File object
		File file = new File(fullFilePath);

		try {
			// Create the file if it doesn't exist
			if (!file.exists()) {
				if (file.createNewFile()) {
					System.out.println("File created: " + file.getAbsolutePath());
				} else {
					System.out.println("Failed to create the file.");
					return; // Exit if the file cannot be created
				}
			} else {
				System.out.println("File already exists. It will be overwritten: " + file.getAbsolutePath());
			}

			// Write the string to the file (this will replace the existing content)
			try (FileWriter writer = new FileWriter(file, false)) { // 'false' ensures overwriting
				writer.write(content);
				System.out.println("Content written to the file: " + content);
			}

		} catch (IOException e) {
			System.out.println("An error occurred while creating or writing to the file.");
			e.printStackTrace();
		}
	}
}