package wham.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userpreference database table.
 * 
 */
@Entity
@Table(name="userpreference")
@NamedQuery(name="Userpreference.findAll", query="SELECT u FROM Userpreference u")
public class Userpreference implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserpreferencePK id;

	private int categoryId;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uId")
	private User user;

	public Userpreference() {
	}

	public UserpreferencePK getId() {
		return this.id;
	}

	public void setId(UserpreferencePK id) {
		this.id = id;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}