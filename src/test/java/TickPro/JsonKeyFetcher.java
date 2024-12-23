package TickPro;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class JsonKeyFetcher {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\User\\OneDrive\\Desktop\\Reliance_Tickpro_QuestionsAndAnswers.json";
        String keyToSearch = "Most famous landmark near your home";
        
        try {
            // Read the JSON file as a string
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Convert the string to a JSONObject
            JSONObject jsonObject = new JSONObject(content);

            // Fetch the value for the given key
            if (jsonObject.has(keyToSearch)) {
                Object value = jsonObject.get(keyToSearch);
                System.out.println("The value for the key '" + keyToSearch + "' is: " + value);
            } 
            
            else {
                System.out.println("The key '" + keyToSearch + "' was not found in the JSON data.");
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
