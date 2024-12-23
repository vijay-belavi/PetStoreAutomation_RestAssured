package Practice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AudioRecorder {
	public static void main(String[] args) throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "ai.mint.keyboard");
		caps.setCapability("appActivity", "com.mint.keyboard.ui.splash.SplashActivity");
		caps.setCapability("noReset", "true");
		caps.setCapability("autoGrantPermission", "true");

		AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		
		AudioFormat format = new AudioFormat(44100, 16, 1, true, true); // Adjust format as needed
		TargetDataLine line;
		try {
			line = AudioSystem.getTargetDataLine(format);
			line.open(format);
			line.start();

			AudioInputStream audioInputStream = new AudioInputStream(line);

			String outputFilePath = "recorded_audio.wav"; // Change the file name and path
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new java.io.File(outputFilePath));

			System.out.println("Audio recorded and saved to " + outputFilePath);

			line.stop();
			line.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
