package Torus_1;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class ConvertJsonArraytoList {
public static void main(String[] args) {
	
	// JSON array as a string
    String jsonArrayString = "[\"Finance\", \"Debt\", \"Pharmaceuticals & Biotech\", \"Realty\", \"Banks\", \"Consumer Durables\", \"Chemicals & Petrochemical\", \"Capital Markets\", \"Electrical Equipments\", \"Industrial Products\", \"Textiles & Apparels\", \"Agricultural Food & other\", \"Auto Components\", \"Commercial Services & Sup\", \"Healthcare Services\", \"Transport Services\", \"Retailing\", \"Industrial Manufacturing\", \"Construction\", \"Leisure Services\", \"IT-Hardware\", \"Diversified Metals\", \"IT-Services\", \"Ferrous Metals\", \"Cash & Others\", \"Power\", \"Beverages\", \"Petroleum Products\", \"Food Products\", \"IT-Software\", \"Cement & Cement Products\", \"Aerospace & Defense\", \"Diversified\", \"Fertilisers & Agrochemica\", \"Financial Technology (Fin\", \"Non-Ferrous Metals\", \"Other Utilities\", \"Telecom Equipment & Acces\", \"Household Products\", \"Telecom-Services\", \"Personal Products\", \"Gas\", \"Automobiles\", \"Minerals & Mining\", \"Others\", \"Paper, Forest & Jute Prod\", \"Entertainment\", \"Miscellaneous\"]";

    // Convert JSON array string to JSONArray
    JSONArray jsonArray = new JSONArray(jsonArrayString);

    // Convert JSONArray to List
    List<String> list = new ArrayList<>();
    for (int i = 0; i < jsonArray.length(); i++) {
        list.add(jsonArray.getString(i));
    }

    // Print the list
    System.out.println("Converted List: " + list);
}
}
