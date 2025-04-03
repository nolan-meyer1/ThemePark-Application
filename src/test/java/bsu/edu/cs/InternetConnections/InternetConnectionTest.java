package bsu.edu.cs.InternetConnections;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static bsu.edu.cs.InternetConnections.InternetConnection.loadApiKey;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InternetConnectionTest {

    @Test
    public void loadApiKeyTest() throws IOException {
        assertNotNull(loadApiKey("google"));
    }
}
