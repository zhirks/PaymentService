package com.payment.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.payment.testbase.TestBase;

public class AccountOverviewPage extends TestBase {

	public AccountOverviewPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='visible-lg-inline-block' and contains(text(),'Log out')]")
	WebElement logoutLink;
	
	@FindBy(xpath = "//h1[@class='page-title' and contains(text(),'Account Overview')]")
	WebElement accountOverviewPageTitle;
	

	public String getAccountOverviewPageTitle() {
		return accountOverviewPageTitle.getText();
	}

	public LoginPage logout() {
		logoutLink.click();
		return new LoginPage();
	}

}
