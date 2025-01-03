package Torus_1;

import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FetchCurrentIpoDetails {
    public static void main(String[] args) throws Exception {
        // Sample JSON response
        String response = "["
                + "{"
                + "\"symbol\": \"INDOFARM\","
                + "\"companyName\": \"Indo Farm Equipment Limited\","
                + "\"series\": \"EQ\","
                + "\"issueStartDate\": \"31-Dec-2024\","
                + "\"issueEndDate\": \"02-Jan-2025\","
                + "\"status\": \"Active\","
                + "\"issueSize\": \"8470000\","
                + "\"issuePrice\": \"Rs.204 to Rs.215 \","
                + "\"sr_no\": 1"
                + "},"
                + "{"
                + "\"symbol\": \"SGLTL\","
                + "\"companyName\": \"Standard Glass Lining Technology Limited\","
                + "\"series\": \"EQ\","
                + "\"issueStartDate\": \"02-Jan-2025\","
                + "\"issueEndDate\": \"08-Jan-2025\","
                + "\"status\": \"Forthcoming\","
                + "\"issueSize\": \"30078840\","
                + "\"issuePrice\": \"Rs.133 to Rs.140 \","
                + "\"sr_no\": 2"
                + "}"
                + "]";

        // Parse the response into a JSONArray
        JSONArray jsonArray = new JSONArray(response);

        // Get the current date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String currentDateStr = sdf.format(new Date()); // Format the current date to match the date format in response

        // Initialize a new JSONArray to collect matching records
        JSONArray resultArray = new JSONArray();

        // Iterate through the array and filter based on date
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String issueStartDateStr = obj.getString("issueStartDate");
            String issueEndDateStr = obj.getString("issueEndDate");

            // Compare if current date is between start date and end date
            if (isCurrentDateBetween(issueStartDateStr, issueEndDateStr, currentDateStr)) {
                // Add matching record to the result array
                resultArray.put(obj);
            }
        }

        // If there's only one matching record, return it as a single element array
        if (resultArray.length() == 1) {
            System.out.println("Single matching record: " + resultArray.get(0));
        } else if (resultArray.length() > 1) {
            System.out.println("Multiple matching records: " + resultArray.toString());
        } else {
            System.out.println("No matching records found.");
        }
    }

    // Utility method to compare current date with start and end dates
    private static boolean isCurrentDateBetween(String startDateStr, String endDateStr, String currentDateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            Date startDate = sdf.parse(startDateStr);
            Date endDate = sdf.parse(endDateStr);
            Date currentDate = sdf.parse(currentDateStr);

            return (currentDate.equals(startDate) || currentDate.after(startDate)) && (currentDate.equals(endDate) || currentDate.before(endDate));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
