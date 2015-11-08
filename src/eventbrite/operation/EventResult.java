package eventbrite.operation;

import eventbrite.model.Event;

/**
 * Represents the result from a request to the GET /events/:id/ API.
 *
 * @Author: yummin
 * Date: 15/11/6
 */
public class EventResult extends BaseResult {

    private Event event;

    /**
     * Gets the Event for this EventResult.
     *
     * @return An instance of Event describing the Event for this EventResult.
     */
    public Event getEvent() {
        return event;
    }

}
