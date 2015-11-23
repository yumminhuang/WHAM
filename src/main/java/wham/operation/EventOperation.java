package wham.operation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import wham.model.Event;

public class EventOperation {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("WHAM");

    /**
     * Insert new Event object into database
     * @param event
     */
    public void createEvent(Event event) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
        em.close();
    }
}
