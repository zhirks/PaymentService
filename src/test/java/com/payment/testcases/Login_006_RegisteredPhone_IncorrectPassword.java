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
import com.payment.pages.LoginPage_PasswordField;
import com.payment.testbase.TestBase;
import com.payment.util.TestListener;

@Listeners(TestListener.class)
public class Login_006_RegisteredPhone_IncorrectPassword extends TestBase {

	public Login_006_RegisteredPhone_IncorrectPassword() {
		super();
	}

	LoginPage loginPage;
	LoginPage_PasswordField loginPagePassword;

	@BeforeClass
	@Parameters({ "browser", "version", "platform" })
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
	public void loginTest() {
		// Creating instance for reporting / logging
		logger = report.createTest("Login to CRM");

		// Fetch test data for Registered Phone and assert if the email / phone
		// displayed is correct
		String registeredPhone = data.getStringData("Login", 6, 0);
		logger.info("Entering user credentials..");
		loginPage.login(registeredPhone);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='row user-identifier-row']/div/strong")));
		Assert.assertEquals(loginPagePassword.getActualEmailPhoneUsed(), registeredPhone);
		logger.info("Successfully entered a registered email / phone!");

		// Fetch test data for incorrect password and assert if error displayed is
		// correct
		loginPagePassword.enterIncorrectPassword(data.getStringData("Login", 6, 1));
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']/div/span")));
		Assert.assertEquals(loginPagePassword.getIncorrectPasswordError(), "Incorrect password. Please try again.");
		logger.info("Incorrect password entered!");
	}

	@AfterMethod
	public void exitBrowser() throws InterruptedException {
		report.flush();
		driver.quit();
	}

}
