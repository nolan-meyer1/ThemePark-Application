package bsu.edu.cs.Parsers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class SampleFileAccessTest {

    @Test
    public void testAccessToParks(){
        String jsonData = readSampleFileAsString("parks.json");
        Assertions.assertNotNull(jsonData);
    }

    @Test
    public void testAccessRides(){
        String jsonData = readSampleFileAsString("magicKingdom.json");
        Assertions.assertNotNull(jsonData);
    }

    @Test
    public void testCountParks(){
        String jsonData = readSampleFileAsString("parks.json");
        JSONArray parks = getParksFromJson(jsonData);
        Assertions.assertEquals(132, parks.size());
    }

    @Test
    public void testCountRides(){
        String jsonData = readSampleFileAsString("magicKingdom.json");
        JSONArray rides = getRidesFromJson(jsonData);
        Assertions.assertEquals(44, rides.size());
    }

    private String readSampleFileAsString(String fileName){
        String output = "";
        try{
            try (InputStream sampleFile = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(fileName)) {
                output = new String(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
            }
        }catch (IOException | NullPointerException e) {
            System.err.println("Couldn't find sample file!");
        }
        return output;
    }

    private JSONArray getParksFromJson(String jsonData) {
        return JsonPath.read(jsonData, "$..parks[*]");
    }

    private JSONArray getRidesFromJson(String jsonData) {
        return JsonPath.read(jsonData, "$..rides[*]");
    }

}
