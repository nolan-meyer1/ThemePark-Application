package bsu.edu.cs.InternetConnections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RideConnectionTest {

    @Test
    public void createURLRequestTest() {
        RideConnection rideConnection = new RideConnection();
        Integer searchItem = 23;

        assertEquals("https://queue-times.com/parks/" + searchItem + "/queue_times.json",
                rideConnection.createRequestUrl(searchItem));
    }
}
