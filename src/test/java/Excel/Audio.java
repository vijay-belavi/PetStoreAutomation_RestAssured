package Excel;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

    public static void main(String[] args) {
        String audioFilePath = "C:\\Users\\User\\Downloads\\English-This is a.wav";
        File audioFile = new File(audioFilePath);

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile)) {

            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            try (Clip clip = (Clip) AudioSystem.getLine(info)) {

                clip.open(audioInputStream);
                clip.start();

                // Wait for the clip to finish playing
                Thread.sleep(clip.getMicrosecondLength() / 1000);

            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedAudioFileException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}