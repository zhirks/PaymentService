package com.payment.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.payment.testbase.TestBase;

public class LoginPage extends TestBase {

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='userIdentifier']")
	WebElement emailPhoneField;

	@FindBy(xpath = "//button[@type='submit' and @class='btn btn-primary btn-block btn-rounded text-uppercase']")
	WebElement loginBtn;

	@FindBy(xpath = "//input[@id='userIdentifier']")
	WebElement passwdField;

	@FindBy(xpath = "//div[@class='alert alert-danger']/div")
	WebElement alertField;

	public void loginNoValue() {
		loginBtn.click();
	}

	/*
	 * public void login(String emailPhoneValue) {
	 * emailPhoneField.sendKeys(emailPhoneValue); loginBtn.click(); }
	 */

	public LoginPage_PasswordField login(String emailPhoneValue) {
		emailPhoneField.sendKeys(emailPhoneValue);
		loginBtn.click();
		return new LoginPage_PasswordField();
	}

	public String getLoginError() {
		return alertField.getText();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

}
