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
    // key: qdUL9O9zA0UD9fo5vdJ5A6aWAJ2ibkXF
    //      XGUyxtVpHGGAcGuYqKsT9vPDAzLVRiXG

    public static double getTemperature(String location) throws Exception {
        FileInputStream apiKeyFile = new FileInputStream(apiKeyPath);
        String apiKey = new String(apiKeyFile.readAllBytes());

        String locationKey = location;
        System.out.println(locationKey);

        String urlStr = "http://dataservice.accuweather.com/currentconditions/v1/"
                + locationKey + "?apikey=" + apiKey;

        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }

        inputStream.close();
        return parse(response.toString());
    }

    private static double parse(String response) {
        JSONArray jsonResponse = new JSONArray(response);
        JSONObject currentWeather = jsonResponse.getJSONObject(0);
        JSONObject temperatureObj = currentWeather.getJSONObject("Temperature");
        JSONObject metricObj = temperatureObj.getJSONObject("Metric");
        return metricObj.getDouble("Value");
    }
}
