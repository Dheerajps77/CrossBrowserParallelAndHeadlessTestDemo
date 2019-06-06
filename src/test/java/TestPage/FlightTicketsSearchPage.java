package TestPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Log;
import sun.util.logging.resources.logging;

public class FlightTicketsSearchPage {

	WebDriver driver;
	WebDriverWait wait;
	Actions action;

	public FlightTicketsSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//label[@for='from-airport']//following::div[1]//input")
	public WebElement fromAirPortTextField;

	@FindBy(how = How.XPATH, using = "//label[@for='from-airport']//following::div[2]//input")
	public WebElement toAirPortTextField;

	@FindBy(how = How.XPATH, using = "//ul[@id='ui-id-2']")
	public WebElement listOfCityFromAirportAppeared;

	@FindBy(how = How.XPATH, using = "//ul[@id='ui-id-3']")
	public WebElement listOfCityToAirportAppeared;

	@FindBy(how = How.XPATH, using = "//input[@id='txtDepartingDate']")
	public WebElement departingDateTextField;

	@FindBy(how = How.XPATH, using = "//input[@id='txtReturnDate']")
	public WebElement returnDateTextField;
	
	@FindBy(how = How.XPATH, using = "//button[@id='btn_search']")
	public WebElement btn_search;
	
	@FindBy(how = How.XPATH, using = "//div[@class='search__NotFound']//strong")
	public WebElement searchedResult;

	public void EnterCityDetailsForFromAirport() {
		try {						
			Log.Info("FROM --> Entered the City name : DEL");
			fromAirPortTextField.sendKeys("DEL");
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(listOfCityFromAirportAppeared));
			Log.Info("Selected the City name from the suggested dropped down list");
			driver.findElement(By.xpath("//ul[@id='ui-id-2']//li[3]")).click();

			Log.Info("TO --> Entered the City name : M");
			toAirPortTextField.sendKeys("M");
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(listOfCityToAirportAppeared));
			Log.Info("Selected the City name from the suggested dropped down list");
			driver.findElement(By.xpath("//ul[@id='ui-id-3']//li[3]")).click();			

		} catch (Exception e) {
			e.printStackTrace();
			Log.Error("Exception occurs !!! ", e);
		}
	}

	public void EnterDates() {
		try {

			int enterNumberOfMonth = 3;
			wait = new WebDriverWait(driver, 20);			
			wait.until(ExpectedConditions.elementToBeClickable(departingDateTextField));			
			departingDateTextField.click();

			// Handle the staleElement reference exception using below loop
			Log.Info("Handle the staleElement reference exception using below loop and click on next arrow button upto 3 time from the current month for departure date");
			for (int i = 0; i < enterNumberOfMonth; i++) {
				action = new Actions(driver);
				action.moveToElement(driver.findElement(By.xpath("//a[@title='Next']"))).build().perform();
				driver.findElement(By.xpath("//a[@title='Next']")).click();
			}

			// Select departure date
			String selectDepartDate = "18";
			Log.Info("select departure date " + selectDepartDate);
			List<WebElement> departDay = driver.findElements(By.xpath(
					"//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//tr//td[@data-handler='selectDay']//a"));
			int totalNumberOfDepartureDay = departDay.size();
			for (int i = 0; i < totalNumberOfDepartureDay; i++) {
				WebElement element = departDay.get(i);
				String departureDate = element.getText();

				if (selectDepartDate.equals(departureDate)) {
					Log.Info("Click on selected depature date : " + departureDate);
					element.click();
					break;
				}
			}

			// Handling satelElement reference using below loop
			Log.Info("Handle the staleElement reference exception using below loop and click on next arrow button upto 3 time from the current month for return date");
			for (int i = 0; i < enterNumberOfMonth; i++) {
				driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']"));
			}

			// Select return day
			String selectReturnDate = "7";
			Log.Info("select return date " + selectReturnDate);
			List<WebElement> returnDay = driver.findElements(By.xpath(
					"//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//tr//td[@data-handler='selectDay']//a"));
			int totalNumberOfReturnDay = returnDay.size();

			for (int i = 0; i < totalNumberOfReturnDay; i++) {
				WebElement element = returnDay.get(i);
				String returnDate = element.getText();

				if (selectReturnDate.equals(returnDate)) {
					Log.Info("Click on selected return date : " + returnDate);
					element.click();
					break;
				}
			}			

		} catch (Exception e) {
			e.printStackTrace();
			Log.Error("Exception occurs !!!", e);
		}
	}
	
	public boolean ClickOnSearchButton()
	{
		boolean flag=false;
		try {						
			wait = new WebDriverWait(driver, 30);
			Log.Info("Clicked on search button");
			wait.until(ExpectedConditions.elementToBeClickable(btn_search));
			btn_search.click();
			
			String expectedSearchedResult=searchedResult.getText();
			String actualSearchedResult="Sorry! We have gearched over 500+ airlines and unable to find flights as per your selected itinerary.".trim();						
			if (actualSearchedResult.equalsIgnoreCase(expectedSearchedResult)) {				
				flag=true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			Log.Error("Exception occurs !!! ", e);
		}		
		return flag;
	}

}
