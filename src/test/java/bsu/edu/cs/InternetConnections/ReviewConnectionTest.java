package bsu.edu.cs.InternetConnections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewConnectionTest {

    @Test
    public void createURLRequestTest() {
        ReviewConnection reviewConnectionInstance = new ReviewConnection();

        assertEquals("https://maps.googleapis.com/maps/api/place/details/json?place_id=ChIJgUulalN-3YgRGoTaWM2LawY&fields=name,rating,reviews&key=AIzaSyD6cucMnd_mVIEq-3gdhJhrJqEFXAXjBeU",
                reviewConnectionInstance.createRequestUrl("ChIJgUulalN-3YgRGoTaWM2LawY"));
    }

}
