package REST_HTTP;

import java.io.IOException;

import CONFIG_PROPERTIES.Excel_Metadata;
import SELENIUM_WEBDRIVER.WebDriver_Varibales;

public class API_Contact_Inputs implements API_Variables,WebDriver_Varibales
{
	//static Excel_Metadata excel = new Excel_Metadata();
	public String baseUrl = null;
	public String ContactId = null;
	public  static String ContactFirstName = "TEST_API";
	//public  static String ContactFirstName = Excel_Metadata.getMapData("FirstName");
	public  static String ContactLastName = "API";
	public static String SOAPContactLastName;
	public static String ContactTitle = "IT-A";
	public  static String ContactEmail = "shan.2408@salesforce.com";
	public  static String ContactPhone = "78956231";
	public static String query = "Select id,lastname from contact where Email ='"+ContactEmail+"'";
	
	//public  static String query = "Select id from contact where Email ='"+ContactEmail+"'";

	public static void inputs() throws IOException
	{
		ContactFirstName = Excel_Metadata.getMapData("Firstname");
		ContactLastName = Excel_Metadata.getMapData("Lastname");
		ContactTitle = Excel_Metadata.getMapData("Title");
		ContactEmail = Excel_Metadata.getMapData("Email");
		ContactPhone = Excel_Metadata.getMapData("Phone");
		query =  "Select id from contact where Email ='"+ContactEmail+"'";
	}
}
