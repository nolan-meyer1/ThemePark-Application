package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Park;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceIDConnectionTest {

    @Test
    public void createURLRequestTest(){
        Park testPark = new Park(26,"Disney Magic Kingdom","United States","North America","28.4177","-81.5812","America/New_York");
        PlaceIDConnection placeIDConnection = new PlaceIDConnection();
        assertEquals("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.4177,-81.5812&rankby=distance&type=amusement_park&keyword=Disney%20Magic%20Kingdom&key=AIzaSyD6cucMnd_mVIEq-3gdhJhrJqEFXAXjBeU",
                placeIDConnection.createRequestUrl(testPark));
    }

}
