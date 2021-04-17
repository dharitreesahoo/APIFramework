package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import junit.framework.Assert;

public class TestBase {
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest Childtest;
	Properties prop;
	TestBase testBase;
	
	public TestBase() throws IOException
	{
		prop = new Properties();
		FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/config.properties");
		prop.load(ip);
	}
	public void writeToConfigFile(String strKey , String strValue) throws IOException
	{
		OutputStream op=new FileOutputStream(System.getProperty("user.dir")+"/src/main/java/com/config.properties");
		prop.setProperty(strKey , strValue);
		prop.store(op, null);
	}
	public String readFromConfigFile(String strKey)
	{
		String strVal = prop.getProperty(strKey);
		return strVal;
	}
	
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
			Assert.assertTrue(true);
		}else if(status.toUpperCase().equals("FAIL"))
		{
			Childtest.log(Status.PASS, MarkupHelper.createLabel(steps, ExtentColor.RED));
			Assert.assertTrue(false);
		}else if(status.toUpperCase().equals("WARNING"))
		{
			Childtest.log(Status.PASS, MarkupHelper.createLabel(steps, ExtentColor.ORANGE));
		}else{
			Childtest.log(Status.PASS, MarkupHelper.createLabel(steps, ExtentColor.BLUE));
		}
	}


}
