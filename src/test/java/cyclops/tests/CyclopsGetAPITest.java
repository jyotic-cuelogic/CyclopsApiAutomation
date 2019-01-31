package cyclops.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cyclops.base.TestBase;
import cyclops.client.Restclient;
import cyclops.util.TestUtil;

public class CyclopsGetAPITest extends TestBase {
	TestBase testbase;

	String URL1;
	String apiurl;
	String serviceurl;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod

	public void setup() throws ClientProtocolException, IOException {

		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL");
		URL1 = serviceurl + apiurl;

	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {

		Restclient restclient = new Restclient();

		closeableHttpResponse = restclient.get(URL1);

		int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		
		System.out.println("status code" +" " + StatusCode);
		Assert.assertEquals(StatusCode,RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
		
		

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		
		//single value assertion
		String perPagevalue= TestUtil.getValueByJPath(responseJson,"/per_page");
		System.out.println("per page value is" + perPagevalue);
	     Assert.assertEquals(Integer.parseInt(perPagevalue), 3,"test cases failed");
		
		
		//get the value from JSON array

		System.out.println("Response JSON from API" + responseJson);

		Header[] headersArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {

			allHeaders.put(header.getName(), header.getValue());

		}

		System.out.println("Headers Array-->>" + allHeaders);

	}
}