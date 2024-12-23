package Torus_1;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Scalar;

public class BackgroundRemoval {
    public static void main(String[] args) {
        // Load the input image
        String imagePath = "C:\\Users\\User\\Downloads\\MainImage_1	.png";
        Mat image = opencv_imgcodecs.imread(imagePath);
        if (image.empty()) {
            System.err.println("Failed to load image from: " + imagePath);
            return;
        }

        // Create an empty mask for foreground extraction
        Mat mask = new Mat(image.size(), opencv_core.CV_8UC1, new Scalar(0));  // Initialize to 0 (background)

        // Perform GrabCut here (example using GC_FGD/GC_PR_FGD, etc.)

        // Now, manually update the mask using ptr() for fast access
        for (int y = 0; y < image.rows(); y++) {
            for (int x = 0; x < image.cols(); x++) {
                byte val = mask.ptr(y, x).get();  // Get pixel value at (x, y)

                // Check if it's foreground or probable foreground
                if (val == opencv_imgproc.GC_FGD || val == opencv_imgproc.GC_PR_FGD) {
                    mask.ptr(y, x).put((byte) 255);  // Set to 255 for foreground
                } else {
                    mask.ptr(y, x).put((byte) 0);    // Set to 0 for background
                }
            }
        }

        // Copy the foreground using the mask (mask is a binary mask now)
        Mat foreground = new Mat();
        image.copyTo(foreground, mask);

        // Save the result
        String outputPath = "C:\\Users\\User\\Downloads\\MainImage_2.png";
        opencv_imgcodecs.imwrite(outputPath, foreground);
        System.out.println("Background removed image saved to: " + outputPath);
    }
}
