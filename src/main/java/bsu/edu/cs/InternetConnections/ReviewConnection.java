package bsu.edu.cs.InternetConnections;

import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;

public class ReviewConnection extends InternetConnection<String>{

    private static final String API_KEY;

    static {
        try {
            API_KEY = loadApiKey();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API Key",e);
        }
    }
    @Override
    protected String createRequestUrl(String searchItem) {
        return String.format("https://maps.googleapis.com/maps/api/place/details/json?place_id=%s&fields=name,rating,reviews&key=%s",
                searchItem,API_KEY);
    }

    private static String loadApiKey() throws IOException {
        InputStream apiKeyFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("ApiKeys.json");
        return JsonPath.read(apiKeyFile,"google");
    }
}
