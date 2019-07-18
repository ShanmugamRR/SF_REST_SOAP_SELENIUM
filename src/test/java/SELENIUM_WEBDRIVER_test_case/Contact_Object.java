package SELENIUM_WEBDRIVER_test_case;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import REST_HTTP.API_Contact_Inputs;
import REST_HTTP.API_Methods;
import REST_HTTP.API_Utilities;
import REST_HTTP.API_Variables;
import SELENIUM_WEBDRIVER.WebDriver_Utilities;
import SELENIUM_WEBDRIVER.WebDriver_Varibales;

public class Contact_Object implements WebDriver_Varibales,API_Variables
{
	WebDriver driver;
	WebDriverWait Ewait;
	JavascriptExecutor jsex;
	static String callBackURL = "https://ap15.lightning.force.com/lightning/setup/SetupOneHome/home";

	WebDriver_Utilities WDU = new WebDriver_Utilities();
	//API Utilities
	API_Methods methods = new API_Methods();
	API_Contact_Inputs inputs = new API_Contact_Inputs();
	API_Utilities HTTP = new API_Utilities();

	@BeforeTest
	public void openBrowser() throws IOException
	{
		inputs.baseUrl = methods.getBaseURL();
		inputs.ContactId = methods.getID(inputs.baseUrl, query);
		
		if(!(inputs.ContactId.equals("No ID")))
		{
			int statuscode = 204;
			String url = inputs.baseUrl + "/sobjects/Contact/" + inputs.ContactId;
			System.out.println(url);
			HttpDelete httpDelete = HTTP.getHttpDelete(url);
			HttpResponse response = HTTP.getResponseDelete(httpDelete);
			System.out.println("Status code: "+HTTP.getStatusCode(response,statuscode));
			if(HTTP.getStatusCode(response, statuscode) == statuscode)
			{
				System.out.println("Deleted Successfully.....");
			}
			else  {
				System.out.println("\nError......\n");
			}
		}
		
		DesiredCapabilities capabilities =  DesiredCapabilities.chrome();
		capabilities.setCapability("username", SL_USERNAME);
		capabilities.setCapability("accessKey", SL_ACCESS_KEY);
		capabilities.setCapability("platform", "Windows 10");
		capabilities.setCapability("version", "64.0");
		driver = new RemoteWebDriver(new URL(SL_URL), capabilities);
		
		driver.get(SF_LOGINURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//WebDriver driver_1 =	WDU.getLogin(driver);
		WDU.getLogin(driver);
		WebDriver_Utilities.waitForPageToLoad(5000);
		System.out.println(driver.getCurrentUrl());
		WDU.explicitWait(driver).until(ExpectedConditions.presenceOfElementLocated(WebDriver_Utilities.appLaunch));
		WebDriver_Utilities.clickAppLauncherIcon(driver);
		driver.findElement(By.xpath(SalesApp)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
		WebDriver_Utilities.waitForPageToLoad(5000);
	}

	@Test(enabled = false)
	public void Contact_Object()
	{
		//Click on Contact Object
		WebElement contactObj = driver.findElement(By.xpath(contactObject));
		jsex = WebDriver_Utilities.getJSE(driver);
		jsex.executeScript("arguments[0].click();", contactObj);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriver_Utilities.waitForPageToLoad(3000);
	}

	@Test
	//(enabled = true)
	(priority =1)
	public void Create_Contact()
	{
		Contact_Object();
		System.out.println("Creating Contact.......");
		driver.findElement(By.xpath(NewButton)).click();
		WebDriver_Utilities.waitForPageToLoad(1000);
		//Create a Contact
		driver.findElement(By.xpath(FNameXpath)).sendKeys(FirstName);
		driver.findElement(By.xpath(LNameXpath)).sendKeys(LastName);
		driver.findElement(By.xpath(ANameXpath)).sendKeys(AccountName);
		driver.findElement(By.xpath(ANameXpathSelect)).click();
		driver.findElement(By.xpath(EmailXpath)).sendKeys(Email);
		driver.findElement(By.xpath(SaveButton)).click();
		System.out.println("Created Successfully.......");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriver_Utilities.waitForPageToLoad(3000);
	}

	@Test
	//(enabled = true)
	(priority =2)
	public void Update_Contact()
	{
		Contact_Object();
		System.out.println("Updating Contact.......");
		WebElement Name = driver.findElement(By.xpath(NameList));
		WDU.explicitWait(driver).until(ExpectedConditions.visibilityOf(Name));
		Name.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(DownButton)).click();
		driver.findElement(By.xpath(EditButton)).click();
		WebDriver_Utilities.waitForPageToLoad(2000);
		driver.findElement(By.xpath(PhoneXpath)).sendKeys(PhoneNumber);
		driver.findElement(By.xpath(MobileXpath)).sendKeys(MobileNumber);
		driver.findElement(By.xpath(SaveButton)).click();
		System.out.println("Updated Successfully.......");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriver_Utilities.waitForPageToLoad(3000);
	}

	@Test
	//(enabled = false)
	(priority =3)
	public void Delete_Contact()
	{
		Contact_Object();
		System.out.println("Deleting Contact.......");
		WebElement Name = driver.findElement(By.xpath(NameList));
		WDU.explicitWait(driver).until(ExpectedConditions.visibilityOf(Name));
		Name.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(DownButton)).click();
		driver.findElement(By.xpath(DeleteButton)).click();
		driver.findElement(By.xpath(DeleteConfirm)).click();
		System.out.println("Deleted Successfully........");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriver_Utilities.waitForPageToLoad(3000);
	}
	
	@AfterTest
	public void afterTest()
	{
		driver.quit();
	}
}
