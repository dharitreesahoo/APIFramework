package com.qa.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class JSONUtil {
	public static void parseObject(JSONObject json, String key) {
		System.out.println(json.has(key));
		System.out.println(json.get(key));
	}
	public static void getKey(JSONObject json, String key) {
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
							getKey(json.getJSONObject(nextKeys), key);
						}

					} else if (json.get(nextKeys) instanceof JSONArray) {
						JSONArray jsonarray = json.getJSONArray(nextKeys);
						for (int i = 0; i < jsonarray.length(); i++) {
							String jsonarrayString = jsonarray.get(i).toString();
							JSONObject innerJSOn = new JSONObject(jsonarrayString);
							if (exists == false) {
								getKey(innerJSOn, key);
							}

						}

					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		} else {
			parseObject(json, key);
		}
	}

	public static String readFilesAsString() throws IOException {
		return new String(Files.readAllBytes(Paths.get(".\\src\\main\\java\\com\\qa\\util\\sampleJSON.json")));
	}

	public static void main(String[] args) throws IOException, ParseException {
		String inputString = readFilesAsString();
		JSONObject inputJSONObject = new JSONObject(inputString);
		getKey(inputJSONObject, "type");
	}
}
