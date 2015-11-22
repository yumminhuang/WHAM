package wham.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BookingPK id;

	private String comment;

	private byte like;

	private int rating;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="eId")
	private Event event;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uId")
	private User user;

	public Booking() {
	}

	public BookingPK getId() {
		return this.id;
	}

	public void setId(BookingPK id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte getLike() {
		return this.like;
	}

	public void setLike(byte like) {
		this.like = like;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}