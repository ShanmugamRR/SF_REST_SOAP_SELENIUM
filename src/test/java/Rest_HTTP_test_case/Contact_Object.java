package Rest_HTTP_test_case;

import java.io.IOException;
import java.util.PriorityQueue;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import REST_HTTP.API_Methods;
import REST_HTTP.API_Utilities;
import REST_HTTP.API_Variables;


public class Contact_Object implements API_Variables
{
	static String baseUrl;
	static String ContactId ;
	static String ContactFirstName = "Test_API";
	static String ContactLastName = "REST_API";
	static String ContactTitle = "Shanmugam_TEST";
	static String ContactEmail = "shan.2408@salesforce.com";
	static String query = "Select id from contact where Email ='"+ContactEmail+"'";

	API_Utilities HTTP = new API_Utilities();
	API_Methods methods = new API_Methods();


	@BeforeTest
	public void getConnection() throws IOException
	{
		String loginURL = LOGINURL + GRANTSERVICE + "&client_id=" 
				+ CLIENTID + "&client_secret=" + CLIENTSECRET + "&username=" 
				+ USERNAME + "&password=" + PASSWORD;

		HttpPost httpPost=	HTTP.getHttpPost(loginURL);
		HttpResponse response = HTTP.getResponsePost(httpPost);

		int statuscode = 200;
		System.out.println("Status code: "+HTTP.getStatusCode(response,statuscode));

		String loginAccessToken = null;
		String loginInstanceUrl = null;

		JSONObject json=HTTP.getJSONToken(response);

		try {

			loginAccessToken = json.getString("access_token");
			loginInstanceUrl = json.getString("instance_url");
		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		}

		baseUrl = loginInstanceUrl + REST_ENDPOINT + API_VERSION ;
		HTTP.Header = new BasicHeader("Authorization", "OAuth " + loginAccessToken) ;
		System.out.println("_Header: " +HTTP.Header);
		System.out.println("\n" + response.getStatusLine());
		System.out.println("Successful login");
		System.out.println("instance URL: "+loginInstanceUrl);
		System.out.println("access token/session ID: "+loginAccessToken);
		System.out.println("baseUrl: "+ baseUrl);
	}


	@Test(priority=1)
	public void checkDuplicates() throws IOException
	{
		ContactId = methods.getID(baseUrl, query);
		System.out.println(ContactId);
		if (ContactId.equals("No ID"))
		{
			System.out.println("Creating Records.......");
			Create_ContactObjects();
			System.out.println("Updating Records.......");
			Update_ContactObjects();
		}else {
			System.out.println("Deleting Records.......");
			Delete_ContactObject();
		}
	}

	@Test(enabled = false)
	public void Create_ContactObjects() throws IOException
	{

		String url = baseUrl + "/sobjects/Contact/";
		int statuscode = 201;

		JSONObject create_contact = new JSONObject();
		create_contact.put("FirstName",ContactFirstName);
		create_contact.put("LastName", ContactLastName);
		create_contact.put("Email", ContactEmail);

		HttpPost httpPost =	HTTP.getHttpPost(url);

		StringEntity body = HTTP.getBody(create_contact);
		
		httpPost.setEntity(body);

		HttpResponse response = HTTP.getResponsePost(httpPost);
		System.out.println("Status code: "+HTTP.getStatusCode(response,statuscode));
		if(HTTP.getStatusCode(response,statuscode) == statuscode)
		{
			System.out.println("Created Successfully....");
		}else { System.out.println("Error...");
		}
	}


	@Test(enabled = false)
	public void Update_ContactObjects() throws IOException
	{

		if(ContactId == null || ContactId.equals("No ID"))
		{

			System.out.println("ID is null. Getting ID.......");
			ContactId = methods.getID(baseUrl, query);
			System.out.println(ContactId);

		} 

		if(!(ContactId.equals("No ID")))
		{
			int statuscode = 204;
			String url = baseUrl + "/sobjects/Contact/" + ContactId;
			JSONObject update_contact = new JSONObject();
			update_contact.put("LastName", "REST_API-UPDATED");
			update_contact.put("MobilePhone", "9046789");
			update_contact.put("Title", ContactTitle);
			//contact.put("AccountId","QE-Automation");

			REST_HTTP.API_Utilities.HttpPatch httpPatch = HTTP.getHttpPatch(url);
			
			StringEntity body = HTTP.getBody(update_contact);
			
			httpPatch.setEntity(body);
			HttpResponse response = HTTP.getResponsePost(httpPatch);
			System.out.println("Status code: "+HTTP.getStatusCode(response,statuscode));
			if(HTTP.getStatusCode(response, statuscode) == statuscode)
			{
				System.out.println("Updated Successfully.....");
			}
			else  {
				System.out.println("Error......");
			}
		}
		else {
			System.out.println("No ID retruned.....");
		}

	}

	@Test(enabled = false)
	public void Delete_ContactObject() throws IOException
	{
		if(ContactId == null)
		{

			System.out.println("\nID is null. Getting ID.......");
			ContactId = methods.getID(baseUrl, query);
			System.out.println(ContactId);

		}
		if(!(ContactId.equals("No ID")))
		{
			int statuscode = 204;
			String url = baseUrl + "/sobjects/Contact/" + ContactId;
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
		}else
		{
			System.out.println("No ID returned");
		}



	}



}


