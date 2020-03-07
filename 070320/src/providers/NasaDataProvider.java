package providers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;

public class NasaDataProvider {

	@SuppressWarnings("unused")
	private final static String ACCESS_KEY = "5nEfGfLZfmjnN062mEw9oEn4UdwbkUPNdmypwAmB";
	@SuppressWarnings("unused")
	private final static String NEO_ENDPOINT = "https://api.nasa.gov/neo/rest/v1/feed";
	
	public void getNeoAsteroids() throws Exception {
		
		// 1. connect to NASA API
		URL oracle = new URL(NEO_ENDPOINT + "?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
		// 2. read data
        String stringData = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null)
           // System.out.println(inputLine.replaceAll("}", "}\n"));
        	stringData += inputLine;
        in.close();

		// 3. parse JSON
        JSONObject data = new JSONObject(stringData);
		// 4. test some data
		
	}
}
