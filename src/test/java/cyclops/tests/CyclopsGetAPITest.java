package cyclops.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cyclops.base.TestBase;
import cyclops.client.Restclient;

public class CyclopsGetAPITest extends TestBase {
	TestBase testbase;

	String URL1;
	String apiurl;
	String serviceurl;
	String     URL2;
	String CompareURL;
	       
	@BeforeMethod

	public void setup() throws ClientProtocolException, IOException {

		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL");
		
		CompareURL=prop.getProperty("compareurl");

		URL1 = serviceurl + apiurl;
		URL2 = serviceurl + CompareURL;

	}

	@Test
public void getAPITest() throws ClientProtocolException, IOException {

		Restclient restclient = new Restclient();

	String a=	restclient.get(URL1);
		
		
		
		System.out.println("running second API");
		
	String b=	restclient.get(URL2);
	
	if(a.equals(b)) {
		
		System.out.println("test is passed");
		
	}
	
	else
	{
		System.out.println("test is failed");
	}

	}

}
