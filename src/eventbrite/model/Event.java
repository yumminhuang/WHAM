package eventbrite.model;

import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import org.joda.time.DateTime;

/**
 * @see: https://www.eventbrite.com/developer/v3/formats/event/
 * @Author: yummin
 * Date: 15/11/6
 */
public class Event {
    @SerializedName("id")
    private long id;

    private MultipartText name;
    private MultipartText description;
    @SerializedName("url")
    private String url;
    private DatetimeTZ start;
    private DatetimeTZ end;
    @SerializedName("created")
    private DateTime created;
    @SerializedName("changed")
    private DateTime changed;
    @SerializedName("capacity")
    private int capacity;
    @SerializedName("status")
    private String status;
    @SerializedName("currency")
    private String currency;
    @SerializedName("online_event")
    private boolean onlineEvent;

    private long venueID;
    private long organizerID;
    private long categoryID;

    public static Type getType() {
        return TypeToken.get(Event.class).getType();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public DateTime getChanged() {
        return changed;
    }

    public void setChanged(DateTime changed) {
        this.changed = changed;
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

    public boolean isOnlineEvent() {
        return onlineEvent;
    }

    public void setOnlineEvent(boolean onlineEvent) {
        this.onlineEvent = onlineEvent;
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
}
