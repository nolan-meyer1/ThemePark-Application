package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Park;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoundingBoxConnectionTest {

    @Test
    public void createURLRequestTest(){
        Park testPark = new Park(26,"Disney Magic Kingdom","United States","North America","28.4177","-81.5812","America/New_York");
        BoundingBoxConnection boundingBoxConnectionInstance = new BoundingBoxConnection();
        assertEquals("https://nominatim.openstreetmap.org/search?q=Disney+Magic+Kingdom&format=json&polygon_geojson=1&lat=28.4177&lon=-81.5812&limit=1",
                boundingBoxConnectionInstance.createRequestUrl(testPark));
    }

}
