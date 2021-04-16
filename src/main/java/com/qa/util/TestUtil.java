package com.qa.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public static String graphQLToJSON(String payload)
	{
		JSONObject jsonObj =  new JSONObject();
		jsonObj.put("query", payload);
		return jsonObj.toString();
		
	}
	public void convertStringToJSONObject(String str)
	{
		JSONObject jsonbj= new JSONObject(str);
	}
	public void writeStringToJSONFileAsString(String jsonString , String strPath) throws JsonGenerationException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(strPath), jsonString);
	}
	public void writeStringToJSONFile(String jsonString , String strPath) throws IOException
	{
		JSONObject responseJSON = new JSONObject(jsonString);
		FileWriter file =  new FileWriter(strPath);
		file.write(responseJSON.toString());
		file.close();
	}
	
}
