package Torus_1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Mob_SwipeUpUntilElementIsVisible implements Nlp {
	@InputParams({ @InputParam(name = "xpath", type = "java.lang.String") })

	@Override
	public List<String> getTestParameters() throws NlpException {
		List<String> params = new ArrayList<>();
		return params;
	}

	@Override
	public StringBuilder getTestCode() throws NlpException {
		StringBuilder sb = new StringBuilder();
		return sb;
	}

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		String xpath = (String) attributes.get("xpath");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("waitForIdleTimeout", 0);

		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		IOSDriver driver1 = nlpRequestModel.getIosDriver();

		Duration impWait = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		boolean elementDisplay = false;
		try {
			if (driver != null) {

				Dimension screenSize = driver.manage().window().getSize();// Identify screen dimension
				int screenCenter = (int) (screenSize.getWidth() * 0.5);// Identify center point of screen for Y axis
				int startPoint = (int) (screenSize.getHeight() * 0.5);// Identify beginning point of scroll for X axis
				int endPoint = (int) (screenSize.getHeight() * 0.2);// Identify ending point of scroll

				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

				// Search Element until it is available on screen. If no, then perform below
				// operations

				while (true) {
					try {
						elementDisplay = driver.findElement(By.xpath(xpath)).isDisplayed();
						Sequence swipe = new Sequence(finger, 1);

						// Move finger into starting position
						swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(),
								screenCenter, startPoint));

						// Finger goes up into contact with screen
						swipe.addAction(finger.createPointerDown(0));

						// Finger moves to End Position
						swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(),
								screenCenter, endPoint));

						// Take out finger from screen
						swipe.addAction(finger.createPointerUp(0));

						driver.perform(Arrays.asList(swipe));

						nlpResponseModel.setStatus(CommonConstants.pass);
						nlpResponseModel.setMessage("Swiped up  until element is visible");

					} catch (Exception e) {
						nlpResponseModel.setStatus(CommonConstants.pass);
						nlpResponseModel.setMessage("Element is not visible");
						break;
					}
				}
				if (elementDisplay == false) {
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Element is not visible");
				}
			}
			
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to swipe up until element is visible");
		} finally {
			driver.manage().timeouts().implicitlyWait(impWait);
		}

		return nlpResponseModel;
	}
}
