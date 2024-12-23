package MobileWare;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class File_Upload {
	public static final String USERNAME = "newtest_kJ98gW";
	public static final String AUTOMATE_KEY = "vu5WrGaKu7QD2qDAo1dG";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public static void main(String[] args) throws Throwable {
		
		  ChromeOptions browserOptions = new ChromeOptions();
		  browserOptions.setPlatformName("Windows 11");
		  browserOptions.setBrowserVersion("latest"); Map<String, Object>
		  browserStackOptions = new HashMap<>(); browserStackOptions.put("name",
		  "Sample Test"); browserOptions.setCapability("browserStack:options",
		  browserStackOptions); URL url = new URL(URL);
		  
		  WebDriver driver = new RemoteWebDriver(url, browserOptions);
		 
//		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

		driver.get("https://ybluat.transxt.in/dmtadminui/#/login");
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("sujan.r@fireflink.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("q$nsCjVQ2T");
		String captcha = driver.findElement(By.xpath("//span[@id='captchaText']")).getText().replaceAll("[^a-zA-Z0-9]",
				"");
		driver.findElement(By.xpath("//input[@placeholder='Captcha']")).sendKeys(captcha);
		driver.findElement(By.xpath("//button[text()='Login']")).click();

		// Locate the element you want to hover over
		WebElement elementToHover = driver.findElement(By.xpath("//a[@id='reports' and contains(text(),'More')]"));
		// Create an Actions object
		Actions actions = new Actions(driver);
		// Perform the mouse hover action
		actions.moveToElement(elementToHover).perform();

		// Define the WebDriverWait with a timeout (e.g., 10 seconds)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait until the element is clickable
		WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@aria-labelledby='reports']//a[contains(text(),'Bulk Upload')]")));
		WebElement elementToClick = driver
				.findElement(By.xpath("//div[@aria-labelledby='reports']//a[contains(text(),'Bulk Upload')]"));

		// Click the element using JavaScript
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", elementToClick);

		WebElement element = driver.findElement(By.xpath("//select[@id='fileTypeList']"));
		Select select = new Select(element);
		select.selectByVisibleText("Agent");

		String apiEndpoint = "https://api-cloud.browserstack.com/automate/upload-media";
		String filePath = "C:\\Users\\User\\Downloads\\agentFile.csv"; // Replace with your file path
		String username = USERNAME; // Replace with your username
		String accessKey = AUTOMATE_KEY; // Replace with your access key

		String mediaUrl = null;
		try {
			mediaUrl = uploadFile(apiEndpoint, filePath, username, accessKey);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='File']"));
		System.out.println(mediaUrl);
		uploadElement.sendKeys("media://d6fd63e763e1f2f490ac70df8c85ee7ba0a2ff8f");
		Thread.sleep(5000);
		driver.quit();
	}

	public static String uploadFile(String apiEndpoint, String filePath, String username, String accessKey)
			throws IOException {
		File file = new File(filePath);
		String boundary = "Boundary-" + System.currentTimeMillis();
		URL url = new URL(apiEndpoint);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		String auth = username + ":" + accessKey;
		String encodedAuth = java.util.Base64.getEncoder().encodeToString(auth.getBytes());
		connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

		try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
			out.writeBytes("--" + boundary + "\r\n");
			out.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
			out.writeBytes("Content-Type: " + Files.probeContentType(file.toPath()) + "\r\n\r\n");
			Files.copy(file.toPath(), out);
			out.writeBytes("\r\n--" + boundary + "--\r\n");
		}

		int responseCode = connection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				String jsonResponse = response.toString();
//				String regex = "media_url\":\"media://([a-f0-9]{40,})";
//				Pattern pattern = Pattern.compile(regex);
				//Matcher matcher = pattern.matcher(jsonResponse);

				// Find and return the media ID
				/*
				 * if (matcher.find()) { return matcher.group(1); }
				 */
				return jsonResponse;
			}
		} else {
			System.out.println("Failed to upload file");
		}
		connection.disconnect();
		return null;
	}
}