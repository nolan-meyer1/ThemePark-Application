package bsu.edu.cs.Parsers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class ParksAccessTest {
    @Test
    public void testAccessToJsonFile(){
        String jsonData = readSampleFileAsString();
        Assertions.assertNotNull(jsonData);
    }

    @Test
    public void testCountRevisionsWithJsonPath(){
        String jsonData = readSampleFileAsString();
        JSONArray parks = getParksFromJson(jsonData);
        Assertions.assertEquals(132, parks.size());
    }

    private String readSampleFileAsString(){
        String output = "";
        try{
            try (InputStream sampleFile = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("parks.json")) {
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
}
