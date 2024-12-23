package MobileWare;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class latestDownloadedFilePath{

    public static void main(String[] args) throws Throwable{
        String downloadDirectoryPath = "C:\\Users\\User\\Downloads"; // Replace with your download directory path

        try {
            File latestFile = getLatestFileFromDir(downloadDirectoryPath);
            if (latestFile != null) {
                System.out.println("Latest downloaded file: " + latestFile.getAbsolutePath());
            } else {
                System.out.println("No files found in the directory.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getLatestFileFromDir(String dirPath) throws IOException {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            return null;
        }

        return Arrays.stream(files)
                .filter(File::isFile)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
    }
}