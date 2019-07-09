package REST_HTTP;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class API_Methods 
{

	API_Utilities api = new API_Utilities();
	
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
