package TestClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DateClick extends TestBase {

	@Test
	public void ClickOnDate() {
		try {

			// Web elements of UI
			WebElement txtDepartingDate = driver.findElement(By.xpath("//input[@id='txtDepartingDate']"));
			WebElement txtReturnDate = driver.findElement(By.xpath("//input[@id='txtReturnDate']"));
			// WebElement nextDate = driver.findElement(By.xpath("//a[@title='Next']"));

			// Wait for the element to be click for 20 seconds and will click on Dapart date
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(txtDepartingDate));
			log.info("Wait for the element to be click for 20 seconds and click on Dapart date");
			txtDepartingDate.click();					

			// Handle the staleElement reference exception using below loop
			log.info("Handle the staleElement reference exception using below loop and click on next arrow button upto 3 time from the current month for departure date");
			for (int i = 0; i < enterNumberOfMonth; i++) {
				action = new Actions(driver);
				action.moveToElement(driver.findElement(By.xpath("//a[@title='Next']"))).build().perform();
				driver.findElement(By.xpath("//a[@title='Next']")).click();
			}

			// Select departure date			
			String selectDepartDate = "18";
			log.info("select departure date "+ selectDepartDate);
			List<WebElement> departDay = driver.findElements(By.xpath(
					"//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//tr//td[@data-handler='selectDay']//a"));
			int totalNumberOfDepartureDay = departDay.size();
			for (int i = 0; i < totalNumberOfDepartureDay; i++) {
				WebElement element = departDay.get(i);
				String departureDate = element.getText();

				if (selectDepartDate.equals(departureDate)) {
					log.info("Click on selected depature date : " + departureDate);
					element.click();
					break;
				}
			}

			// Handling satelElement reference using below loop
			log.info("Handle the staleElement reference exception using below loop and click on next arrow button upto 3 time from the current month for return date");
			for (int i = 0; i < enterNumberOfMonth; i++) {
				driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']"));
			}

			// Select return day
			String selectReturnDate = "7";
			log.info("select return date "+ selectReturnDate);
			List<WebElement> returnDay = driver.findElements(By.xpath(
					"//div[@class='ui-datepicker-group ui-datepicker-group-last']//table//tbody//tr//td[@data-handler='selectDay']//a"));
			int totalNumberOfReturnDay = returnDay.size();

			for (int i = 0; i < totalNumberOfReturnDay; i++) {
				WebElement element = returnDay.get(i);
				String returnDate = element.getText();

				if (selectReturnDate.equals(returnDate)) {
					log.info("Click on selected return date : " + returnDate);
					element.click();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occures : ", e);
			System.out.println(e.getMessage());		
		}
	}
}
