package API;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NonEnglishTextExtractor {
    public static void main(String[] args) {
        String inputString = "Hello, 你好, こんにちは, How are you?";

        // Regular expression to match non-English characters
        String regex = "[^\\p{ASCII}]";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(inputString);

        // Find and print non-English text in the input string
        StringBuilder nonEnglishText = new StringBuilder();
        while (matcher.find()) {
            nonEnglishText.append(matcher.group());
        }

        System.out.println("Non-English Text: " + nonEnglishText);
    }
}