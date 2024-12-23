package Navadhan;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import org.w3c.dom.NodeList;

public class GetImageSizeInCm {
    public static void main(String[] args) {
        String imgPath = "C:\\Users\\User\\Downloads\\Sign.png"; // Replace with your image path

        try {
            // Read the image file
            BufferedImage image = ImageIO.read(new File(imgPath));

            // Get image dimensions in pixels
            int widthInPixels = image.getWidth();
            int heightInPixels = image.getHeight();

            // Get DPI from image metadata
            int dpi = getDPI(new File(imgPath));
            if (dpi == 0) {
                dpi = 72; // Default DPI if not found in metadata
            }

            // Convert pixels to centimeters
            double widthInCm = widthInPixels / (double) dpi * 2.54;
            double heightInCm = heightInPixels / (double) dpi * 2.54;

            // Print the dimensions in centimeters
            System.out.println("Width: " + widthInCm + " cm");
            System.out.println("Height: " + heightInCm + " cm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getDPI(File file) throws IOException {
        int dpi = 0;
        try (ImageInputStream iis = ImageIO.createImageInputStream(file)) {
            ImageReader reader = ImageIO.getImageReaders(iis).next();
            reader.setInput(iis, true, true);
            IIOMetadata metadata = reader.getImageMetadata(0);
            for (String name : metadata.getMetadataFormatNames()) {
                IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree(name);
                NodeList nodes = root.getElementsByTagName("HorizontalPixelSize");
                if (nodes.getLength() > 0) {
                    IIOMetadataNode dpc = (IIOMetadataNode) nodes.item(0);
                    float density = Float.parseFloat(dpc.getAttribute("value"));
                    dpi = (int) (25.4f / density);
                    break;
                }
            }
            reader.dispose();
        }
        return dpi;
    }
}
