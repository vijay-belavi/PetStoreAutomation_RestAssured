package Navadhan;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import java.io.File;
import java.io.IOException;

public class AddESignature {
    public static void main(String[] args) throws IOException {
        String src = "C:\\Users\\User\\Downloads\\sample_table_for_esign.pdf";
        String dest = "C:\\Users\\User\\Downloads\\sample_table_for_esign.pdf(1)";
        String imgPath = "C:\\Users\\User\\Downloads\\Sign.png";

        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        Document document = new Document(pdfDoc);

        ImageData imageData = ImageDataFactory.create(imgPath);
        Image img = new Image(imageData);

        // Resize the image to fit the designated area
        img.setAutoScale(true);
        img.setWidth(UnitValue.createPointValue(20)); // Adjust width
        img.setHeight(UnitValue.createPointValue(20)); // Adjust height

        // Coordinates where the e-signature should be placed
        float x = 400;
        float y = 50;

        for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
            Rectangle pageSize = pdfDoc.getPage(i).getPageSize();
            float imgX = pageSize.getWidth() - x - img.getImageScaledWidth();
            float imgY = y;
            img.setFixedPosition(i, imgX, imgY);
            document.add(img);
        }
        System.out.println("Signture Done");
        document.close();
        pdfDoc.close();
    }
}