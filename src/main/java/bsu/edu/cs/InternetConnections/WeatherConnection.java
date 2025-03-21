package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Coordinates;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;

public class WeatherConnection extends InternetConnection<Coordinates> {

    private static final String API_KEY;

    static {
        try {
            API_KEY = loadApiKey();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API Key",e);
        }
    }


    @Override
    public String createRequestUrl(Coordinates latitudeAndLongitude) {
        return "https://api.openweathermap.org/data/2.5/weather?lat=" + latitudeAndLongitude.getLatitude() + "&lon=" + latitudeAndLongitude.getLongitude() + "&appid=" + API_KEY + "&units=imperial";
    }

    private static String loadApiKey() throws IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("ApiKeys.json");
        return JsonPath.read(sampleFile,"weather");
    }

}