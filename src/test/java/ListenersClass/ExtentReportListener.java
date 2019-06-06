package ListenersClass;

import java.io.File;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener implements ITestListener, ISuiteListener, IInvokedMethodListener{

	ExtentHtmlReporter e1;
	ExtentReports e2;
	ExtentTest e3;
	File file;
	WebDriver driver;
	
	String extentReportPath=System.getProperty("user.dir")+"/ExtentReport/"+ this.getClass().getSimpleName() + new Date().getTime() +".html";
	
	 private static String getTestMethodName(ITestResult iTestResult) {
	        return iTestResult.getMethod().getConstructorOrMethod().getName();
	    }
	 
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
		e3=e2.createTest(this.getClass().getSimpleName() + " : : "+ method.getTestMethod());
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		
		e3.log(Status.INFO, testResult.getMethod().getMethodName() + "module has been completed.");
	}

	public void onStart(ISuite suite) {
		file=new File(extentReportPath);
		e1=new ExtentHtmlReporter(file);
		e2=new ExtentReports();
		e2.attachReporter(e1);	
		
		e2.setSystemInfo("Tester Name", "Dheeraj Pratap Singh");
		e2.setSystemInfo("UserName", "dheeraj.singh@google.com");
		e2.setSystemInfo("Environment", "Production");
		e2.setSystemInfo("Sprint Name", "Flash");
		e2.setSystemInfo("Version Number", "ASHSJ1324_234");
		e2.setSystemInfo("Host", "127.0.0.1");

		e1.config().setDocumentTitle("This is automation testing module");
		e1.config().setReportName("Extent Report generator testing demo.");
		e1.config().setTheme(Theme.DARK);
	}

	public void onFinish(ISuite suite) {		
		e2.flush();
	}

	public void onTestStart(ITestResult result) {
		
		e3.log(Status.INFO, result.getMethod().getMethodName() + "module has been started.");
	}

	public void onTestSuccess(ITestResult result) {
		
		if (result.getStatus() == ITestResult.SUCCESS) {
			e3.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.GREEN));
			e3.log(Status.PASS, result.getMethod().getMethodName() + " test passed");
		}
	}

	public void onTestFailure(ITestResult result) {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			e3.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.RED));
			e3.log(Status.FAIL, result.getMethod().getMethodName() + " test failed");
			e3.log(Status.FAIL, result.getThrowable().getCause());
			e3.log(Status.FAIL, result.getThrowable().getMessage());
		}
	}

	public void onTestSkipped(ITestResult result) {
		
		if (result.getStatus() == ITestResult.SKIP) {
			e3.log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.YELLOW));
			e3.log(Status.SKIP, result.getMethod().getMethodName() + " test skipped");
			e3.log(Status.SKIP, result.getThrowable());
		}		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		if (result.getStatus() == ITestResult.SUCCESS_PERCENTAGE_FAILURE) {
			e3.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.ORANGE));
			e3.log(Status.FAIL, result.getThrowable());
			e3.log(Status.FAIL, result.getMethod().getMethodName()
					+ " module passed but with on Test Failed But Within Success Percentage.");
			 System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(result));
		}		
	}
	
	public void onStart(ITestContext context) {
		
		 System.out.println("I am in onStart method " + context.getName());
		 context.setAttribute("WebDriver", this.driver);		
	}

	public void onFinish(ITestContext context) {
		
		System.out.println("I am in onFinish method");
		
	}

}
