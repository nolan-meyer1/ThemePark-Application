package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherParserTest {

    @Test
    public void conversionTestGetName() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals("Clear",weatherParserInstance.parse().getName());
    }

    @Test
    public void conversionTestGetId() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(800,weatherParserInstance.parse().getId());
    }

    @Test
    public void conversionTestGetFeelsLike() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(79.75,weatherParserInstance.parse().getFeels_like());
    }

    @Test
    public void conversionTestGetTemp() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(80.2,weatherParserInstance.parse().getTemperature());
    }

    @Test
    public void conversionTestGetWindSpeed() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(3,weatherParserInstance.parse().getWindSpeed());
    }

    @Test
    public void conversionTestGetHumidity() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(36,weatherParserInstance.parse().getHumidity());
    }

    @Test
    public void conversionTestGetID() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals("01d",weatherParserInstance.parse().getIconID());
    }

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("WeatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals("$..*",weatherParserInstance.getQuery());
    }

}