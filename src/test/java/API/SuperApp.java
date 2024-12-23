package API;

import java.util.Random;

public class SuperApp {
	public static void main(String[] args) throws Throwable {
		/*
		 * // Set the desired capabilities for your Android WebDriver
		 * DesiredCapabilities caps = new DesiredCapabilities();
		 * caps.setCapability("platformName", "Android");
		 * caps.setCapability("appPackage", "com.mosl.mobile.debug");
		 * caps.setCapability("appActivity", "mosl.supperfina.com.MainActivity");
		 * caps.setCapability("noReset", "true");
		 * 
		 * // Initialize the AndroidDriver AppiumDriver driver = new AndroidDriver(new
		 * URL("http://localhost:4723/wd/hub"), caps);
		 */
		        String inputString = "1234567890";
		        int numberOfDigits = 10;
		        char[] characters = inputString.toCharArray();
		        Random random = new Random();
		        String number = null;
		        StringBuilder randomNumber = new StringBuilder();
		        for (int i = 0; i < numberOfDigits; i++) {
		            int randomIndex = random.nextInt(characters.length);
		            number = randomNumber.append(characters[randomIndex]).toString();
		        }
		        System.out.println(number);
		    }
		}
