package Rest_HTTP_test_case;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import REST_HTTP.API_Contact_Inputs;
import REST_HTTP.API_Methods;
import REST_HTTP.API_Utilities;
import REST_HTTP.API_Variables;

public class Contact_Create implements API_Variables
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
	public void Create_ContactObjects() throws IOException
	{
		inputs.ContactId = methods.getID(inputs.baseUrl, inputs.query);
		System.out.println("ContactID is:"+inputs.ContactId);
		if (inputs.ContactId == null || inputs.ContactId.equals("No ID"))
		{
		String url = inputs.baseUrl + "/sobjects/Contact/";
		int statuscode = 201;
		JSONObject create_contact = new JSONObject();
		create_contact.put("FirstName",inputs.ContactFirstName);
		create_contact.put("LastName", inputs.ContactLastName);
		create_contact.put("Email", inputs.ContactEmail);
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
	}else
	{
		System.out.println("Contact Email id Already exists..Change ID and Create again...");
	}
	}
}
