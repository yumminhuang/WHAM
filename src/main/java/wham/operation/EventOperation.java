package wham.operation;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import wham.model.Event;

public class EventOperation {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("WHAM");

    public boolean createEvent(Event event) {
        return false;
    }

}
