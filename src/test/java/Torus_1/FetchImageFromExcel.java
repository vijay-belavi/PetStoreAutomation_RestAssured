package Torus_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FetchImageFromExcel {

    public static void main(String[] args) {
        try {
            // Load the Excel file
            FileInputStream excelFile = new FileInputStream("C:\\Users\\User\\Downloads\\Torus_IOS_TestData.xlsx");
            Workbook workbook = WorkbookFactory.create(excelFile);
            XSSFWorkbook xssfWorkbook = (XSSFWorkbook) workbook;
            
            // Get the sheet where the image is inserted
            XSSFSheet sheet = xssfWorkbook.getSheetAt(1);
            
            // Extract all the images (pictures) from the Excel sheet
            List<XSSFPictureData> pictures = xssfWorkbook.getAllPictures();
            for (XSSFPictureData picture : pictures) {
                // Get the image data
                byte[] data = picture.getData();
                
                // Save the image to a file
                try (FileOutputStream out = new FileOutputStream("extracted_image.png")) {
                    out.write(data);
                }
                System.out.println("Image extracted and saved successfully.");
            }

            workbook.close();
            excelFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}