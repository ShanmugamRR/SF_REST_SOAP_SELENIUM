package REST_HTTP;

public class API_Contact_Inputs 
{
	public  String baseUrl = null;
	public  String ContactId = null ;
	public  String ContactFirstName = "Test_API";
	public  String ContactLastName = "REST_API";
	public  String SOAPContactLastName = "REST_API";
	public  String ContactTitle = "Shanmugam_TEST";
	public  static String ContactEmail = "shan.2408@salesforce.com";
	public  static String query = "Select id from contact where Email ='"+ContactEmail+"'";

}
