package API;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.amazonaws.services.resourcegroupstaggingapi.model.ThrottledException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ApiTest {
	public static void main(String[] args) throws Throwable{
		
		String text = " Photo 🔴 *Join NOW:* I am Live on *EloElo* App! ✅ Follow and Chat with me ✅ Watch Ad Free Exclusive Live Streams ✅ Make Friends and Join Call 👉🏻 *Download & Join my Live NOW:* https://eloeloapp.go.link/?adj_t=14j1y6jf&adj_campaign=cr&adj_deeplink=https%3A%2F%2Fwww.eloelo.in%2FreferId%3D47062639%3F%26share%26creatorUserId%3D47062639%26userid%3D47062639 👈🏻 Received at 14:25\r\n"
				+ "";
		String regexPattern = "https?://\\S+";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object
		Matcher matcher = pattern.matcher(text);

		String string3  = null;
		// Find and print the link using regex
		if (matcher.find()) {
			String extractedLink = matcher.group();
			string3 = extractedLink;
			
		} else {
			System.out.println("Link not found in the string.");
		}
		
		System.out.println(string3);
	}
}