package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RideParserTest {

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("magicKingdom.json");
        assert sampleFile != null;
        RideParser rideParser = new RideParser(new ApiInputStream(sampleFile));
        assertEquals("$..rides[*]", rideParser.getQuery());
    }

    @Test
    public void convertDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("magicKingdom.json");
        assert sampleFile != null;
        RideParser rideParser = new RideParser(new ApiInputStream(sampleFile));
        JSONArray parsedRevisions = rideParser.extractData(new ByteArrayInputStream(rideParser.inputStreamInstance.inputStream));
        List<Ride> convertedList = rideParser.convertData(parsedRevisions);
        assertEquals(44, convertedList.size());
    }

    @Test
    public void extractDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("magicKingdom.json");
        assert sampleFile != null;
        ParkParser parkParser = new ParkParser(new ApiInputStream(sampleFile));
        JSONArray parsedRevisions = parkParser.extractData(new ByteArrayInputStream(parkParser.inputStreamInstance.inputStream));
        assertNotNull(parsedRevisions);
    }

    @Test
    public void extractDataTestNoItems() throws openInputStreamException, IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("magicKingdom.json");
        assert sampleFile != null;
        ParkParser parkParser = new ParkParser(new ApiInputStream(sampleFile));

        //Closed to test file exception
        sampleFile.close();

        try {
            parkParser.extractData(sampleFile);
            fail("Expected noItemFoundException");
        } catch (noItemFoundException e) {
            assertEquals("Could not find the item you were looking for!", e.getMessage());
        }

    }

    @Test
    public void parseTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("magicKingdom.json");
        assert sampleFile != null;
        RideParser rideParser = new RideParser(new ApiInputStream(sampleFile));
        assertEquals(44,rideParser.parse().size());
    }
}
