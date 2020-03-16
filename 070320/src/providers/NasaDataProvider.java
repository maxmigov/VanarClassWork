package src.providers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import org.json.*;

public class NasaDataProvider {

    @SuppressWarnings("unused")
    private final static String ACCESS_KEY = "5nEfGfLZfmjnN062mEw9oEn4UdwbkUPNdmypwAmB";
    @SuppressWarnings("unused")
    private final static String NEO_ENDPOINT = "https://api.nasa.gov/neo/rest/v1/feed";
    
    public void getNeoAsteroids(String start, String end ) throws Exception {

        // *** 1. connect to NASA API

        URL oracle = new URL(NEO_ENDPOINT + "?start_date=" + start + "&end_date=" + end + "&api_key=" + ACCESS_KEY);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        // *** 2. read data

        String stringData = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            // System.out.println(inputLine.replaceAll("}", "}\n"));
            stringData += inputLine;
        in.close();

        // *** 3. parse JSON

        JSONObject data = new JSONObject(stringData);

        // 4. test some data
        // *****************CLASSWORK***********************************************************************************

        /*int count = data.getInt("element_count");
        System.out.println("Found " + count + " results.");
        Float d = data.getJSONObject("near_earth_objects")
                .getJSONArray(start)
                .getJSONObject(0)
                .getJSONObject("estimated_diameter")
                .getJSONObject("kilometers")
                .getFloat("estimated_diameter_min");
        System.out.println("Diameter in KM " + d);*/
        // ****************END CLASSWORK********************************************************************************

        // ************FIRST HOMEWORK(WILL BE ABLE TO SHOW INFOS ONLY FOR ONE DAY!!!!!)*********************************
        // START

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

        // *******************STOP FIRST HOMEWORK***********************************************************************


        //****************************************************************************************************
        // *****TESTING getNames method of JSONObject (converts Strings of an object into String[] array)*****
        // P.S. NOT MANDATORY!!!
        /*
        String[] date_array = getNames(data.getJSONObject("near_earth_objects"));
        List<String> date_array_list = new ArrayList<>();
        Collections.addAll(date_array_list, date_array);
        Integer date_array_list_size = date_array_list.size();
        */
        //****************************************************************************************************

        // SECOND HOMEWORK (CONVERTING JSONObjects INTO MAP)
        // START

        JSONObject object = (JSONObject) data.get("near_earth_objects");
        Map<String, Object> keys = object.toMap();  // toMap() is a method of JSONObject class
        keys.keySet().toArray(); // returns an array of keys (DO WE NEED IT????)
        keys.values().toArray(); // returns an array of values (DO WE NEED IT????)

        for (Map.Entry<String, Object> pair : keys.entrySet()) {
            System.out.println("Map's key:  " + pair.getKey() + '\n' + "Map's value:  " + pair.getValue() + '\n');
        }
    }
}