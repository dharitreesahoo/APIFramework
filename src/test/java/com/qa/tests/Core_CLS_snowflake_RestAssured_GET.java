package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
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

public class Core_CLS_snowflake_RestAssured_GET extends TestBase {

	public Core_CLS_snowflake_RestAssured_GET() throws IOException {
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
	String sheetName ="CoreCLSSnowFlake_RestAssured";
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
	String TCID, Description,expectedStatus,expectedResponse,requestType,targetURL,strPayLoad,expectedStatusCode;
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
		header_authToken = new Header("Authorization","Bearer"+" "+authToken);
		headers = new Headers(header_contentType,header_authToken);
		
		
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
		getParamExcelData(row);
		response = apiUtil.sendResquestWithRestAssured(requestType, targetURL, strPayLoad, headers, paramMap);
		if(!response.body().asString().contains("Application Error")&&!(response.getStatusCode()!=407))
		{
			if(response.getStatusCode()==Integer.parseInt(expectedStatusCode))
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
		expectedStatusCode = excelHelper.getXLValue(sheetName, "ExpectedStatusCode", row);
		expectedResponse = excelHelper.getXLValue(sheetName, "ExpectedResponse", row);
		requestType = excelHelper.getXLValue(sheetName, "RequestType", row);
		targetURL = excelHelper.getXLValue(sheetName, "TargetURL", row);
	}
	public void getParamExcelData(int row)
	{
		paramMap.put("acctNbrs", excelHelper.getXLValue(sheetName, "acctNo", row));
		paramMap.put("startDate", excelHelper.getXLValue(sheetName, "startDate", row));
		paramMap.put("endDate", excelHelper.getXLValue(sheetName, "endDate", row));
		paramMap.put("dealType", excelHelper.getXLValue(sheetName, "dealType", row));
	}
	

}
