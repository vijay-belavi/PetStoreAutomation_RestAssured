package Navadhan;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class EsignInPdf {
    public static void main(String[] args) {
        String dest = "C:\\Users\\User\\Downloads\\sample_table_for_esign.pdf";
        String imgPath = "C:\\Users\\User\\Downloads\\ESign.png";

        try {
        	
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            ImageData imageData = ImageDataFactory.create(imgPath);
            Image image = new Image(imageData);

            //image position at the bottom-right corner
            image.setFixedPosition(pdfDoc.getDefaultPageSize().getWidth() - image.getImageScaledWidth() - 01, 01);

            // Add image to the document
            document.add(image);
            document.close();
            pdfDoc.close();

            System.out.println("Image inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}