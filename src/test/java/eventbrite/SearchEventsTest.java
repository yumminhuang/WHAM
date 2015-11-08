package eventbrite;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import eventbrite.exception.RequestException;
import eventbrite.operation.SearchRequest;

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
        request.setKeywords(new String[] { "Rock", "Music" });
        assertNotNull(client.search(request));
    }
}
