package Rest_HTTP_test_case;

import java.io.IOException;
import java.util.PriorityQueue;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
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

import REST_HTTP.API_Utilities;
import REST_HTTP.API_Variables;


public class Contact_Object implements API_Variables
{
	static String baseUrl;
	static String ContactId ;
	static String ContactFirstName = "Test_API";
	static String ContactLastName = "REST_API";
	static String ContactTitle = "Shanmugam_TEST";
	static String query = "Select id from contact where title ="+ContactTitle;

	API_Utilities HTTP = new API_Utilities();


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

	/*@Test

	public void Create_ContactObjects() throws IOException
	{

		String url = baseUrl + "/sobjects/Contact/";
		int statuscode = 201;

		JSONObject create_contact = new JSONObject();
		create_contact.put("FirstName",ContactFirstName);
		create_contact.put("LastName", ContactLastName);
		create_contact.put("Title", ContactTitle);

		HttpPost httpPost =	HTTP.getHttpPost(url);

		StringEntity body = new StringEntity(create_contact.toString(1));
		body.setContentType("application/json");
		httpPost.setEntity(body);

		HttpResponse response = HTTP.getResponsePost(httpPost);
		System.out.println("Status code: "+HTTP.getStatusCode(response,statuscode));
if(HTTP.getStatusCode(response,statuscode) == statuscode)
{
System.out.println("\nCreated Successfully....");
}else { System.out.println("\n Error...");
	}*/


	@Test
	public void Update_ContactObjects() throws IOException
	{

		if(ContactId == null)
		{
			System.out.println("\n ID is null. Getting ID.......");
			String query_c = HTTP.replaceString(query);
			System.out.println(query_c);
			/*ContactId=HTTP.getID(baseUrl, query_c);
	System.out.println(ContactId);*/

			HttpGet httpGet = HTTP.getHttp(baseUrl);
			HttpResponse response = HTTP.getResponse(httpGet);

			int statuscode = 200;
			if(HTTP.getStatusCode(response, statuscode) == statuscode)
			{
				String response_string = EntityUtils.toString(response.getEntity());
				JSONObject json = new JSONObject(response_string);
				System.out.println("JSON result of Query:\n" + json.toString(1));
				JSONArray j = json.getJSONArray("records");
				if(j.length() ==1)
				{
					ContactId = json.getJSONArray("records").getJSONObject(0).getString("Id");
					System.out.println(ContactId);
				} else {
					System.out.println("More than one ID has returned...");
				}
			}
		} 
		int statuscode = 204;
		String url = baseUrl + "/sobjects/Contact/" + ContactId;
		JSONObject contact = new JSONObject();
		contact.put("LastName", "REST_API-UPDATED");
		contact.put("MobilePhone", "90456789");

		REST_HTTP.API_Utilities.HttpPatch httpPatch = HTTP.getHttpPatch(baseUrl);
		StringEntity body = new StringEntity(contact.toString(1));
		body.setContentType("application/json");
		httpPatch.setEntity(body);
		HttpResponse response = HTTP.getResponsePost(httpPatch);
		if(HTTP.getStatusCode(response, statuscode) == statuscode)
		{
			System.out.println("\n Updated Successfully.....");
		}
		else  {
			System.out.println("\n Error......");
		}
	}

}


