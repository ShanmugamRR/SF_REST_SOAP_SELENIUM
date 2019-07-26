package Rest_HTTP_test_case;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import REST_HTTP.API_Methods;
import REST_HTTP.API_Utilities;
import REST_HTTP.API_Variables;

public class Contact_Object implements API_Variables
{
	static String baseUrl;
	static String ContactId ;
	
	API_Utilities HTTP = new API_Utilities();
	API_Methods methods = new API_Methods();
	
	@BeforeTest
	public void getConnection() throws IOException
	{
		baseUrl = methods.getBaseURL();
		System.out.println("baseUrl: "+ baseUrl);
	}
	
	@Test(enabled = true)
	public void checkDuplicates() throws IOException
	{
		ContactId = methods.getID_REST(baseUrl, query);
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
			ContactId = methods.getID_REST(baseUrl, query);
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
			ContactId = methods.getID_REST(baseUrl, query);
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