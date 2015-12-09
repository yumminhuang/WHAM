package wham;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import wham.model.Event;
import wham.model.User;
import wham.operation.BookingOperation;
import wham.operation.EventOperation;
import wham.operation.PreferenceOperation;
import wham.operation.UserOperation;

public class QueryTest extends JPATestCase {

    @Test
    public void testGetUserByEmail() {
        UserOperation operation = new UserOperation();
        User u = operation.getUser("abc@edf.com", "password");
        assertEquals(2, u.getUId());
    }

    @Test
    public void testGetUserWithWrongPassword() {
        UserOperation operation = new UserOperation();
        assertNull(operation.getUser("abc@edf.com", "wrongpwd"));
    }

    @Test
    public void testGetUserWithNonExistEmail() {
        UserOperation operation = new UserOperation();
        assertNull(operation.getUser("no@email.com", "wrongpwd"));
    }

    @Test
    public void testCheckingEventExist() {
        EventOperation operation = new EventOperation();
        assertEquals(true, operation.eventExist("17546444889"));
    }

    @Test
    public void testQueryPreferences() {
        PreferenceOperation o = new PreferenceOperation();
        assertEquals("[3011, 10001, 16006]", o.getSubCategory("abc@edf.com").toString());
    }

    @Test
    public void testQueryBookingReviews() {
        BookingOperation bo = new BookingOperation();
        List<String> reviews = bo.getAllReviewsForEvent("17546444889");
        ArrayList<String> list = new ArrayList<String>();
        list.add("good");
        assertEquals(list, reviews);
    }

    @Test
    public void testQueryBookingRates() {
        BookingOperation bo = new BookingOperation();
        assertEquals(4.0, bo.averageRate("17546444889"), 0.01);
    }

    @Test
    public void testQueryBookingHistory() {
        BookingOperation bo = new BookingOperation();
        List<String> list = new ArrayList<String>();
        for (Event e : bo.getAllBookingByUser("abc@edf.com"))
            list.add(e.getEId());
        assertEquals("17546444889", list.get(0));
        assertEquals("18941183590", list.get(1));
    }

}
