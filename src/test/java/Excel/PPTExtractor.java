package Excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class PPTExtractor {

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\User\\OneDrive\\Desktop\\New folder\\test.pptx");
            extractTextFromPPT(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractTextFromPPT(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             XMLSlideShow ppt = new XMLSlideShow(fis)) {
            for (XSLFSlide slide : ppt.getSlides()) {
                // Extract text from each text shape in the slide
                for (XSLFShape shape : slide.getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape textShape = (XSLFTextShape) shape;
                        System.out.println(textShape.getText());
                        
                    }
                }
            }
        }
    }
}