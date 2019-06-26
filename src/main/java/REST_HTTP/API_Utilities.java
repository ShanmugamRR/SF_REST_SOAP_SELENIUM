package REST_HTTP;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class API_Utilities 
{

	public static Header Header;
	public static Header PrintHeader = new BasicHeader("X-PrettyPrint", "1");

	public int getStatusCode(HttpResponse response, int statuscode) 
	{
		int status_Code = response.getStatusLine().getStatusCode() ;
		if (status_Code != statuscode) 
		{
			System.out.println("Error Code: "+status_Code);	
		}
		return status_Code;

	}

	public JSONObject getJSONToken(HttpResponse response)
	{
		String getResult = null;
		try {
			getResult = EntityUtils.toString(response.getEntity());
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		JSONObject jsonObject = null;
		jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
		return jsonObject;

	}



	public HttpResponse getResponse(HttpPost httpPost) throws IOException, IOException 
	{
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse response = httpClient.execute(httpPost);
		return response;

	}

	public HttpPost getHttpPost(String url)
	{
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(Header);
		httpPost.addHeader(PrintHeader);
		return httpPost;
	}



}
