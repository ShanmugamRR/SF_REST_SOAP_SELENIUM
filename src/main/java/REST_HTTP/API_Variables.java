package REST_HTTP;

import CONFIG_PROPERTIES.*;

public interface API_Variables
{

	//Salesforce Login Details
	public static final String USERNAME     	= System_Properties_Methods.getInput().getProperty("SF_REST_USERNAME");
	public static final String PASSWORD     	= System_Properties_Methods.getInput().getProperty("SF_REST_PASSWORD");
	public static final String LOGINURL     	= System_Properties_Methods.getInput().getProperty("SF_LOGINURL");
	public static final String GRANTSERVICE 	= "/services/oauth2/token?grant_type=password";
	public static final String CLIENTID    		= System_Properties_Methods.getInput().getProperty("SF_CLIENTID");
	public static final String CLIENTSECRET		= System_Properties_Methods.getInput().getProperty("SF_CLIENTSECRET");

	//REST Service Details
	public static final String REST_ENDPOINT 	= "/services/data" ;
	public static final String API_VERSION 	= "/v45.0" ;




}
