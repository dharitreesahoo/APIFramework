package com.qa.util;

import java.util.concurrent.TimeUnit;

import com.qa.base.TestBase;

import io.restassured.response.Response;
import junit.framework.Assert;

public class ResponseUtil {
	public void displayApplicationFailureMsgForRestAssured(Response response)
	{
		if(response.getStatusCode()==503)
		{
			TestBase.logExtentReport("Status code -503", "fail");
			TestBase.logExtentReport("Application is not available", "fail");
			Assert.assertTrue(false);
		}
		else if(response.getStatusCode()==401)
		{
			TestBase.logExtentReport("Status code -401", "fail");
			TestBase.logExtentReport("Application Error", "fail");
			Assert.assertTrue(false);
		}
		else if(response.getStatusCode()==407)
		{
			TestBase.logExtentReport("Status code -407", "fail");
			TestBase.logExtentReport("Cannot get response from Application", "fail");
			Assert.assertTrue(false);
		}
		else if(response.getStatusCode()==400)
		{
			TestBase.logExtentReport("Status code -400", "fail");
			TestBase.logExtentReport("Cannot make request when application is not connected", "fail");
			Assert.assertTrue(false);
		}
		else if(response.getStatusCode()==415)
		{
			TestBase.logExtentReport("Status code -415", "fail");
			TestBase.logExtentReport("Cannot make request when application is not connected", "fail");
			Assert.assertTrue(false);
		}else{
			TestBase.logExtentReport("Showing error code"+response.getStatusCode(), "fail");
			Assert.assertTrue(false);
		}
	}
	public void displayAppFailureforGrapQL(Response response)
	{
		if(response.getStatusCode()==500)
		{
			TestBase.logExtentReport("Status code -500", "fail");
			TestBase.logExtentReport("Internal server error", "fail");
			Assert.assertTrue(false);
		}
		else if(response.getStatusCode()==503)
		{
			TestBase.logExtentReport("Status code -403", "fail");
			TestBase.logExtentReport("Application is not availale", "fail");
			Assert.assertTrue(false);
		}else{
			TestBase.logExtentReport("Please check GraphQL API manually"+response.getStatusCode(), "fail");
			Assert.assertTrue(false);
		}
	}
	public void validateResponseTime(Response response)
	{
		long responseTime = 0;
		try {
			responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
			TestBase.logExtentReport("Time Taken to fetch reponse"+responseTime,"pass");
		} catch (Exception e) {
			TestBase.logExtentReport("Time Taken to fetch reponse"+responseTime,"fail");
			Assert.assertTrue(false);
		}
	}
	public void validateResponseStatusLine(Response response , String statusLine)
	{
		String responseStatusLine = response.getStatusLine();
		try {
			if(responseStatusLine.contains(statusLine))
			{
			TestBase.logExtentReport("Response sttaus line is as expected","pass");
			}else{
				
			}
		} catch (Exception e) {
			TestBase.logExtentReport("Response sttaus line is not as expected","fail");
			Assert.assertTrue(false);
		}
	}
	public void validateResponseContentType(Response response)
	{
		String responseStatusLine = response.getStatusLine();
		
	}
	public void validateResponseHeader()
	{
		
	}
	public void displayResponse(Response response){
		TestBase.logExtentReport("Response"+response.getBody().prettyPrint(), "info");
	}
	public void displayResponseAttribute(Response response){
		TestBase.logExtentReport("Response"+response.getBody().prettyPrint(), "info");
	}
	public void displayResponseAttributeAsList(Response response){
		TestBase.logExtentReport("Response"+response.getBody().prettyPrint(), "info");
	}

}
