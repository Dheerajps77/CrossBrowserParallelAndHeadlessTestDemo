package Utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	
		
	public static String ScreenshotCapture(WebDriver driver, String screenshotName)
	{
		String screenshotPath="";
		try
		{
			Date date=Calendar.getInstance().getTime();
			DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd' 'HH mm ss");
			 String strDate=dateFormat.format(date);			
			screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+screenshotName + strDate+".png";
			
			TakesScreenshot ts=(TakesScreenshot)driver;
			File imageCapture=ts.getScreenshotAs(OutputType.FILE);
			File imageDestination=new File(screenshotPath);
			FileUtils.copyFile(imageCapture, imageDestination);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		return screenshotPath;
	}

}
