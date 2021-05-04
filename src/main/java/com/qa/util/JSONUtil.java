package com.qa.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class JSONUtil {

	public static String readFilesAsString() throws IOException {
		return new String(Files.readAllBytes(Paths.get(".\\src\\main\\java\\com\\qa\\util\\sampleJSON.json")));
	}
	//updates json payload any key value..this way also we can provide dynamic json body  .update the required value and then pass
	public static void parseObject(JSONObject json, String key ,String keyVal) throws IOException {
		System.out.println(json.get(key));
		json.put(key, keyVal);
		 //Write into the file
        try (FileWriter file = new FileWriter(".\\src\\main\\java\\com\\qa\\util\\sampleJSON.json")) 
        {
            file.write(json.toString());
            System.out.println("Successfully updated json object to file...!!");
        }
	}
	public static void getKey(JSONObject json, String key ,String keyVal) throws IOException {
		boolean exists = json.has(key);
		Iterator<?> keys;
		String nextKeys;
		if (!exists) {
			keys = json.keys();
			while (keys.hasNext()) {
				nextKeys = (String) keys.next();
				try {

					if (json.get(nextKeys) instanceof JSONObject) {

						if (exists == false) {
							getKey(json.getJSONObject(nextKeys), key,keyVal );
						}

					} else if (json.get(nextKeys) instanceof JSONArray) {
						JSONArray jsonarray = json.getJSONArray(nextKeys);
						for (int i = 0; i < jsonarray.length(); i++) {
							String jsonarrayString = jsonarray.get(i).toString();
							JSONObject innerJSOn = new JSONObject(jsonarrayString);
							if (exists == false) {
								getKey(innerJSOn, key,keyVal);
							}

						}

					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		} else {
			parseObject(json, key,keyVal);
		}
	}
	public static void main(String[] args) throws IOException, ParseException {
		String inputString = readFilesAsString();
		JSONObject inputJSONObject = new JSONObject(inputString);
		getKey(inputJSONObject, "name","dharitree");
	}
}
