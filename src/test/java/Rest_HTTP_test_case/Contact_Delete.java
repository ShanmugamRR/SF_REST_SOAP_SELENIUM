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
		inputs.baseUrl = methods.getBaseURL();
		System.out.println("baseUrl: "+ inputs.baseUrl);
	}
	
	@Test(enabled = true)
	public void Delete_ContactObject() throws IOException
	{
		if(inputs.ContactId == null)
		{
			System.out.println("ID is null. Getting ID.......");
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
