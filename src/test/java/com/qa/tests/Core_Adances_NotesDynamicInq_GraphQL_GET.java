package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.APIUtil;
import com.qa.helper.AssertionHelper;
import com.qa.helper.ExcelHelper;
import com.qa.util.AuthUtil;
import com.qa.util.ResponseUtil;
import com.qa.util.TestUtil;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Core_Adances_NotesDynamicInq_GraphQL_GET extends TestBase {

	public Core_Adances_NotesDynamicInq_GraphQL_GET() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	APIUtil apiUtil;
	TestUtil testUtil;
	ExcelHelper excelHelper;
	ResponseUtil responseUtil;
	AuthUtil authUtil;
	HashMap<String ,String> headerMap;
	HashMap<String ,String> paramMap;
	HashMap<String ,String> tokenMap;
	String sheetName ="CoreAdvancesNotes_GraphQL";
	String expectedResponsePath = System.getProperty("user.dir")+"\\resources\\Core_Responses\\Core_AdancesNotesDynamicInq";
	int row;
	String authToken;
	Header header_contentType;
	Header header_acceptEncoding;
	Header header_accept;
	Header header_connection;
	Header header_DNT;
	Header header_origin;
	Header header_authToken;
	Headers headers;
	String TCID, Description,expectedStatus,expectedResponse,requestType,acctNo,noteNbr,payload;
	String payLoadGraphQL;
	String proxy;
	Response response;
	
	@BeforeClass
	void startClass()
	{
		excelHelper = new ExcelHelper(System.getProperty("user.dir")+"\\resources\\CoreAPI.xlsx");
		row = excelHelper.getCellRowNum(sheetName, "TCID", "TC1");
		apiUtil = new APIUtil();
		testUtil = new TestUtil();
		responseUtil = new ResponseUtil();
		authUtil = new AuthUtil();
		
		tokenMap = new HashMap<String ,String>();
		tokenMap.put("client_id", excelHelper.getXLValue(sheetName, "clientId", row));
		tokenMap.put("client_secret", excelHelper.getXLValue(sheetName, "clientSecret", row));
		tokenMap.put("scope", excelHelper.getXLValue(sheetName, "scope", row));
		tokenMap.put("grant_type", excelHelper.getXLValue(sheetName, "grantType", row));
		String tokenURL = excelHelper.getXLValue(sheetName, "tokenURL", row);
		authToken = authUtil.getToken(tokenURL, tokenMap);
		
		header_contentType = new Header("content-type","application/json");
		header_acceptEncoding = new Header("AcceptEncoding","gzip");
		header_accept = new Header("Accept","application/json");
		header_connection = new Header("connection","keep-alive");
		header_DNT = new Header("DNT","1");
		header_origin = new Header("Origin","https://lendingcorpfrbnp1com");
		header_authToken = new Header("Authorization","Bearer"+" "+authToken);
		headers = new Headers(header_contentType,header_contentType,header_accept,header_connection,header_DNT,header_origin,header_authToken);
		
		
		//applicable for Restassured GET call
		paramMap = new HashMap<String ,String>();
		proxy = "";
	}
	@Test(priority=1,enabled=true)
	public void verifySuccessResponse()
	{
		row = excelHelper.getCellRowNum(sheetName, "TCID", "TC1");
		assignTCDescription(row);
		getExceldata(row);
		payload = getPayLoad(row);
		String queryPayLoadGraphQL = graphQLToJSON(payload);
		response = apiUtil.sendRequestWithGraphQL(requestType, "HOSTURL", queryPayLoadGraphQL, headers,proxy);
		if(!response.body().asString().contains("Internal server error")
				&&response.body().asString().contains("cannot query field")
				&&response.body().asString().contains("Application is not available"))
		{
			//Here we cannot go ahead with status code beceause even if reponse has error it gives sttatauuc code 200
			if(response.body().asString().contains(expectedStatus))
			{
				responseUtil.displayResponse(response);
				AssertionHelper.markPass(TCID +":"+Description);
			}else{
				AssertionHelper.markFail(TCID +":"+Description);
			}
			
		}else{
			responseUtil.displayAppFailureforGrapQL(response);
			AssertionHelper.markFail(TCID +":"+Description);
		}
		
		
		
	}
	//****************************************************8888
	public void assignTCDescription(int row)
	{
		TCID = excelHelper.getXLValue(sheetName, "TCID", row);
		Description = excelHelper.getXLValue(sheetName, "Description", row);
		Childtest = test.createNode(TCID +":"+Description);
	}
	public void getExceldata(int row)
	{
		expectedStatus = excelHelper.getXLValue(sheetName, "ExpectedStatus", row);
		expectedResponse = excelHelper.getXLValue(sheetName, "ExpectedResponse", row);
		requestType = excelHelper.getXLValue(sheetName, "RequestType", row);
	}
	public String getPayLoad(int row)
	{
		acctNo = excelHelper.getXLValue(sheetName, "AcctNo", row);
		noteNbr = excelHelper.getXLValue(sheetName, "NoteNbr", row);
		return payload="{\n loanAccount(acctNbr: "+acctNo+")....}";
		
	}
	public String graphQLToJSON(String payload)
	{
		JSONObject json = new JSONObject();
		json.put("query", payload);
		return json.toString();
		
	}
	

}
