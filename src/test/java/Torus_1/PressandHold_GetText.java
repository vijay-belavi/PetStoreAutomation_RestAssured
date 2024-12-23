package Torus_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class PressandHold_GetText {
	private static AndroidDriver driver;

	public static void main(String[] args) throws Throwable {
		// Initialize DesiredCapabilities and start the driver session
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");

		// Initialize the Appium driver
		driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		ExecutorService executor = Executors.newFixedThreadPool(2);

		// Task 1: Press and Hold Action
		Runnable pressAndHoldTask = () -> {
			// Command as a single string
			String command = "adb shell input swipe 500 1000 500 1000 6000";

			try {
				// Use ProcessBuilder to execute the command in cmd
				ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
				Process process = processBuilder.start();

				// Capture and display the command's output, if any
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}

				// Wait for the process to complete and get the exit code
				int exitCode = process.waitFor();
				System.out.println("Command executed with exit code: " + exitCode);

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		};
		// Task 2: Search Element Task (with explicit wait)
		Runnable searchElementTask = () -> {
			int count = 1;
			while (count < 5) {
				try {
					System.out.println("Searching for the element...");
					// Add a wait to ensure that the tooltip element has time to appear
					String text = driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'NAV')]"))
							.getAttribute("content-desc");
					System.out.println("Tooltip element found: " + text);
					if (text != null) {
						break;
					}
				} catch (Exception e) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					count++;

				}

			}

		};

		// Execute the tasks
		executor.execute(pressAndHoldTask);
		executor.execute(searchElementTask);

		// Wait for tasks to complete
		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);
	}
}
