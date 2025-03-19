package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.RidePositionSearch;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RidePositionConnection extends InternetConnection<RidePositionSearch>{
    @Override
    protected String createRequestUrl(RidePositionSearch searchItem) {
        return String.format("https://nominatim.openstreetmap.org/search?q=%s&format=json&viewbox=%s,%s,%s,%s&bounded=1&limit=1",
                URLEncoder.encode(searchItem.getRideName(), StandardCharsets.UTF_8),
                searchItem.getBoundingBox().getMinimumLatitude(),
                searchItem.getBoundingBox().getMaximumLatitude(),
                searchItem.getBoundingBox().getMinimumLongitude(),
                searchItem.getBoundingBox().getMaximumLongitude());
    }

}

