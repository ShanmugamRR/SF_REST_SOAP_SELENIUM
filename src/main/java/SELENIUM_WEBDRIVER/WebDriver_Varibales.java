package SELENIUM_WEBDRIVER;

import CONFIG_PROPERTIES.*;

public interface WebDriver_Varibales 
{
	//Salesforce Login
	public static final String SF_USERNAME     	= System_Properties_Methods.getInput().getProperty("SF_UI_USERNAME");
	public static final String SF_PASSWORD     	= System_Properties_Methods.getInput().getProperty("SF_UI_PASSWORD");
	public static final String SF_LOGINURL     	= System_Properties_Methods.getInput().getProperty("SF_LOGINURL"); 
	
	//Local ChromeDriver 
	public static final String CHROMEDRIVER		= System_Properties_Methods.getInput().getProperty("CHROMEDRIVERPATH");
	
	//SauceLabs Details
	public static final String SL_USERNAME = System_Properties_Methods.getInput().getProperty("SL_USERNAME");
	public static final String SL_ACCESS_KEY = System_Properties_Methods.getInput().getProperty("SL_ACCESS_KEY");
	public static final String SL_URL = System_Properties_Methods.getInput().getProperty("SL_URL");
	
	//Login 
	public static String USER =	"input#username";
	public static String PWD = 	"password";
	public static String Login = "input#Login";  
	
	//AppLauncher
	public static String AppLauncher = "//div[@class='slds-icon-waffle']";
	
	//Sales Apps
	public static String SalesApp = "//p[contains(text(),'Manage your sales')]";
	
	//Contact Object
	public static String contactObject = "//*[@title='Contacts']";
	public static String NewButton = "//div[@title='New' or text()='New']";
	public static String FNameXpath = "//span[text()='First Name']/../following::input[1]";
	public static String FirstName = "Selenium";
	public static String LNameXpath = "//span[text()='Last Name']/../following::input[1]";
	public static String LastName = "TEST";
	public static String ANameXpath = "//label/span[text()='Account Name']/../following::input[1]";
	public static String Email = "shan.2408@salesforce.com";
	public static String EmailXpath = "//span[text()='Email']/../following-sibling::input";
	public static String AccountName = "QE-Automation";
	public static String ANameXpathSelect = "(//mark[text()='QE-Automation'])[1]";
	public static String PhoneNumber = "852852963";
	public static String PhoneXpath = "//span[text()='Phone']/../following-sibling::input";
	public static String MobileNumber = "18015263";
	public static String MobileXpath = "//span[text()='Mobile']/../following-sibling::input";
	public static String SaveButton = "//button[@title='Save']";
	public static String NameList = "(//a[@title='"+FirstName+" "+LastName+"'])[1]";
	public static String DownButton = "//a[@role='button' and @title ='Show 5 more actions']";
	public static String EditButton = "//a[@title='Edit']";
	public static String DeleteButton = "//a[@title='Delete']";
	public static String DeleteConfirm = "//button/span[text()='Delete']";
}
