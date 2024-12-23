package Excel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

public class FileComparison {

    public static void main(String[] args) {
        try {
            // Load PDF file
            File pdfFile = new File("C:\\Users\\User\\OneDrive\\Desktop\\New folder\\additionalSlip1.pdf");
            String pdfText = extractTextFromPDF(pdfFile);

            // Load PPTX file
            File pptxFile = new File("C:\\Users\\User\\OneDrive\\Desktop\\Testing.pptx");
            String pptxText = extractTextFromPPTX(pptxFile);

            // Compare the extracted text
            if (pdfText.equals(pptxText)) {
            	//System.out.println("PDF Data: "+pdfText);
            	//System.out.println("PPT Data: "+pptxText);
                System.out.println("PDF and PPTX files have the same content.");
            } else {
            	System.out.println("PDF Data: "+pdfText);
            	System.out.println("PPT Data: "+pptxText);
                System.out.println("PDF and PPTX files have different content.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String extractTextFromPDF(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    private static String extractTextFromPPTX(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             XMLSlideShow pptx = new XMLSlideShow(fis)) {
            StringBuilder text = new StringBuilder();
            for (XSLFSlide slide : pptx.getSlides()) {
                text.append(slide.getTitle());
            }
            return text.toString();
        }
    }
}