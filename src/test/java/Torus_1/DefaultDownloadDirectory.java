package Torus_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DefaultDownloadDirectory {
    public static void main(String[] args) {
        try {
            // Command to query Windows Registry for the default download directory
            String command = "reg query \"HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\User Shell Folders\" /v {374DE290-123F-4565-9164-39C4925E467B}";
            
            // Execute the command
            Process process = Runtime.getRuntime().exec(command);
            
            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Downloads")) {
                    // Extract the path after the last space, ensuring the correct format
                    String downloadPath = line.split("\\s+")[line.split("\\s+").length - 1];
                    
                    // Normalize path if needed (optional)
                    if (downloadPath.contains("%USERPROFILE%")) {
                        String userProfile = System.getenv("USERPROFILE");
                        downloadPath = downloadPath.replace("%USERPROFILE%", userProfile);
                    }
                    
                    System.out.println("Default Download Directory: " + downloadPath);
                    return;
                }
            }
            
            System.out.println("Download directory not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
