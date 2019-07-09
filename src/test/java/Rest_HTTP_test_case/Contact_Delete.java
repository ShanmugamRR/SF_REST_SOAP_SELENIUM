package Rest_HTTP_test_case;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import REST_HTTP.API_Contact_Inputs;
import REST_HTTP.API_Methods;
import REST_HTTP.API_Utilities;
import REST_HTTP.API_Variables;

public class Contact_Delete implements API_Variables
{
	API_Utilities HTTP = new API_Utilities();
	API_Methods methods = new API_Methods();
	API_Contact_Inputs inputs = new API_Contact_Inputs();
	
	@BeforeTest
	public void getConnection() throws IOException
	{
		String loginURL = LOGINURL + GRANTSERVICE + "&client_id=" 
				+ CLIENTID + "&client_secret=" + CLIENTSECRET + "&username=" 
				+ USERNAME + "&password=" + PASSWORD;
		//https://login.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9G9pzCUSkzZuq3BzJXe_gTkvgpzcGhGCA5kn8BXIeFCqY3YYWk1Pr5WdNdLZBKxwWCNj9yOvRbRx98nPH&client_secret=7778334A69FA68A904EA08DD4AEC396444399C2B2CB748B60AFE223493AD56BB&username=shan.2408@gmail.com&password=Beat@2013LpVWQmoTT08KnxYVSkC55tbnh
		System.out.println(loginURL);
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
		inputs.baseUrl = loginInstanceUrl + REST_ENDPOINT + API_VERSION ;
		HTTP.Header = new BasicHeader("Authorization", "OAuth " + loginAccessToken) ;
		System.out.println("_Header: " +HTTP.Header);
		System.out.println("\n" + response.getStatusLine());
		System.out.println("Successful login");
		System.out.println("instance URL: "+loginInstanceUrl);
		System.out.println("access token/session ID: "+loginAccessToken);
		System.out.println("baseUrl: "+ inputs.baseUrl);
	}
	
	@Test(enabled = true)
	public void Delete_ContactObject() throws IOException
	{
		if(inputs.ContactId == null)
		{
			System.out.println("\nID is null. Getting ID.......");
			inputs.ContactId = methods.getID(inputs.baseUrl, inputs.query);
			System.out.println(inputs.ContactId);
		}
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
		}else
		{
			System.out.println("No ID returned");
		}
	}

}