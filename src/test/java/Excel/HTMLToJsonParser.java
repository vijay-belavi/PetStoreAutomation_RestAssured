package Excel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLToJsonParser {
    public static void main(String[] args) {
            // Replace "htmlContent" with the HTML content you want to parse

            Pattern pattern = Pattern.compile("<script.*?>(.*?)</script>", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(htmlContent);

            // Find the JSON data and print it
            while (matcher.find()) {
                String jsonData = matcher.group(1);
                System.out.println("Found JSON data:");
                System.out.println(jsonData);
    }
}
}