package eventbrite.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

/**
 * @see: https://www.eventbrite.com/developer/v3/formats/event/
 * @Author: yummin
 * Date: 15/11/6
 */
public class Event {
    private long id;
    private String name;
    private String url;
    // Note: description is optional
    private String description;
    private DateTime startTime;
    private DateTime endTime;
    private int capacity;
    private String status;

    // Expansion attributes
    private long venueID;
    private long organizerID;
    private long categoryID;
    private long subCategoryID;

    /*
     * These two attributes only exist when adding expend in search request
     */
    private Double longitude;
    private Double latitude;

    public Event() {

    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public long getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(long subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event: " + this.name;
    }

    public String serialize() {
        return this.extractAttributes().toString();
    }

    public JSONObject extractAttributes() {
        JSONObject e = new JSONObject();
        e.put("id", id);
        e.put("name", name);
        e.put("url", url);
        e.put("description", description);
        e.put("start", startTime.toString());
        e.put("end", endTime.toString());
        e.put("capacity", capacity);
        e.put("status", status);
        e.put("venueID", venueID);
        e.put("organizerID", organizerID);
        e.put("categoryID", categoryID);
        e.put("subCategoryID", subCategoryID);
        e.put("longitude", longitude);
        e.put("latitude", latitude);
        return e;
    }

    public Event deserialize(JSONObject e) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        this.id = e.getLong("id");
        this.name = e.getJSONObject("name").getString("text");
        this.url = e.getString("url");
        this.capacity = e.getInt("capacity");
        this.organizerID = e.getLong("organizer_id");
        this.startTime = formatter.parseDateTime(e.getJSONObject("start").getString("local"));
        this.endTime = formatter.parseDateTime(e.getJSONObject("end").getString("local"));
        this.status = e.getString("status");
        // Following attributes may be null
        if (!e.isNull("venue_id"))
            this.venueID = e.getLong("venue_id");
        if (!e.isNull("category_id"))
            this.categoryID = e.getLong("category_id");
        if (!e.isNull("subcategory_id")) {
            this.subCategoryID = e.getLong("subcategory_id");
        }
        if (!e.getJSONObject("description").isNull("text"))
            this.setDescription(e.getJSONObject("description").getString("text"));
        if (e.has("venue") && !e.isNull("venue")) {
            this.longitude = e.getJSONObject("venue").getJSONObject("address").getDouble("longitude");
            this.latitude = e.getJSONObject("venue").getJSONObject("address").getDouble("latitude");
        }
        return this;
    }

    public Event deserialize(String json) {
        JSONObject e = new JSONObject(json);
        return this.deserialize(e);
    }

}
