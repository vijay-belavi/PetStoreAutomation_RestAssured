package Torus_1;


import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class SwipeUpAndStoreValuesBasedOnElementSwipe_Android {

	public static void main(String[] args) throws Throwable {
		HashSet<String> uniqueElements = new HashSet<>();
		ArrayList<String> arrayList = new ArrayList();
				

		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			// Set your desired capabilities
			caps.setCapability("appium:platformName", "Android");
			
			AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			String xpath = "//XCUIElementTypeImage[@name=\"Filters\"]/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther/XCUIElementTypeImage";
			String attributeValues = "label";

			String swipeElement = "//XCUIElementTypeImage[@name='Filters']/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView";

			uniqueElements = swipeAndStoreValues(driver, uniqueElements, xpath, attributeValues, swipeElement);
			arrayList.addAll(uniqueElements);
			for (String string : arrayList) {
				System.out.println(string);
			}
			System.out.println(arrayList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HashSet swipeAndStoreValues(AndroidDriver driver, HashSet<String> uniqueElements, String xpath,
			String attributeValues, String swipeElement) {
		try {
			boolean newElementsFound;

			do {
				newElementsFound = false;
				List<WebElement> elementsValues = driver.findElements(By.xpath(xpath));

				for (WebElement webElement : elementsValues) {
					String attributeValue = webElement.getAttribute(attributeValues); // Change to your required
																						// attribute
					if (uniqueElements.add(attributeValue)) {
						newElementsFound = true; // New element was added
					}
				}

				// Swipe up if new elements were found
				if (newElementsFound) {

					WebElement element = driver.findElement(By.xpath(swipeElement));
					// Dimension size = driver.manage().window().getSize();

					int width = element.getSize().getWidth();

					int startY = (int) (element.getSize().getHeight());
					int endY = (int) (element.getSize().getHeight() * 0.5);
					int startX = width / 2;

					// Using PointerInput to perform swipe
					PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
					Sequence swipe = new Sequence(finger, 0);

					swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
							startX, startY));
					swipe.addAction(finger.createPointerDown(0));
					swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(),
							startX, endY));
					swipe.addAction(finger.createPointerUp(0));

					driver.perform(List.of(swipe));
				}

			} while (newElementsFound); // Continue swiping until no new elements are found

		} catch (Exception e) {
			// swipeAndStoreValues(driver, uniqueElements, xpath, attributeValues);
			e.printStackTrace();

		}
		return uniqueElements;
	}
}
