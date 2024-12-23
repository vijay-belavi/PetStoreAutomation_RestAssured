package Practice;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AudioToBinaryConverter {

    public static void main(String[] args) throws MalformedURLException {
    	
    	DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "ai.mint.keyboard");
		caps.setCapability("appActivity", "com.mint.keyboard.ui.splash.SplashActivity");
		caps.setCapability("noReset", "true");
		caps.setCapability("autoGrantPermission", "true");

		AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4725/wd/hub"), caps);
    	
        String audioFilePath = "C:\\Users\\User\\Downloads\\file_example_WAV_1MG.wav"; // Replace with your audio file path

        try {
            // Read the audio file as bytes
            Path path = Paths.get(audioFilePath);
            byte[] audioBytes = Files.readAllBytes(path);

            // Convert the bytes to binary representation
            StringBuilder binaryString = new StringBuilder();
            for (byte b : audioBytes) {
                String binary = Integer.toBinaryString(b & 0xFF); // Convert to positive value
                binaryString.append(String.format("%8s", binary).replace(' ', '0')); // Pad with zeros
            }

            // Print the binary representation
            
            binaryString.toString();
            
            String binaryData = binaryString.toString(); // Replace with your binary data
            
            // Make sure the binary data's length is a multiple of 8
            int length = binaryData.length();
            int extraZeros = 8 - (length % 8);
            if (extraZeros < 8) {
                binaryData = "0".repeat(extraZeros) + binaryData;
            }
            
            StringBuilder textBuilder = new StringBuilder();
            for (int i = 0; i < binaryData.length(); i += 8) {
                String binaryByte = binaryData.substring(i, i + 8);
                int decimal = Integer.parseInt(binaryByte, 2);
                char character = (char) decimal;
                textBuilder.append(character);
            }

            String text = textBuilder.toString();
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}