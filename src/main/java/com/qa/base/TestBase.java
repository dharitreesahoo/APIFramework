package com.qa.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestBase {
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest Childtest;
	
	@BeforeSuite
	public void beforeSuite()
	{
		extent.createTest(getClass().getSimpleName());
	}
	@BeforeTest
	public void beforeTest()
	{
		test=extent.createTest(getClass().getSimpleName());
	}
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			
		}else if(result.getStatus()==ITestResult.SUCCESS)
		{
			
		}else if(result.getStatus()==ITestResult.SKIP)
		{
			
		}
		extent.flush();
	}
	public static void logExtentReport(String steps,String status)
	{
		if(status.toUpperCase().equals("PASS"))
		{
			Childtest.log(Status.PASS, MarkupHelper.createLabel(steps, ExtentColor.GREEN));
		}else if(status.toUpperCase().equals("FAIL"))
		{
			Childtest.log(Status.PASS, MarkupHelper.createLabel(steps, ExtentColor.RED));
		}else if(status.toUpperCase().equals("WARNING"))
		{
			Childtest.log(Status.PASS, MarkupHelper.createLabel(steps, ExtentColor.ORANGE));
		}else{
			Childtest.log(Status.PASS, MarkupHelper.createLabel(steps, ExtentColor.BLUE));
		}
	}


}
