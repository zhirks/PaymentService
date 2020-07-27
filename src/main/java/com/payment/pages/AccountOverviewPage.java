package com.payment.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.payment.testbase.TestBase;

public class AccountOverviewPage extends TestBase {

	public AccountOverviewPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a.open-close.waves-effect.waves-light")
	WebElement leftNavMenu;
	
	@FindBy(css = "a.waves-effect.waves-light>span.hide-menu")
	WebElement logoutLink;

	@FindBy(xpath = "//h1[@class='page-title' and contains(text(),'Account Overview')]")
	WebElement accountOverviewPageTitle;

	Actions action = new Actions(driver);
	
	public String getAccountOverviewPageTitle() {
		return accountOverviewPageTitle.getText();
	}

	public void clickLeftNav() {

		action.moveToElement(leftNavMenu).build().perform();
		leftNavMenu.click();
		// return new LoginPage();
	}
	
	public LoginPage logout() {
		action.moveToElement(logoutLink).build().perform();
		logoutLink.click();
		return new LoginPage();
	}


}
