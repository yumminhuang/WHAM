package eventbrite.model;

import org.joda.time.DateTime;

/**
 * @see: https://www.eventbrite.com/developer/v3/formats/event/
 * @Author: yummin
 * Date: 15/11/6
 */
public class Event {
    private long id;
    private String name;
    private String url;
    private DateTime startTime;
    private DateTime endTime;
    private int capacity;
    private EventStatus status;

    private long venueID;
    private long organizerID;
    private long CategoryID;
    private long SubCategoryID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public long getVenueID() {
        return venueID;
    }

    public void setVenueID(long venueID) {
        this.venueID = venueID;
    }

    public long getOrganizerID() {
        return organizerID;
    }

    public void setOrganizerID(long organizerID) {
        this.organizerID = organizerID;
    }

    public long getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(long categoryID) {
        CategoryID = categoryID;
    }

    public long getSubCategoryID() {
        return SubCategoryID;
    }

    public void setSubCategoryID(long subCategoryID) {
        SubCategoryID = subCategoryID;
    }

    @Override
    public String toString() {
        return "Event: " + this.name;
    }

}
