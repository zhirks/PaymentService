package com.payment.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.payment.testbase.TestBase;

public class TestListener extends TestBase implements ITestListener {
	
    @Override
    public void onTestFailure(ITestResult result) {
    	System.out.println("Test has failed");
    	takeScreenShot(result.getMethod().getMethodName());
    }
    
   
	public void onFinish(ITestContext context) {}
  
    public void onTestStart(ITestResult result) {   }
  
    public void onTestSuccess(ITestResult result) {   }

    public void onTestSkipped(ITestResult result) {   }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {   }
} 