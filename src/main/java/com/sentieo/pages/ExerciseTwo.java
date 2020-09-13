package com.sentieo.pages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExerciseTwo {

	static Map<String, JSONObject> empinfo = new TreeMap<>();
	public static List<String> key = new ArrayList<String>();

	public static void main(String[] args) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			String currentDate = dtf.format(now);
			key.add("projectName");
			key.add("projectType");
			key.add("description");
			key.add("sqft");
			key.add("estimatedProjectValuation");
			key.add("number");
			key.add("noticeType");
			key.add("address");
			key.add("city");
			key.add("state");
			key.add("Zipcode");
			key.add("contact");
			key.add("contactPhone");
			key.add("contactEmail");
			key.add("contactAddress");
			key.add("owner");
			key.add("architect");
			key.add("openDate");
			key.add("addedTimeStamp");
			key.add("status");
			key.add("closeDate");
			key.add("link");

			StringBuffer response = new StringBuffer();

			URL url = new URL("https://data.sfgov.org/resource/p4e4-a5a7.json");// your url i.e fetch data from
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}

			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			while ((output = br.readLine()) != null) {
				response.append(output);

			}
			JSONArray arr = new JSONArray(response.toString());
			int j = 0;
			JSONObject obj1 = new JSONObject();
			for (j = 0; j < arr.length(); j++) {
				JSONObject obj = arr.getJSONObject(j);
				String filed_date = obj.getString("filed_date");
				// only add the new records found on that day
				if (filed_date.contains(currentDate)) {
					obj1 = new JSONObject();
					for (int i = 0; i < key.size(); i++) {
						try {
							obj1.put(key.get(i).toString(), obj.get(key.get(i).toLowerCase().trim()) + "");

						} catch (Exception e) {
							obj1.put(key.get(i).toString(), "0");
						}
					}
					empinfo.put(j + "", obj1);

				}
			}
			conn.disconnect();
			if (empinfo.size() > 0) {
				WriteDataToExcel aaww = new WriteDataToExcel();
				aaww.writeData(empinfo);
			}
		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
		}
	}
}
