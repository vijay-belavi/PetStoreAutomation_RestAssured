package Torus_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class FetchAllCompanies {

	public static void main(String[] args) {
		try {
			int pageSize = 100; // Number of items per page
			int currentPage = 1; // Start with the first page
			boolean morePages = true;
			ArrayList arrayList = new ArrayList();
			String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6InRvcnVzIiwicm9sZSI6IkFkbWluIiwibmJmIjoxNzA4MDg3MDQ3LCJleHAiOjE3Mzk2MjMwNDcsImlhdCI6MTcwODA4NzA0NywiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo1MDE5MSIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6NTAxOTEifQ.9iOCbtoHGPiziCDW_zDWrQfvYJZPuL91CfxxGZONQB0";
			while (morePages) {
				String apiUrl = "https://is-cug.torusdigital.com/api/v1/schemes/search?pageSize=" + pageSize
						+ "&pagination=" + currentPage;

				URL url = new URL(apiUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Bearer " + token);

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					response.append(line);
				}

				conn.disconnect();

				// Parse the response as JSON
				JSONObject jsonResponse = new JSONObject(response.toString());
				JSONArray companies = jsonResponse.getJSONArray("data"); // Adjust "companies" as per your API's
																			// response structure
				
				// Print or store the company names
				for (int i = 0; i < companies.length(); i++) {
					JSONObject company = companies.getJSONObject(i);
					String companyName = company.getString("schName");
					arrayList.add(companyName);// Adjust "name" based on your API's field
					//System.out.println(companyName);
					if (companyName.contains("Bandhan Small Cap Fund")) {
						System.out.println(company);
						break;
					}
				}

				// Check if more pages exist (assuming the API provides info on total items or
				// total pages)
				morePages = companies.length() == pageSize;
				currentPage++;
			}
			System.out.println(arrayList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}