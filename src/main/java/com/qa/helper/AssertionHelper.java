package com.qa.helper;

import org.testng.Assert;

import com.qa.base.TestBase;

public class AssertionHelper {
	public static void verifyText(String s1,String s2,String msg)
	{
		if(s1.equalsIgnoreCase(s2))
		{
			TestBase.logExtentReport(msg+"ACTUAL"+s1+"EXPECTED"+s2, "info");
		}else
		{
			TestBase.logExtentReport(msg+"ACTUAL"+s1+"EXPECTED"+s2, "fail");
			Assert.assertTrue(false);
		}
	}
	public static void verifyPartialText(String completeText,String partialText,String msg)
	{
		if(completeText.equalsIgnoreCase(partialText))
		{
			TestBase.logExtentReport(msg+"COMPLETE"+completeText+"PARTIAL"+partialText, "info");
		}else
		{
			TestBase.logExtentReport(msg+"COMPLETE"+completeText+"PARTIAL"+partialText, "fail");
			Assert.assertTrue(false);
		}
	}
	public static void verifyNumber(String s1,String s2,String msg)
	{
		if(s1.equalsIgnoreCase(s2))
		{
			TestBase.logExtentReport(msg+"ACTUAL"+s1+"EXPECTED"+s2, "info");
		}else
		{
			TestBase.logExtentReport(msg+"ACTUAL"+s1+"EXPECTED"+s2, "fail");
			Assert.assertTrue(false);
		}
	}
	public static void verifyBoolean(String s1,String s2,String msg)
	{
		if(s1.equalsIgnoreCase(s2))
		{
			TestBase.logExtentReport(msg+"ACTUAL"+s1+"EXPECTED"+s2, "info");
		}else
		{
			TestBase.logExtentReport(msg+"ACTUAL"+s1+"EXPECTED"+s2, "fail");
			Assert.assertTrue(false);
		}
	}

}
