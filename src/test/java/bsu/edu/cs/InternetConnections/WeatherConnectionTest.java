package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherConnectionTest {

    @Test
    public void createURLRequestTest() {
        WeatherConnection weatherConnectionInstance = new WeatherConnection();

        assertEquals("https://api.openweathermap.org/data/2.5/weather?lat=28.417663&lon=-81.581212&appid=1b0f4529f6b03a65738f138508968d2b&units=imperial",
                weatherConnectionInstance.createRequestUrl(new Coordinates("28.417663","-81.581212")));
    }
}
