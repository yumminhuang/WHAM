package eventbrite.operation;

import java.util.List;

import eventbrite.model.Event;

/**
 * Represents the result from a request to the events/search API.
 *
 * @Author: yummin Date: 15/11/6
 */

public class EventsSearchResult {
    private List<Event> events;

    /**
     * Gets a List of Event objects.
     *
     * @return A List of Event objects for this result.
     */
    public List<Event> getEvents() {
        return events;
    }

    public EventsSearchResult deserialize(String json) {
        return this;
    }

    public String serialize() {
        return "";
    }

    /**
     *
     * @return
     */
    public int nextPage() {
        return 0;
    }
}
