package TestClass;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;


import TestPage.FlightTicketsSearchPage;
import Utilities.EnvironmentPropertiesReaderForLogMessage;
import Utilities.Log;

public class CrossBrowserTest_FlightTicketsSearch extends TestBase {
	
	FlightTicketsSearchPage objFlightTicketsSearchPage;
	EnvironmentPropertiesReaderForLogMessage objEnvironmentPropertiesReader;
	Properties properties;
	
	@Test(priority=0, description="Verifying the searchTickets functionality")
	/*
	 * @Severity(SeverityLevel.CRITICAL)
	 * 
	 * @Description("Test case description : Verifying the searchTickets functionality"
	 * )
	 * 
	 * @Story("Search Tickets = To check if search tickets funcation working or not"
	 * )
	 */
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
	
	@Test(priority=1, description="Verifying the sign up link functionality on login page")
	/*
	 * @Severity(SeverityLevel.BLOCKER)
	 * 
	 * @Description("Test case description : Verifying the sign up link functionality on login page"
	 * )
	 * 
	 * @Story("Search Tickets = To check if sign up funcation working or not on login page"
	 * )
	 */
	public void VerifySignUpLinkTest()
	{
		try {
			
			//Assert.assertTrue(false, "failed due to sign up link button vissiblity");
			
			Assert.assertEquals("failed due to sign up link button vissiblity", "failed due to sign up link button vissiblity");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
