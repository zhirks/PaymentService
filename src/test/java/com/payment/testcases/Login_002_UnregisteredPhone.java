package com.payment.testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.payment.pages.LoginPage;
import com.payment.testbase.TestBase;
import com.payment.util.TestListener;

@Listeners(TestListener.class)
public class Login_002_UnregisteredPhone extends TestBase {

	public Login_002_UnregisteredPhone() {
		super();
	}

	LoginPage loginPage;

	@BeforeClass
	@Parameters({ "browser", "version", "platform"})
	public void setup(String br, String vr, String pf) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", br);
		capabilities.setCapability("version", vr);
		capabilities.setCapability("platform", pf);
		driver = new RemoteWebDriver(new URL(URL), capabilities);
		
		initialization();

		loginPage = new LoginPage();
	}
	
	@Test
	public void verifyUnregisteredPhone() {
		//Creating instance for reporting / logging
		logger = report.createTest("Login to CRM");
		
		//Fetch test data for Registered Phone and assert if the email / phone displayed is correct
		String unregisteredPhone = data.getStringData("Login", 2, 0);
		logger.info("Entering user credentials..");
		loginPage.login(unregisteredPhone);
		wait.until(ExpectedConditions .visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']/div")));
		Assert.assertEquals(loginPage.getLoginError(), "The specified user could not be found");
		logger.info("Account not yet registered!");
	}

	@AfterMethod
	public void exitBrowser() throws InterruptedException {
		report.flush();
		driver.quit();
	}

}
