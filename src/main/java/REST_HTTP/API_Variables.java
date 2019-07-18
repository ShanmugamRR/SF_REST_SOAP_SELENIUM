package REST_HTTP;

public interface API_Variables 
{
	//Salesforce Login Details
	public static final String USERNAME     	= "shan.2408@gmail.com";
	public static final String PASSWORD     	= "Beat@2013LpVWQmoTT08KnxYVSkC55tbnh";
	public static final String LOGINURL     	= "https://login.salesforce.com";
	public static final String GRANTSERVICE 	= "/services/oauth2/token?grant_type=password";
	public static final String CLIENTID    	= "3MVG9G9pzCUSkzZuq3BzJXe_gTkvgpzcGhGCA5kn8BXIeFCqY3YYWk1Pr5WdNdLZBKxwWCNj9yOvRbRx98nPH";
	public static final String CLIENTSECRET	= "7778334A69FA68A904EA08DD4AEC396444399C2B2CB748B60AFE223493AD56BB";
	
	//REST Service Details
	public static final String REST_ENDPOINT 	= "/services/data" ;
	public static final String API_VERSION 	= "/v45.0" ;
	
	//Contact Object Details
	public static String ContactFirstName = "Test_API";
	public static String ContactLastName = "REST_API";
	public static String ContactTitle = "Shanmugam_TEST";
	public static String ContactEmail = "shan.2408@salesforce.com";
	public static String query = "Select id from contact where Email ='"+ContactEmail+"'";
	
	
	

}
