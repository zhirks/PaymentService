package com.payment.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;

import com.payment.testbase.TestBase;

public class LoginPage_PasswordField extends TestBase {

	public LoginPage_PasswordField() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='password']")
	WebElement passwdField;

	@FindBy(xpath = "//button[contains(text(),'Log in')]")
	WebElement loginBtn;

	@FindBy(xpath = "//div[@class='row user-identifier-row']/div/strong")
	WebElement emailPhoneUsedField;

	@FindBy(xpath = "//div[@class='alert alert-danger']/div/span")
	WebElement incorrectPasswordError;

	public String getActualEmailPhoneUsed() {
		return emailPhoneUsedField.getText();
	}

	public void enterIncorrectPassword(String passwd) {
		passwdField.sendKeys(passwd);
		loginBtn.click();

	}

	public String getIncorrectPasswordError() {
		return incorrectPasswordError.getText();
	}
	
	public AccountOverviewPage enterCorrectPassword(String passwd) {
		passwdField.sendKeys(passwd);
		loginBtn.click();
		return new AccountOverviewPage();
	}


}
