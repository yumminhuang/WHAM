package wham.operation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import wham.model.User;

public class UserOperation {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("WHAM");

    public boolean createUser(User newUser) {
        // TODO: return false if any exception occurs
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(newUser);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public User getUser(String email, String password) {
        return null;
    }

    public boolean updateUser(String email, User user) {
        return false;
    }

}
