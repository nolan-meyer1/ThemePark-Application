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

public class ParkParserTest {

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        ParkParser parkParser = new ParkParser(new ApiInputStream(sampleFile));
        assertEquals("$..parks[*]",parkParser.getQuery());
    }

    @Test
    public void convertRevisionsToListTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        ParkParser parkParser = new ParkParser(new ApiInputStream(sampleFile));
        JSONArray parsedRevisions = parkParser.extractData(new ByteArrayInputStream(parkParser.inputStreamInstance.inputStream));
        List<Park> convertedList = parkParser.convertRevisionsToList(parsedRevisions);
        assertEquals(132, convertedList.size());
    }

    @Test
    public void extractDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        ParkParser parkParser = new ParkParser(new ApiInputStream(sampleFile));
        assertNotNull(parkParser.extractData(sampleFile));
    }

    @Test
    public void extractDataTestNoItemsFound() throws openInputStreamException, IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        ParkParser parkParser = new ParkParser(new ApiInputStream(sampleFile));

        //Closed the file to throw an error
        sampleFile.close();

        try {
            parkParser.extractData(sampleFile);
            fail("Expected noItemFoundExcetpion");
        }catch(noItemFoundException e){
            assertEquals("Could not find the item you were looking for!",e.getMessage());
        }
    }

    @Test
    public void parseTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        ParkParser parkParser = new ParkParser(new ApiInputStream(sampleFile));
        assertEquals(132,parkParser.parse().size());
    }

}
