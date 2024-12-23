package Practice;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AudioRecordForCertainLength {

    public static void main(String[] args) throws MalformedURLException {
        int durationInSeconds = 10; // Duration in seconds
        
    	DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appPackage", "ai.mint.keyboard");
		caps.setCapability("appActivity", "com.mint.keyboard.ui.splash.SplashActivity");
		caps.setCapability("noReset", "true");
		caps.setCapability("autoGrantPermission", "true");

		AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4725/wd/hub"), caps);
		
        
        AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
        TargetDataLine line;

        try {
            line = AudioSystem.getTargetDataLine(format);
            line.open(format);
            line.start();

            byte[] buffer = new byte[4096];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            
            while ((System.currentTimeMillis() - startTime) < durationInSeconds * 1000) {
                bytesRead = line.read(buffer, 0, buffer.length);
                // Process or save the audio data as needed
            }

            line.stop();
            line.close();

            System.out.println("Audio recording complete.");
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
