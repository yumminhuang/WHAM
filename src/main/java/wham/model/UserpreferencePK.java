package wham.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the userpreference database table.
 * 
 */
@Embeddable
public class UserpreferencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int uId;

	private double subCategoryId;

	public UserpreferencePK() {
	}
	public int getUId() {
		return this.uId;
	}
	public void setUId(int uId) {
		this.uId = uId;
	}
	public double getSubCategoryId() {
		return this.subCategoryId;
	}
	public void setSubCategoryId(double subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserpreferencePK)) {
			return false;
		}
		UserpreferencePK castOther = (UserpreferencePK)other;
		return 
			(this.uId == castOther.uId)
			&& (this.subCategoryId == castOther.subCategoryId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.uId;
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.subCategoryId) ^ (java.lang.Double.doubleToLongBits(this.subCategoryId) >>> 32)));
		
		return hash;
	}
}