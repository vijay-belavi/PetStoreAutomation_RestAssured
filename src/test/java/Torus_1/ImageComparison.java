package Torus_1;

import org.sikuli.script.ScreenImage;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics2D;

public class ImageComparison {
    public static void main(String[] args) {
        try {
            // Load images using SikuliX
            BufferedImage img1 = ImageIO.read(new File("C:\\Users\\User\\Downloads\\MainImage.png"));
            BufferedImage img2 = ImageIO.read(new File("C:\\Users\\User\\Downloads\\SubImage.png"));

            // Resize images to the same dimensions (choose the smaller width/height)
            int commonWidth = Math.min(img1.getWidth(), img2.getWidth());
            int commonHeight = Math.min(img1.getHeight(), img2.getHeight());

            BufferedImage resizedImg1 = resizeImage(img1, commonWidth, commonHeight);
            BufferedImage resizedImg2 = resizeImage(img2, commonWidth, commonHeight);
            
            ImageIO.write(resizedImg1, "png", new File("C:\\Users\\User\\Downloads\\ResizedMainImage.png"));
            ImageIO.write(resizedImg2, "png", new File("C:\\Users\\User\\Downloads\\ResizedSubImage.png"));


            // Compare the resized images pixel by pixel
            boolean imagesAreEqual = compareImages(resizedImg1, resizedImg2);

            if (imagesAreEqual) {
                System.out.println("Images are the same.");
            } else {
                System.out.println("Images are different.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to resize an image
    private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    // Method to compare two images pixel by pixel
    private static boolean compareImages(BufferedImage img1, BufferedImage img2) {
        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}
