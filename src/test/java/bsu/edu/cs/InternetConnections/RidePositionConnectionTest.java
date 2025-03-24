package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.BoundingBox;
import bsu.edu.cs.Parsers.RidePositionSearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RidePositionConnectionTest {

    @Test
    public void createURLRequestTest() {
        RidePositionSearch ridePositionSearch = new RidePositionSearch("Pirates of the Caribbean", new BoundingBox("28.4148969","28.4232773","-81.5869975","-81.5758593"));

        RidePositionConnection ridePositionConnectionInstance = new RidePositionConnection();
        assertEquals("https://nominatim.openstreetmap.org/search?q=Pirates+of+the+Caribbean&format=json&viewbox=28.4148969,28.4232773,-81.5869975,-81.5758593&bounded=1&limit=1",
                ridePositionConnectionInstance.createRequestUrl(ridePositionSearch));
    }
}
