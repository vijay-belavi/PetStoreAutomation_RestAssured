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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestFileUpload {
	public static final String USERNAME = "newtest_kJ98gW";
	public static final String AUTOMATE_KEY = "vu5WrGaKu7QD2qDAo1dG";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public static void main(String[] args) throws Throwable {

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 11");
		browserOptions.setBrowserVersion("latest");
		Map<String, Object> browserStackOptions = new HashMap<>();
		browserStackOptions.put("name", "Sample Test");
		browserOptions.setCapability("browserStack:options", browserStackOptions);
		URL url = new URL(URL);

		WebDriver driver = new RemoteWebDriver(url, browserOptions);

//		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

		driver.get("https://ybluat.transxt.in/dmtadminui/#/login");

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

		WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
		System.out.println(mediaUrl);
		uploadElement.sendKeys(mediaUrl);
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
				// Matcher matcher = pattern.matcher(jsonResponse);

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

	public static String uploadFile(String apiEndpoint, String filePath, String username, String accessKey,
			String mediaUrl) throws IOException {
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
				mediaUrl = response.toString();

				/*
				 * String regex = "\"media_url\":\"media://([a-f0-9]{40,})\""; Pattern pattern =
				 * Pattern.compile(regex); Matcher matcher = pattern.matcher(jsonResponse);
				 * 
				 * // Find and return the media ID if (matcher.find()) { mediaUrl =
				 * matcher.group(1).toString(); } else { mediaUrl = "Media File Not Found"; }
				 */
			}
		} else

		{
			mediaUrl = "Failed to upload file to BrowserStack Remote Server";
		}
		connection.disconnect();
		return mediaUrl;
	}
}