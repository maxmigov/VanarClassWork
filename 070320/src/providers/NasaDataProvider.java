package src.providers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.databind.type.ReferenceType;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.json.JSONObject.getNames;

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

        /*Integer JSON_array_size = data.getInt("element_count");
        Asteroids.date = start;
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
        System.out.println('\n' + "______" + Asteroids.date + "______" + '\n');
        for (Asteroids s : asteroid_list) {
            System.out.println(s);
        }*/

        String[] date_array = getNames(data.getJSONObject("near_earth_objects"));
        List<String> date_array_list = new ArrayList<>();
        Collections.addAll(date_array_list, date_array);
        Integer date_array_list_size = date_array_list.size();
        /*Collections.sort(date_array_list);
        for (int i = 0; i < date_array_list.size(); i++) {
            System.out.println(date_array_list.get(i));
        }

        */
        /*
        List<Asteroids> asteroids = new ArrayList<>();
        for (int i = 0; i < date_array_list_size; i++) {
            for (String s : date_array_list) {
                                asteroids.add((new Asteroids
                                (data.getJSONObject("near_earth_objects")
                                        .getJSONArray(s)
                                        .getJSONObject(i)
                                        .getInt("id"),
                                data.getJSONObject("near_earth_objects")
                                        .getJSONArray(s)
                                        .getJSONObject(i)
                                        .getJSONArray("close_approach_data")
                                        .getJSONObject(0)
                                        .getJSONObject("miss_distance")
                                        .getFloat("kilometers"),
                                data.getJSONObject("near_earth_objects")
                                        .getJSONArray(s)
                                        .getJSONObject(i)
                                        .getJSONObject("estimated_diameter")
                                        .getJSONObject("kilometers")
                                        .getFloat("estimated_diameter_max"),
                                data.getJSONObject("near_earth_objects")
                                        .getJSONArray(s)
                                        .getJSONObject(i)
                                        .getBoolean("is_potentially_hazardous_asteroid"))));
            }

        }
        for (int i = 0; i < asteroids.size(); i++) {
            System.out.println(asteroids.get(i));
        }
        */
        JSONObject object = (JSONObject) data.get("near_earth_objects");
        Set<String> keys = object.keySet();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> carMap = mapper.readValue(data.getJSONObject("near_earth_objects"), Asteroids.class);
        for (String s : keys) {
            System.out.println(s);
        }

        /*Map<String, List<Asteroids>> map = new HashMap<String, List<Asteroids>>();
            for () {
                
            }

         */
    }
}