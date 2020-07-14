package com.payment.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.payment.pages.AccountOverviewPage;
import com.payment.pages.LoginPage;
import com.payment.pages.LoginPage_PasswordField;
import com.payment.testbase.TestBase;
import com.payment.util.TestListener;

@Listeners(TestListener.class)
public class Login_007_RegisteredPhone_CorrectPassword extends TestBase {

	public Login_007_RegisteredPhone_CorrectPassword() {
		super();
	}

	LoginPage loginPage;
	LoginPage_PasswordField loginPagePassword;
	AccountOverviewPage accountOverview;

	@BeforeMethod
	public void setup() {
		initialization();

		loginPage = new LoginPage();
		loginPagePassword = new LoginPage_PasswordField();
		accountOverview = new AccountOverviewPage();
	}

	@Test
	public void verifyRegisteredPhone_CorrectPassword() {
		//Creating instance for reporting / logging
		logger = report.createTest("Login to CRM");
		
		//Fetch test data for Registered Phone and assert if the email / phone displayed is correct
		String registeredPhone = data.getStringData("Login", 7, 0);
		logger.info("Entering user credentials..");
		loginPage.login(registeredPhone);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row user-identifier-row']/div/strong")));
		Assert.assertEquals(loginPagePassword.getActualEmailPhoneUsed(), registeredPhone);

		//Fetch test data for correct password and assert if Account overview is displayed after login
		loginPagePassword.enterIncorrectPassword(data.getStringData("Login", 7, 1));
		Assert.assertEquals(accountOverview.getAccountOverviewPageTitle(), "Account Overview");
		logger.info("Successfully signed-in!");
		accountOverview.logout();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'LOG IN')]")));
		Assert.assertEquals(loginPage.getPageTitle(), "Log in to your account - Paysera");
		logger.info("Successfully signed-out!");
	}

	@AfterMethod
	public void exitBrowser() throws InterruptedException {
		report.flush();
		driver.quit();
	}


}
