package TestPage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NoSuchElementInPage {

	WebDriver driver;
	String chromePath;
	Actions action;

	@BeforeTest
	public void InitiateBrowser() {

		try {

			chromePath = System.getProperty("user.dir") + "/Drivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromePath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
			driver.get("https://www.magicbricks.com/");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(enabled = false)
	public void NoSuchElementTest1() {
		boolean flag = false;
		try {
			flag = driver.findElement(By.xpath("//input[@id='buy_budget_lb']")).isDisplayed();
			if (flag) {
				flag = true;
				driver.findElement(By.xpath("//input[@id='buy_budget_lb']")).click();
			} else {
				System.out.println("No such Element present in the page....");
			}

		} catch (Exception e) {
			System.out.println("No such Element present in the page....");
			e.printStackTrace();
		}
	}

	public ArrayList<String> getDropDownStringValues() {

		ArrayList<String> valueArrayList = null;
		try {
			valueArrayList = new ArrayList<String>();
			List<WebElement> dropdownValuesElement = driver.findElements(By.xpath("//div[@id='keyword_suggest']//div"));
			int totalDropDownValues = dropdownValuesElement.size();
			for (int i = 1; i < totalDropDownValues; i++) {
				WebElement element = driver.findElement(By.xpath("//div[@id='keyword_suggest']//div[" + i + "]"));
				String dropdownValue = element.getText();
				valueArrayList.add(dropdownValue);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueArrayList;
	}

	@Test
	public void StaleElementReferenceExceptionTest()
	{
		WebElement textBox1;	
		String value="Delhi";
		String valueToBeCompared="New Delhi-Dwarka";
		try {
			
			textBox1=driver.findElement(By.xpath("//input[@name='keyword']"));
			//textBox1.sendKeys("textBox1");
			action=new Actions(driver);
			action.sendKeys(textBox1, value).build().perform();			
			ArrayList<String> string=getDropDownStringValues();			
			Iterator<String> iterateIterator=string.iterator();			
			while(iterateIterator.hasNext())
			{
				String value1=iterateIterator.next();				
				if(value1.equals(valueToBeCompared))
				{
					System.out.println("Test case has been passed with expected value to be clicked on " + value1);					
				}
			}			
			List<WebElement> dropdownValuesElement=driver.findElements(By.xpath("//div[@id='keyword_suggest']//div"));
			int totalDropDownValues=dropdownValuesElement.size();
			System.out.println(totalDropDownValues);
			
			for(int i=1;i<totalDropDownValues;i++)
			{
				WebElement element=driver.findElement(By.xpath("//div[@id='keyword_suggest']//div["+i+"]"));
				String dropdownValue=element.getText();	
				
				if(dropdownValue.contains("New Delhi-Dwarka"))
				{
					element.click();
					break;
				}
			}			
			driver.navigate().refresh();
			Thread.sleep(3000);
			//action.sendKeys(textBox1, "Sangam Vihar").build().perform();			
			WebDriverWait wait=new WebDriverWait(driver, 40);
			boolean d=wait.until(ExpectedConditions.stalenessOf(textBox1)).booleanValue();
			if(d)
			{
				driver.findElement(By.xpath("//input[@name='keyword']")).sendKeys("Sangam Vihar");
			}
			else {
				System.out.println("Occured stale reference exception");
			}
			WebElement element=null;
			Select selctSelect=new Select(element);
			selctSelect.deselectAll();
			
			Actions actions=new Actions(driver); //The user-facing API for emulating 
			//complex user gestures. Use this class rather than using theKeyboard or Mouse directly. 

			Action actionaAction; //Interface representing a single user-interaction action.
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
