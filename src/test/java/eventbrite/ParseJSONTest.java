package eventbrite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import eventbrite.model.Organizer;
import eventbrite.model.Venue;
import eventbrite.operation.EventsSearchResult;

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
        assertNotNull(v.serialize());
    }

    @Test
    public void testParseOrganizer() {
        String json = loadJSONFile("Organizer.json");
        Organizer o = new Organizer();
        o.deserialize(json);
        assertNotNull(o.serialize());
    }

    @Test
    public void testParseEventsSearch() {
        String json = loadJSONFile("SearchResult.json");
        EventsSearchResult result = new EventsSearchResult(json);
        assertEquals(result.getEvents().size(), 5);
        assertEquals(result.nextPage(), -1);
    }

}
