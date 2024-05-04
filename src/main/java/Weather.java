import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Weather extends JPanel {

    static String apiKeyPath = "keys/WeatherAPIKey";

    public static double getTemperature() throws Exception {
        System.out.println(WorldData.getWorldData().getLocation());
        FileInputStream apiKeyFile = new FileInputStream( apiKeyPath );

        // XGUyxtVpHGGAcGuYqKsT9vPDAzLVRiXG

        // http://dataservice.accuweather.com/locations/v1/cities/geoposition/search?apikey=
        // XGUyxtVpHGGAcGuYqKsT9vPDAzLVRiXG
        // &q=35.302990%2C%20-120.667650

        String locationKey = "331999";
        String urlStr = "http://dataservice.accuweather.com/currentconditions/v1/"
                + locationKey + "?apikey=" + new String(apiKeyFile.readAllBytes());

        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String response = "";
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            response += inputLine;
        }
        inputStream.close();
        return parse(response);
    }

    private static double parse(String response) {
        JSONArray jsonResponse = new JSONArray(response);
        JSONObject currentWeather = jsonResponse.getJSONObject(0);
        JSONObject temperatureObj = currentWeather.getJSONObject("Temperature");
        JSONObject metricObj = temperatureObj.getJSONObject("Metric");
        return metricObj.getDouble("Value");
    }
}
