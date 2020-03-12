package src.providers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class NasaDataProvider {

    @SuppressWarnings("unused")
    private final static String ACCESS_KEY = "5nEfGfLZfmjnN062mEw9oEn4UdwbkUPNdmypwAmB";
    @SuppressWarnings("unused")
    private final static String NEO_ENDPOINT = "https://api.nasa.gov/neo/rest/v1/feed";

    public void getNeoAsteroids( String start, String end ) throws Exception {

        // 1. connect to NASA API
        URL oracle = new URL(NEO_ENDPOINT + "?start_date=" + start + "&end_date=" + end + "&api_key=" + ACCESS_KEY);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
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
        /*int count = data.getInt("element_count");
        System.out.println("Found " + count + " results.");
        Float d = data.getJSONObject("near_earth_objects")
                .getJSONArray(start)
                .getJSONObject(0)
                .getJSONObject("estimated_diameter")
                .getJSONObject("kilometers")
                .getFloat("estimated_diameter_min");
        System.out.println("Diameter in KM " + d);*/

        Integer JSON_array_size = data.getInt("element_count");
        List<Asteroids> asteroid_list = new ArrayList<>();
        for (int i = 0; i < JSON_array_size; i++) {
                asteroid_list.add(new Asteroids(data.getJSONObject("near_earth_objects")
                        .getJSONArray(start)
                        .getJSONObject(i)
                        .getInt("id"),
                        data.getJSONObject("near_earth_objects")
                                .getJSONArray(start)
                                .getJSONObject(i)
                                .getJSONArray("close_approach_data")
                                .getJSONObject(0)
                                .getJSONObject("miss_distance")
                                .getFloat("kilometers"),
                        data.getJSONObject("near_earth_objects")
                                .getJSONArray(start)
                                .getJSONObject(i)
                                .getJSONObject("estimated_diameter")
                                .getJSONObject("kilometers")
                                .getFloat("estimated_diameter_max"),
                        data.getJSONObject("near_earth_objects")
                                .getJSONArray(start)
                                .getJSONObject(i)
                                .getBoolean("is_potentially_hazardous_asteroid")));
        }
        for (Asteroids s : asteroid_list) {
            System.out.println(s);
        }
    }
}