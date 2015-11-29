package wham.operation;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import wham.config.AppEntityManager;
import wham.model.User;

public class UserOperation {

    private EntityManager em;

    public UserOperation() {
    	this.em = AppEntityManager.createEntityManager();
    }

    /**
     * Insert new User into database
     * @param user User object
     */
    public void createUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Get User object with email and password
     * @param email
     * @param password
     * @return User object if email and password are valid; Otherwise, return null.
     */
    public User getUser(String email, String password) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.emailId = :email", User.class);
        User user = query.setParameter("email", email).getSingleResult();
        em.close();
        if (null != user.getPassword() && user.getPassword().equals(password))
            return user;
        return null;
    }
    
    /**
     * Update User with the given email id. Put attributes need to be updated in
     * modifiedAttrs
     * @param email
     * @param modifiedAttrs
     */
    public void updateUser(String email, Map<String, String> modifiedAttrs) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.emailId = :email", User.class);
        User user = query.setParameter("email", email).getSingleResult();
        em.getTransaction().begin();
        for (Map.Entry<String, String> entry : modifiedAttrs.entrySet()) {
            if (entry.getKey().equals("address"))
                user.setAddress(entry.getValue());
            else if (entry.getKey().equals("city"))
                user.setCity(entry.getKey());
            else if (entry.getKey().equals("fName"))
                user.setFName(entry.getValue());
            else if (entry.getKey().equals("lName"))
                user.setLName(entry.getValue());
            else if (entry.getKey().equals("password"))
                user.setPassword(entry.getValue());
            else if (entry.getKey().equals("phone"))
                user.setPhone(entry.getValue());
            else if (entry.getKey().equals("zipCode"))
                user.setZipCode(entry.getValue());
        }
        em.getTransaction().commit();
        em.close();
    }

}
