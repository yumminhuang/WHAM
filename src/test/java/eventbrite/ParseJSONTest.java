package eventbrite;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import eventbrite.model.Organizer;
import eventbrite.model.Venue;

public class ParseJSONTest {

    private String loadJSONFile(String file) {
        String jsonData = null;
        try {
            jsonData = new String(Files.readAllBytes(Paths.get(getClass().getResource("/" + file).toURI())));
        } catch (IOException e) {
            jsonData = "";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    @Test
    public void testParseVenue() {
        String json = loadJSONFile("Venue.json");
        Venue v = new Venue();
        v.deserialize(json);
        assertEquals(v.serialize(),
                "{\"id\":11768139,\"name\":\"Sinclair-Music Hall\",\"address1\":\"52 Church Street\",\"longitude\":\"-71.12059299999999\",\"latitude\":\"42.373975\",\"city\":\"Cambridge\"}");
    }

    @Test
    public void testParseOrganizer() {
        String json = loadJSONFile("Organizer.json");
        Organizer o = new Organizer();
        o.deserialize(json);
    }

}
