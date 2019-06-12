package TestClass;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utilities.CaptureScreenshot;
import Utilities.Log;

public class TestBase {

	String url = "https://www.tripiflights.com/";
	String extentReportPath = System.getProperty("user.dir") + "/ExtentReports/" + this.getClass().getSimpleName()
			+ ".html";
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	int enterNumberOfMonth = 3;
	Logger log;
	File file;
	FileInputStream fis;
	ExtentHtmlReporter e1;
	ExtentReports e2;
	ExtentTest e3;

	@BeforeSuite
	public void InitializingExtenReports() {
		file = new File(extentReportPath);
		e1 = new ExtentHtmlReporter(file);
		e2 = new ExtentReports();
		e2.attachReporter(e1);

		e2.setSystemInfo("User Name", "DheerajPratapSingh@gmail.com");
		e2.setSystemInfo("Tester Name", "Dheeraj Pratap Singh");
		e2.setSystemInfo("Enviroment", "QA");
		e2.setSystemInfo("Sprint Version", "V.23123");
		e2.setSystemInfo("Sprint Name", "Auqaman");
		e2.setSystemInfo("Host Name", "KJKLEKWLKDJ23123");
		e2.setSystemInfo("Host Number", "127.0.0.1");

		e1.config().setDocumentTitle(this.getClass().getSimpleName() + " Secenario Test for the TripFlights");
		e1.config().setReportName(this.getClass().getSimpleName());
		e1.config().setTheme(Theme.DARK);

		try {

		} catch (Exception e) {
			e.printStackTrace();
			Log.Error("Exception occurs !!!", e);
		}
	}

	@AfterSuite
	public void FlushingExtentReports() {
		try {
			e2.flush();
		} catch (Exception e) {
			e.printStackTrace();
			Log.Error("Exception occurs !!!", e);
		}
	}

	@BeforeTest
	@Parameters({ "browser","HeadLessYes"})
	public void OpenBrowser(String browser, String HeadLessYes) {
		String chromePath = "";
		String ffPath = "";
		String iePath = "";
		try {
			log = Logger.getLogger(this.getClass().getSimpleName());
			PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/java/Config/log4j.properties");

			if (browser.equalsIgnoreCase("chrome")) {	
				
				Log.Info("Invoked chrome browser");
				chromePath = System.getProperty("user.dir") + "/Drivers/chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", chromePath);
				
				if(HeadLessYes.equalsIgnoreCase("yes"))
				{
					Log.Info("Headless mode is invoked");
					ChromeOptions chromeOptions=new ChromeOptions();
					chromeOptions.addArguments("window-size=1400,600");
					chromeOptions.addArguments("headless");
					driver=new ChromeDriver(chromeOptions);					
				}
				else {					
					driver = new ChromeDriver();
					driver.manage().window().maximize();
				}	
				
			} else if (browser.equalsIgnoreCase("ff")) {
				
				Log.Info("Invoked firefox browser");				
				ffPath = System.getProperty("user.dir") + "/Drivers/geckodriver.exe";
				
				DesiredCapabilities caps = new DesiredCapabilities();
			    caps.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
				System.setProperty("webdriver.gecko.driver", ffPath);
				
				if(HeadLessYes.equalsIgnoreCase("yes"))
				{
					Log.Info("Headless mode is invoked");
					FirefoxBinary firefoxBinary=new FirefoxBinary();
					//firefoxBinary.addCommandLineOptions("window-size=1400,600");
					firefoxBinary.addCommandLineOptions("--headless");
					
					 
					FirefoxOptions firefoxOptions = new FirefoxOptions();
				    firefoxOptions.setBinary(firefoxBinary);
				    driver = new FirefoxDriver(firefoxOptions);				    
				}
				else {
					driver = new FirefoxDriver(caps);
					driver.manage().window().maximize();
				}					
			}

			else if (browser.equalsIgnoreCase("ie")) {
				
				Log.Info("Invoked internet explrore browser");
				iePath = System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe";
				
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);								
				System.setProperty("webdriver.ie.driver", iePath);								
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().window().maximize();
			}			
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);			
			driver.get(url);			
			Log.Info("Invoked url " + url);
		} catch (Exception e) {
			e.printStackTrace();
			Log.Error("Exception occurs !!!", e);
		}
	}

	@AfterTest
	public void TearDownApplication() {
		try {
			Log.Info("Closing browser session");
			if (driver != null) {
				driver.quit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.Error("Exception occures : ", e);
		}
	}

	@AfterMethod
	public void GetReportResult(ITestResult result) {
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test module started");
				e3.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.GREEN));
				e3.log(Status.PASS, result.getMethod().getMethodName() + " test passed");
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test module completed");
			}

			if (result.getStatus() == ITestResult.FAILURE) {
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test module started");
				e3.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.RED));
				e3.log(Status.FAIL, result.getMethod().getMethodName() + " test failed");
				e3.log(Status.FAIL, result.getThrowable());
				String screenshotCapturedString = CaptureScreenshot.ScreenshotCapture(driver,
						result.getMethod().getMethodName());
				e3.addScreenCaptureFromPath(screenshotCapturedString);
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test module completed");
				e3.log(Status.FAIL, result.getThrowable().getCause());
				e3.log(Status.FAIL, result.getThrowable().getMessage());
			}

			if (result.getStatus() == ITestResult.SKIP) {
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test module started");
				e3.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.YELLOW));
				e3.log(Status.SKIP, result.getMethod().getMethodName() + " test skipped");
				e3.log(Status.SKIP, result.getThrowable());
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test module completed");
			}

			if (result.getStatus() == ITestResult.SUCCESS_PERCENTAGE_FAILURE) {
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test module started");
				e3.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.ORANGE));
				e3.log(Status.FAIL, result.getThrowable());
				e3.log(Status.FAIL, result.getMethod().getMethodName()
						+ " module passed but with on Test Failed But Within Success Percentage.");
				e3.log(Status.INFO, result.getMethod().getMethodName() + " test module completed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.Error("Exception occurs !!!", e);
		}
	}

	@BeforeMethod
	public void CreateReport(Method method) {
		try {
			e3 = e2.createTest(this.getClass().getSimpleName() + " : : test case is -> " + method.getName());
		} catch (Exception e) {
			e.printStackTrace();
			Log.Error("Exception occurs !!!", e);
		}
	}
}
