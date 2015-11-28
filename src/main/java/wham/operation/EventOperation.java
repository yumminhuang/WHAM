package wham.operation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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

    /**
     * Check whether EventId exists in database
     *
     * @param EventId
     * @return
     */
    public boolean eventExist(String eventId) {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.eId = :eventId", Event.class);
        List<Event> results = query.setParameter("eventId", eventId).getResultList();
        return results.size() == 1;
    }
}
