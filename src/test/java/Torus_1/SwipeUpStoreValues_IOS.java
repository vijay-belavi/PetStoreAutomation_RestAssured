package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;

public class SwipeUpStoreValues_IOS {

	public static void main(String[] args) throws Throwable {

		HashSet hashSet = new HashSet();

		ArrayList arrayList = new ArrayList();

		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("appium:platformName", "iOS");
			caps.setCapability("appium:platformVersion", "17.5.1"); // Update to match your iOS version
			caps.setCapability("appium:deviceName", "Ast");
			caps.setCapability("appium:automationName", "XCUITest"); // Path to your .app or .ipa file
			caps.setCapability("appium:udid", "00008110-000C45143C32401E"); // Path to your .app or .ipa file

			caps.setCapability("appium:bundleId", "com.google.Gmail"); // Path to your .app or .ipa file
			caps.setCapability("appium:agentPath",
					"/Applications/FireFlinkClient.app/Contents/Resources/flinko-client/exec/appium-webdriveragent/WebDriverAgent.xcodeproj");
			caps.setCapability("appium:bootstrapPath",
					"/Applications/FireFlinkClient.app/Contents/Resources/flinko-client/exec/appium-webdriveragent");

			IOSDriver driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			Thread.sleep(10000);
			String xpath = "//XCUIElementTypeOther[@name=\"Mutual Fund\"]/../../../following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther/XCUIElementTypeImage";
			int count = 44;
			String attributeValue = "label";
			arrayList = swipeAndStoreValues(driver, xpath, hashSet, count, attributeValue, arrayList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList swipeAndStoreValues(IOSDriver driver, String xpath, HashSet<String> hashSet, int count,
			String attributeValue, ArrayList<String> arrayList) {
		try {
			while (hashSet.size() < count) {
				List<WebElement> elementsValues = driver.findElements(By.xpath(xpath));

				for (WebElement webElement : elementsValues) {
					String attributeValues = webElement.getAttribute(attributeValue);
					hashSet.add(attributeValues); // Add directly to HashSet
				}
				System.out.println("List Count: " + arrayList.size() + " Set size: " + hashSet.size());
				System.out.println();
				if (hashSet.size() == count) {
					arrayList.addAll(hashSet);
					break;
				} else {
					Dimension size = driver.manage().window().getSize();
					int width = size.getWidth();
					int height = size.getHeight();

					int startX = width / 2;
					int startY = (int) (height * 0.8);
					int endY = (int) (height * 0.2);

					// Using PointerInput to perform swipe
					PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
					Sequence swipe = new Sequence(finger, 0);

					swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
							startX, startY));
					swipe.addAction(finger.createPointerDown(0)); // Specify pointer ID
					swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),
							startX, endY));
					swipe.addAction(finger.createPointerUp(0)); // Specify pointer ID

					// Perform the swipe action
					driver.perform(Collections.singletonList(swipe));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
}
