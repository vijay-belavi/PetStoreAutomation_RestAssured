package Torus_1;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;

public class MultiTouchSimulator {

	public static void main(String[] args) throws Throwable {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		// Set your desired capabilities here
		capabilities.setCapability("appium:platformName", "iOS");
		capabilities.setCapability("appium:platformVersion", "17.5.1");
		capabilities.setCapability("appium:deviceName", "Ast");
		capabilities.setCapability("appium:automationName", "XCUITest");
		capabilities.setCapability("appium:udid", "00008110-000C45143C32401E");
		capabilities.setCapability("appium:bundleId", "com.google.Gmail");

		IOSDriver driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

	}
}
