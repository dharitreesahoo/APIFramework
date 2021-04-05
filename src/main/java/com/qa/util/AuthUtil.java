package com.qa.util;

import java.util.HashMap;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AuthUtil {
	public String getToken(String tokenURL, HashMap<String, String> tokenMap) {
		Response response = RestAssured.given().relaxedHTTPSValidation()
				.contentType("application/x-www-form-urlencoded").formParams(tokenMap).when().post(tokenURL);
		return extractToken(response);
	}
	public String generateTokenWithBody(String tokenURL , String payLoad , Header header , String proxy)
	{
		Response response =  RestAssured.given().proxy(proxy).header(header).body(payLoad).when().post(tokenURL);
		return extractToken(response);
	}

	public String extractToken(Response response) {
		String access_tk = null;
		if (response.statusCode() == 200) {
			JSONObject jsonObj = new JSONObject(response.prettyPrint());
			access_tk = jsonObj.getString("access_token");
			// the above two lines can be done as below two lines
			/*
			 * JsonPath jsonPath = response.jsonPath();
			 * jsonPath.getString("access_token");
			 */
			String responseData = response.asString();
			String[] dataStr = responseData.split(":");
			String[] dataFinal = dataStr[1].split(",");

		}
		return access_tk;

	}
	

}
