package MobileWare;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ZipExtractor {

    public static void extractZipFile(String zipFilePath, String destDir) throws IOException {
        File dir = new File(destDir);
        // Create output directory if it doesn't exist
        if (!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        // Buffer for read and write data to file
        byte[] buffer = new byte[1024];
        fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry ze = zis.getNextEntry();
        while (ze != null) {
            String fileName = ze.getName();
            File newFile = new File(destDir + File.separator + fileName);
            System.out.println("Unzipping to " + newFile.getAbsolutePath());
            // Create directories for sub directories in zip
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            // Close this ZipEntry
            zis.closeEntry();
            ze = zis.getNextEntry();
        }
        // Close last ZipEntry
        zis.closeEntry();
        zis.close();
        fis.close();
    }

    public static void main(String[] args) {
        try {
            String zipFilePath = "C:\\Users\\User\\Downloads\\agentMigrationReport-ULZaZq.zip";
            String destDir = "C:\\Users\\User\\Downloads\\agentMigrationReport-ULZaZq";
            extractZipFile(zipFilePath, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}