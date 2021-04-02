package com.qa.client;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class APIUtil {
	Response responseData;
	public Response sendResquestWithRestAssured(String requestType , String proxy , String targetURL ,String entityString , Headers headers , Map<String , String> queryParam)
	{
		switch (requestType) {
		case "GET":
			responseData = RestAssured.given().proxy(proxy).relaxedHTTPSValidation().headers(headers).queryParams(queryParam).when().get(targetURL).thenReturn();
			break;
		case "POST":
			responseData = RestAssured.given().proxy(proxy).relaxedHTTPSValidation().headers(headers).queryParams(queryParam).body(entityString).when().post(targetURL).thenReturn();
			break;
		case "PUT":
			responseData = RestAssured.given().proxy(proxy).relaxedHTTPSValidation().headers(headers).queryParams(queryParam).body(entityString).when().put(targetURL).thenReturn();
			break;
		default:
			break;
		}
		return null;
		
	}
	//method overloading
	public Response sendResquestWithRestAssured(String requestType ,  String targetURL ,String entityString , Headers headers , Map<String , String> queryParam)
	{
		switch (requestType) {
		case "GET":
			responseData = RestAssured.given().relaxedHTTPSValidation().headers(headers).queryParams(queryParam).when().get(targetURL).thenReturn();
			break;
		case "POST":
			responseData = RestAssured.given().relaxedHTTPSValidation().headers(headers).queryParams(queryParam).body(entityString).when().post(targetURL).thenReturn();
			break;
		case "PUT":
			responseData = RestAssured.given().relaxedHTTPSValidation().headers(headers).queryParams(queryParam).body(entityString).when().put(targetURL).thenReturn();
			break;
		default:
			break;
		}
		return null;
		
	}
	public static Response upload(String targetURL , Map<String , Object> body , Headers headers,String fileToUpload , String fileFormat , String fileKey)
	{
		return RestAssured.given().relaxedHTTPSValidation().headers(headers).multiPart(fileKey ,"","").formParams(body).post(targetURL).thenReturn();
		
	}

public Response sendResquestWithRestAssured(String requestType , String proxy , String targetURL ,String entityString , Headers headers , Map<String , String> queryParam,String basicAuth)
	{
		String username = basicAuth.split(":")[0];
		String password = basicAuth.split(":")[1];
		
		switch (requestType) {
		case "GET":
			responseData = RestAssured.given().relaxedHTTPSValidation().auth().basic(username, password).headers(headers).queryParams(queryParam).when().get(targetURL).thenReturn();
			break;
		case "POST":
			responseData = RestAssured.given().relaxedHTTPSValidation().auth().basic(username, password).headers(headers).queryParams(queryParam).body(entityString).when().post(targetURL).thenReturn();
			break;
		case "PUT":
			responseData = RestAssured.given().relaxedHTTPSValidation().auth().basic(username, password).headers(headers).queryParams(queryParam).body(entityString).when().put(targetURL).thenReturn();
			break;
		default:
			break;
		}
		
		return responseData;
		
	}
public void sendRequestWithGraphQL(String requestType, String targetURL , String entityString , Headers headers)
{
	switch (requestType) {
	case "GET":
		responseData = RestAssured.given().relaxedHTTPSValidation().headers(headers).body(entityString).when().post(targetURL).thenReturn();
		
		break;

	default:
		break;
	}
	
}


}
