package wham.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;


/**
 * The persistent class for the event database table.
 *
 */
@Entity
@Table(name="event")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String eId;

    @Column(name="address_1")
    private String address1;

    @Column(name="address_2")
    private String address2;

    private int capacity;

    private int categoryID;

    private String city;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    private double latitude;

    @Column(name="logo_url")
    private String logoUrl;

    private double longitude;

    private String name;

    private int organizerID;

    @Column(name="postal_code")
    private String postalCode;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    private String status;

    private int subCategoryID;

    private String url;

    //bi-directional many-to-one association to Booking
    @OneToMany(mappedBy="event")
    private List<Booking> bookings;

    public Event() {
    }

    public String getEId() {
        return this.eId;
    }

    public void setEId(String eId) {
        this.eId = eId;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCategoryID() {
        return this.categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrganizerID() {
        return this.organizerID;
    }

    public void setOrganizerID(int organizerID) {
        this.organizerID = organizerID;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSubCategoryID() {
        return this.subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Booking addBooking(Booking booking) {
        getBookings().add(booking);
        booking.setEvent(this);

        return booking;
    }

    public Booking removeBooking(Booking booking) {
        getBookings().remove(booking);
        booking.setEvent(null);

        return booking;
    }

    public Event deserialize(JSONObject e) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
        this.eId = String.valueOf(e.getLong("id"));
        this.name = e.getString("name");
        this.url = e.getString("url");
        this.capacity = e.getInt("capacity");
        this.startTime = formatter.parseDateTime(e.getString("start")).toDate();
        this.endTime = formatter.parseDateTime(e.getString("end")).toDate();
        this.status = e.getString("status");
        if (!e.isNull("category_id"))
            this.categoryID = e.getInt("categoryID");
        if (!e.isNull("subcategory_id")) {
            this.subCategoryID = e.getInt("subCategoryID");
        }
        this.description = e.getString("description");
        if (e.has("logo") && !e.isNull("logo"))
            this.logoUrl = e.getJSONObject("logo").getString("url");
        if(!e.isNull(address1))
            this.address1 = e.getString("address_1");
        if(!e.isNull(address2))
            this.address2 = e.getString("address_2");
        this.longitude = e.getDouble("longitude");
        this.latitude = e.getDouble("latitude");
        this.city = e.getString("city");
        this.postalCode = e.getString("postal_code");

        return this;
    }

    public Event deserialize(String json) {
        JSONObject e = new JSONObject(json);
        return this.deserialize(e);
    }

}