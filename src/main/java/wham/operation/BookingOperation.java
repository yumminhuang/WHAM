package wham.operation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import wham.config.AppEntityManager;
import wham.model.Booking;
import wham.model.BookingPK;
import wham.model.Event;
import wham.model.User;

public class BookingOperation {

    private EntityManager em;

    public BookingOperation() {
    	 this.em = AppEntityManager.createEntityManager();
    }

    /**
     * Book an event
     * @param email
     * @param eventID
     */
    public void save(String email, String eventId) {
        // Find user object
        TypedQuery<User> query1 = em.createQuery("SELECT u FROM User u WHERE u.emailId = :email", User.class);
        User user = query1.setParameter("email", email).getSingleResult();
        // Find event object
        TypedQuery<Event> query2 = em.createQuery("SELECT e FROM Event e WHERE e.eId = :eventId", Event.class);
        Event event = query2.setParameter("eventId", eventId).getSingleResult();
        Booking b = new Booking();
        BookingPK bpk = new BookingPK();
        bpk.setUId(user.getUId());
        bpk.setEId(eventId);
        b.setId(bpk);
        b.setUser(user);
        b.setEvent(event);
        // Commit save
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * List all booked events for a user
     * @param email
     * @return
     */
    public List<Event> getAllBookingByUser(String email) {
        TypedQuery<Event> query = em.createQuery("SELECT b.event FROM Booking b WHERE b.user.emailId = :email",
                Event.class);
        List<Event> results = query.setParameter("email", email).getResultList();
        em.close();
        return results;
    }

    /**
     * Add a user review for the event
     * @param email
     * @param eventId
     * @param text
     */
    public void review(String email, String eventId, String text) {
        TypedQuery<Booking> query = em.createQuery(
                "SELECT b FROM Booking b WHERE b.event.eId = :eventId AND b.user.emailId = :email", Booking.class);
        Booking b = query.setParameter("eventId", eventId).setParameter("email", email).getSingleResult();
        em.getTransaction().begin();
        b.setComment(text);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Rate an event
     * @param email
     * @param eventId
     * @param rate
     */
    public void rate(String email, String eventId, int rate) {
        TypedQuery<Booking> query = em.createQuery(
                "SELECT b FROM Booking b WHERE b.event.eId = :eventId AND b.user.emailId = :email", Booking.class);
        Booking b = query.setParameter("eventId", eventId).setParameter("email", email).getSingleResult();
        em.getTransaction().begin();
        b.setRating(rate);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Dislike a event.
     * TODO: Any better way? Default is like
     * @param email
     * @param eventId
     * @param dislike
     */
    public void dislike(String email, String eventId, boolean dislike) {
        TypedQuery<Booking> query = em.createQuery(
                "SELECT b FROM Booking b WHERE b.event.eId = :eventId AND b.user.emailId = :email", Booking.class);
        Booking b = query.setParameter("eventId", eventId).setParameter("email", email).getSingleResult();
        em.getTransaction().begin();
        b.setDislike(dislike);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Count the number of dislikes or likes
     * NOTE: default is like.
     * @param eventId
     * @param dislike
     * @return
     */
    public int countDislike(String eventId, boolean dislike) {
        int count = 0;
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.event.eId = :eventId",
                Booking.class);
        List<Booking> results = query.setParameter("eventId", eventId).getResultList();
        em.close();
        for (Booking b : results)
            if (b.getDislike() == dislike)
                count++;
        return count;
    }

    /**
     * Get average rating of the event
     * @param eventID
     * @return
     */
    public double averageRate(String eventId) {
        TypedQuery<Double> query = em.createQuery(
                "SELECT AVG(b.rating) FROM Booking b WHERE b.event.eId = :eventId AND b.rating != 0", Double.class);
        double average = query.setParameter("eventId", eventId).getSingleResult();
        em.close();
        return average;
    }

    /**
     * List all comments for an event
     * @param eventId
     * @return
     */
    public List<String> getAllReviewsForEvent(String eventId) {
        TypedQuery<String> query = em.createQuery("SELECT b.comment FROM Booking b WHERE b.event.eId = :eventId",
                String.class);
        List<String> results = query.setParameter("eventId", eventId).getResultList();
        em.close();
        return results;
    }
}
