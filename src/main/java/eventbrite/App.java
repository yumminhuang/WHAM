package eventbrite;

import eventbrite.operation.EventsSearchResult;
import eventbrite.operation.SearchRequest;

/**
 * @Author: yummin Date: 15/11/6
 */
public class App {

    private static void searchEvent(EventbriteClient client) throws Exception {
        SearchRequest request = new SearchRequest();
        request.setCategories("Music");
        request.setKeywords(new String[] { "Rock" });
        request.setVenue_city("Boston");
        EventsSearchResult events = client.search(request);
    }

    public static void main(String[] args) {
        EventbriteClient client = new EventbriteClient(new Credentials());
    }
}
