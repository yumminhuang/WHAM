package eventbrite;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import eventbrite.exception.RequestException;
import eventbrite.operation.EventRequest;
import eventbrite.operation.EventsSearchResult;
import eventbrite.operation.OrganizerRequest;
import eventbrite.operation.SearchRequest;
import eventbrite.operation.VenueRequest;

/**
 * Unit Tests for the EventbriteClient.search wrapper of event_search.
 *
 * @Author: yummin
 * Date: 15/11/6
 */
public final class SearchEventsTest extends TestBase {

    private EventbriteClient client;

    @Before
    public void setUp() {
        client = new EventbriteClient(getCredentials());
    }

    @Test
    public void testEventSearch() throws RequestException {
        SearchRequest request = new SearchRequest();
        request.setVenue_city("Boston");
        request.setCategories("Music");
        request.setKeywords(new String[] { "Rock"});
        request.setExpand("venue");
        EventsSearchResult events = client.search(request);
        assertNotNull(events.getEvents());
        assertNotNull(events.serialize().contains("latitude"));
    }

    @Test
    public void testGetVenue() throws RequestException {
        VenueRequest request = new VenueRequest();
        request.setId(11892073l);
        assertNotNull(client.get(request));
    }

    @Test
    public void testGetOrganizer() throws RequestException {
        OrganizerRequest request = new OrganizerRequest();
        request.setId(2937401881l);
        assertNotNull(client.get(request));
    }

    @Test
    public void testGetEvent() throws RequestException {
        EventRequest request = new EventRequest();
        request.setId(18986075864l);
        assertNotNull(client.get(request));
    }
}
