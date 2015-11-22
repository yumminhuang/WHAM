package wham.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the booking database table.
 * 
 */
@Embeddable
public class BookingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int uId;

	@Column(insertable=false, updatable=false)
	private double eId;

	public BookingPK() {
	}
	public int getUId() {
		return this.uId;
	}
	public void setUId(int uId) {
		this.uId = uId;
	}
	public double getEId() {
		return this.eId;
	}
	public void setEId(double eId) {
		this.eId = eId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BookingPK)) {
			return false;
		}
		BookingPK castOther = (BookingPK)other;
		return 
			(this.uId == castOther.uId)
			&& (this.eId == castOther.eId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.uId;
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.eId) ^ (java.lang.Double.doubleToLongBits(this.eId) >>> 32)));
		
		return hash;
	}
}