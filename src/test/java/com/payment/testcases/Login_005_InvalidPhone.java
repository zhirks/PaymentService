package com.payment.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.payment.pages.LoginPage;
import com.payment.testbase.TestBase;
import com.payment.util.TestListener;

@Listeners(TestListener.class)
public class Login_005_InvalidPhone extends TestBase {

	public Login_005_InvalidPhone() {
		super();
	}

	LoginPage loginPage;

	@BeforeMethod
	public void setup() {
		initialization();

		loginPage = new LoginPage();
	}

	@Test
	public void verifyInvalidPhone() {
		//Creating instance for reporting / logging
		logger = report.createTest("Login to CRM");

		//Fetch test data for Registered Phone and assert if the email / phone displayed is correct
		String invalidEmail1 = data.getStringData("Login", 5, 0);
		logger.info("Entering user credentials..");
		loginPage.login(invalidEmail1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']/div")));
		Assert.assertEquals(loginPage.getLoginError(), "The specified user could not be found");
		logger.info("Account not yet registered!");
	}

	@AfterMethod
	public void exitBrowser() throws InterruptedException {
		report.flush();
		driver.quit();
	}

}