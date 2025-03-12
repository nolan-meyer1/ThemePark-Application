package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RideParserTest {

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        RideParser rideParser = new RideParser(new ApiInputStream(sampleFile));
        assertEquals("$..rides[*]",rideParser.getQuery());
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

}
