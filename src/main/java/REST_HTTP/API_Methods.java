package REST_HTTP;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class API_Methods implements API_Variables
{

	static String baseUrl;
	API_Utilities api = new API_Utilities();
	
	public String getBaseURL() throws IOException
	{
		String loginURL = LOGINURL + GRANTSERVICE + "&client_id=" 
				+ CLIENTID + "&client_secret=" + CLIENTSECRET + "&username=" 
				+ USERNAME + "&password=" + PASSWORD;
		//https://login.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9G9pzCUSkzZuq3BzJXe_gTkvgpzcGhGCA5kn8BXIeFCqY3YYWk1Pr5WdNdLZBKxwWCNj9yOvRbRx98nPH&client_secret=7778334A69FA68A904EA08DD4AEC396444399C2B2CB748B60AFE223493AD56BB&username=shan.2408@gmail.com&password=Beat@2013LpVWQmoTT08KnxYVSkC55tbnh
		System.out.println(loginURL);
		HttpPost httpPost=	api.getHttpPost(loginURL);
		HttpResponse response = api.getResponsePost(httpPost);
		int statuscode = 200;
		System.out.println("Status code: "+api.getStatusCode(response,statuscode));
		String loginAccessToken = null;
		String loginInstanceUrl = null;
		JSONObject json=api.getJSONToken(response);
		try {
			loginAccessToken = json.getString("access_token");
			loginInstanceUrl = json.getString("instance_url");
		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		}
		baseUrl = loginInstanceUrl + REST_ENDPOINT + API_VERSION ;
		api.Header = new BasicHeader("Authorization", "OAuth " + loginAccessToken) ;
		System.out.println("_Header: " +api.Header);
		System.out.println("\n" + response.getStatusLine());
		System.out.println("Successful login");
		System.out.println("instance URL: "+loginInstanceUrl);
		System.out.println("access token/session ID: "+loginAccessToken);
		//System.out.println("baseUrl: "+ baseUrl);
		return baseUrl;
		
	}
	
	public String  getID(String url, String query) throws IOException
	{
		String query_c = api.getQuery(query);
		String baseurl = url+"/query?q="+query_c;
		System.out.println(baseurl);
		String ID;
		HttpGet httpGet = api.getHttp(baseurl);
		HttpResponse response = api.getResponse(httpGet);
		
		int statuscode = 200;
		if(api.getStatusCode(response, statuscode) == statuscode)
		{
			String response_string = EntityUtils.toString(response.getEntity());
			JSONObject json = new JSONObject(response_string);
			//System.out.println("JSON result of Query:\n" + json.toString(1));
			JSONArray j = json.getJSONArray("records");
			if(j.length() ==1)
			{
				ID = json.getJSONArray("records").getJSONObject(0).getString("Id");
				//System.out.println(ID);
				return ID;
			} else {
				System.out.println("No Records or More than one ID has returned...");
				return "No ID";
			}
		}
return null;

	}
}
