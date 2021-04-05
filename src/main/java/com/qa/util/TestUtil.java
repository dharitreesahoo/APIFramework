package com.qa.util;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.qa.base.TestBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestUtil {
	public Object convertJSONStoJSONObject(String stringToParse) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(stringToParse);
		return jsonObj;
	}
	/*public static String getValueByJPath(JSONObject responsejson, String jpath){
		Object obj = responsejson;
		for(String s : jpath.split("/")) 
			if(!s.isEmpty()) 
				if(!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if(s.contains("[") || s.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}*/
	public String getValueByJSONPath(Response response ,String path)
	{
		JsonPath jsonpath = response.jsonPath();
		String strValue = jsonpath.getString(path);
		return strValue;
		
	}
	public List<Object> getListValueByJSONPath(Response response ,String path)
	{
		JsonPath jsonpath = response.jsonPath();
		List<Object> strValue = jsonpath.getList(path);
		return strValue;
		
	}
	
}
