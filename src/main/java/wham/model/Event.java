package wham.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private double eId;

	@Column(name="address_1")
	private String address1;

	@Column(name="address_2")
	private String address2;

	private int capacity;

	private double categoryID;

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

	private double organizerID;

	@Column(name="postal_code")
	private String postalCode;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	private String status;

	private double subCategoryID;

	private String url;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="event")
	private List<Booking> bookings;

	public Event() {
	}

	public double getEId() {
		return this.eId;
	}

	public void setEId(double eId) {
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

	public double getCategoryID() {
		return this.categoryID;
	}

	public void setCategoryID(double categoryID) {
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

	public double getOrganizerID() {
		return this.organizerID;
	}

	public void setOrganizerID(double organizerID) {
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

	public double getSubCategoryID() {
		return this.subCategoryID;
	}

	public void setSubCategoryID(double subCategoryID) {
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

}