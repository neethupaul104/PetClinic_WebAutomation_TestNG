package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Base {

	public static WebDriver driver;

	public static Properties prop = new Properties();
	public static FileInputStream file;

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports ext;
	public static ExtentTest test;

	@BeforeSuite
	public void InitialSetup() throws IOException {
		// Driver initialization and Browser launch
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/");
		System.out.println("Browser Launched");

		// properties for constants
		file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\Constants\\Constant.properties");
		prop.load(file);

		// Extent report
		String ReportName = new SimpleDateFormat("MM-dd-yyyy_HH-SSS").format(new GregorianCalendar().getTime());
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "//Extent-Reports/" + "ExtentReports" + ReportName + ".html");
		ext = new ExtentReports();
		ext.attachReporter(htmlReporter);
		//String ExtentReportTitle = "Pet Clinic Test";
		// test = ext.createTest(ExtentReportTitle);

	}

	@AfterMethod
	public void getResults(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail(MarkupHelper.createLabel(result.getName() + "Test Case Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel(result.getName() + "Test Case Passed", ExtentColor.GREEN));
		} else {
			test.skip(MarkupHelper.createLabel(result.getName() + "Test Case Skipped", ExtentColor.YELLOW));
			test.fail(result.getThrowable());
		}
	}

	public static WebDriver driver() {
		return driver;
	}

	@AfterSuite
	public void QuitBrowser() {
		driver.quit();
		ext.flush();
	}

}
