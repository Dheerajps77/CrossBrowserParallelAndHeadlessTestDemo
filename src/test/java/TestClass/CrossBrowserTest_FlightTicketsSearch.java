package TestClass;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;


import TestPage.FlightTicketsSearchPage;
import Utilities.EnvironmentPropertiesReaderForLogMessage;
import Utilities.Log;

public class CrossBrowserTest_FlightTicketsSearch extends TestBase {
	
	FlightTicketsSearchPage objFlightTicketsSearchPage;
	EnvironmentPropertiesReaderForLogMessage objEnvironmentPropertiesReader;
	Properties properties;
	@Test
	public void SearchTickets()
	{
		
		Log.StartLog("SearchTickets test has been started");
		objFlightTicketsSearchPage=new FlightTicketsSearchPage(driver);		
		try {
			objEnvironmentPropertiesReader=EnvironmentPropertiesReaderForLogMessage.getInstance();
			properties=objEnvironmentPropertiesReader.PropertiesFile();
			
			objFlightTicketsSearchPage.EnterCityDetailsForFromAirport();
			objFlightTicketsSearchPage.EnterDates();	
			boolean bool=objFlightTicketsSearchPage.ClickOnSearchButton();			
			if(bool)
			{
				Assert.assertTrue(true, properties.getProperty("TestPassed"));
				Log.Info(properties.getProperty("TestPassed"));
			}
			else {
				Log.Info(properties.getProperty("TestFailed"));
				Assert.fail(properties.getProperty("TestFailed"));				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.Error(properties.getProperty("TestFailed"), e);
		}
		
		Log.StartLog("SearchTickets test has been completed");
	}

}
