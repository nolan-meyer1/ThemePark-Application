package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherParserTest {

    @Test
    public void convertDataTestGetName() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParser = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals("Clear",weatherParser.parse().getName());
    }

    @Test
    public void convertDataTestGetId() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParser = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(800,weatherParser.parse().getId());
    }

    @Test
    public void convertDataTestGetFeelsLike() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParser = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(79.75,weatherParser.parse().getFeels_like());
    }

    @Test
    public void convertDataTestGetTemp() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParser = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(80.2,weatherParser.parse().getTemperature());
    }

    @Test
    public void convertDataTestGetWindSpeed() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParser = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(3,weatherParser.parse().getWindSpeed());
    }

    @Test
    public void convertDataTestGetHumidity() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParser = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(36,weatherParser.parse().getHumidity());
    }

    @Test
    public void convertDataTestGetID() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParser = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals("01d",weatherParser.parse().getIconID());
    }

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParser = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals("$..*",weatherParser.getQuery());
    }

}