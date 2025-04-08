package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Park;
import bsu.edu.cs.Parsers.RidePositionSearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RidePositionConnectionTest {

    @Test
    public void createURLRequestTest() {
        Park testPark = new Park(26,"Disney Magic Kingdom","28.4177","-81.5812");
        RidePositionSearch ridePositionSearch = new RidePositionSearch("Pirates of the Caribbean", testPark);
        RidePositionConnection ridePositionConnectionInstance = new RidePositionConnection();
        assertTrue(ridePositionConnectionInstance.createRequestUrl(ridePositionSearch)
                .matches("https://maps\\.googleapis\\.com/maps/api/place/nearbysearch/json\\?location=28\\.4177,-81\\.5812&rankby=distance&keyword=[^&]+&key=.+"));
    }
}
