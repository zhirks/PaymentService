package com.payment.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.payment.util.ExcelDataProvider;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public ExcelDataProvider data;
	public ExtentReports report;
	public WebDriverWait wait;
	public ExtentTest logger;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\RATutor\\eclipse-workspace\\PaymentService\\PaymentProject\\src\\main\\java\\com\\payment\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\RATutor\\eclipse-workspace\\PaymentService\\PaymentProject\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver, 20);

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\RATutor\\eclipse-workspace\\PaymentService\\PaymentProject\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			wait = new WebDriverWait(driver, 20);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
		data = new ExcelDataProvider();
		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/FreeCRM_" + getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);

	}
	
	 public void takeScreenShot(String methodName) {
		 
		 //String filePath = "C:\\Users\\RATutor\\eclipse-workspace\\PaymentService\\PaymentProject\\Screenshots";
    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with test method name 
            try {
				//FileUtils.copyFile(scrFile, new File("C:\\Users\\RATutor\\eclipse-workspace\\PaymentService\\PaymentProject\\Screenshots"+methodName+"_"+getCurrentDateTime()+".png"));
				FileUtils.copyFile(scrFile, new File("C:\\Users\\RATutor\\eclipse-workspace\\PaymentService\\PaymentProject\\Screenshots\\"+"Failed_"+methodName+"_"+getCurrentDateTime()+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
    
    public String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}
    
}