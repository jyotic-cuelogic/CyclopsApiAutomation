package cyclops.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Restclient {

	public String get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(url);

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);

		int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();

		System.out.println("status code" + StatusCode);

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responsejson = new JSONObject(responseString);

		System.out.println("Response JSON from API" + responsejson);

		Header[] headersArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {

			allHeaders.put(header.getName(), header.getValue());

		}

		System.out.println("Headers Array-->>" + allHeaders);

		return responseString;
	}

}
