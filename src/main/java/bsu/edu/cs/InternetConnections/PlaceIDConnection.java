package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Park;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PlaceIDConnection extends InternetConnection<Park>{

    private static final String API_KEY;

    static {
        try {
            API_KEY = loadApiKey();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API Key",e);
        }
    }

    @Override
    protected String createRequestUrl(Park searchItem) {
        String urlEncodedParkName = URLEncoder.encode(searchItem.getName(), StandardCharsets.UTF_8);
        urlEncodedParkName = urlEncodedParkName.replace("+","%20");
        return String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&rankby=distance&type=amusement_park&keyword=%s&key=%s",
                searchItem.getLatitude(),searchItem.getLongitude(),urlEncodedParkName,API_KEY);
    }

    private static String loadApiKey() throws IOException {
        InputStream apiKeyFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("ApiKeys.json");
        return JsonPath.read(apiKeyFile,"google");
    }
}
