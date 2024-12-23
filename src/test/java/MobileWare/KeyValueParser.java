package MobileWare;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyValueParser {

    public static void main(String[] args) {
        String input = "[MWTError [errorCode=DMT1066_ errorMsg=Email id  already exist._ fieldName=[]]]";
        Map<String, String> keyValueMap = extractKeyValuePairs(input);

        // Print the key-value pairs
        for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    public static Map<String, String> extractKeyValuePairs(String input) {
        Map<String, String> keyValueMap = new HashMap<>();

        // Define the regex pattern to match key=value pairs
        String patternString = "(\\w+)=([^\\]]+)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);

        // Find all matches and store them in the map
        while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();
            keyValueMap.put(key, value);
        }

        return keyValueMap;
    }
}