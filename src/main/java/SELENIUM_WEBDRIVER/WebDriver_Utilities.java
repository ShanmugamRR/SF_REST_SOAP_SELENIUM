package SELENIUM_WEBDRIVER;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver_Utilities implements WebDriver_Varibales
{
	static String originalHandle;

	//getLogin Method
	By username = By.cssSelector(USER);
	By password = By.id(PWD);
	By loginBtn = By.cssSelector(Login);
	
	 //AppLauncher Method
	public static By appLaunch = By.xpath(AppLauncher);

	//public  WebDriver getLogin(WebDriver driver)
	public void getLogin(WebDriver driver)
	{
		driver.findElement(username).sendKeys(SF_USERNAME);
		driver.findElement(password).sendKeys(SF_PASSWORD);
		driver.findElement(loginBtn).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//return driver;
	}

	public void getAlerts(WebDriver driver)
	{
		Alert alert = driver.switchTo().alert();
		String alertmsg = driver.switchTo().alert().getText();
		System.out.println(alertmsg);
		alert.dismiss();
	}

	public static void waitForPageToLoad(long millis ) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void switchToLatestWindow(WebDriver driver){

		originalHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>(handles);
		driver.switchTo().window(handlesList.get(handlesList.size() - 1));
	}

	public static void closeAllOpenTabsAndSwitchToDefault(WebDriver driver)
	{

		for(String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(originalHandle);

	}
	
	public static void clickAppLauncherIcon(WebDriver driver)
	{
		driver.findElement(appLaunch).click();	
	}
	
	public static JavascriptExecutor getJSE (WebDriver driver)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		return jse;
	}
	
	public WebDriverWait explicitWait(WebDriver driver)
	{
		WebDriverWait eWait = new WebDriverWait(driver, 20);
		return eWait;
	}

}
