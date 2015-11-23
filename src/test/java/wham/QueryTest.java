package wham;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import wham.model.User;
import wham.operation.UserOperation;

public class QueryTest {

    @Test
    public void testGetUserByEmail() {
        UserOperation operation = new UserOperation();
        User u = operation.getUser("abc@edf.com", "password");
        assertEquals(2, u.getUId());
    }

}
