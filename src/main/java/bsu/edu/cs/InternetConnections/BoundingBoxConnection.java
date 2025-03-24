package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Park;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BoundingBoxConnection extends InternetConnection<Park>{

    @Override
    protected String createRequestUrl(Park searchItem) {
        String parkNameEncoded = URLEncoder.encode(searchItem.getName(), StandardCharsets.UTF_8);
        return String.format("https://nominatim.openstreetmap.org/search?q=%s&format=json&polygon_geojson=1&lat=%s&lon=%s&limit=1",
                parkNameEncoded,searchItem.getLatitude(),searchItem.getLongitude());
    }
}
