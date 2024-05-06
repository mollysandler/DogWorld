import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Weather extends JPanel {

    static String apiKeyPath = "keys/WeatherAPIKey";

    public static double getTemperature() throws Exception {
        System.out.println(WorldData.getWorldData().getLocation());
        FileInputStream apiKeyFile = new FileInputStream(apiKeyPath);
        String apiKey = new String(apiKeyFile.readAllBytes());

        Point locationPoint = WorldData.getWorldData().getLocation();
        String locationKey = Double.toString(locationPoint.getX()) + "," + Double.toString(locationPoint.getY());
        String encodedLocationKey = URLEncoder.encode(locationKey, StandardCharsets.UTF_8.toString());

        String urlStr = "http://dataservice.accuweather.com/locations/v1/cities/geoposition/search?apikey="
                + apiKey + "&q=" + encodedLocationKey;

        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String response = "";
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            response += inputLine;
        }
        System.out.println(response);

        JSONObject jsonResponse = new JSONObject(response);
        locationKey = jsonResponse.getString("Key");
        System.out.println(locationKey);
        urlStr = "http://dataservice.accuweather.com/currentconditions/v1/"
                + locationKey + "?apikey=" + apiKey;

        url = new URL(urlStr);
        connection = url.openConnection();
        inputStream = connection.getInputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));

        response = "";
        while ((inputLine = reader.readLine()) != null) {
            response += inputLine;
        }
        System.out.println(response);

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
