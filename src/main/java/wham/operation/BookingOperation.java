package wham.operation;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import wham.model.Event;

public class BookingOperation {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("WHAM");

    public boolean save(String email, double eventID) {
        return false;
    }

    public List<Event> getAllBookingByUser(String email) {
        return null;
    }

    public boolean review(String email, double eventID, String text) {
        return false;
    }

    public boolean like(String email, double like) {
        return false;
    }

    public boolean dislike(String email, double dislike) {
        return false;
    }

    public boolean dislike(String email, double eventID, double rate) {
        return false;
    }

    public double averageRate(double eventID) {
        return 0.0;
    }
}
