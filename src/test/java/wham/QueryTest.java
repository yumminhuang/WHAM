package wham;

import org.junit.Test;

import junit.framework.TestCase;
import wham.config.AppEntityManager;
import wham.model.User;
import wham.operation.UserOperation;

public class QueryTest extends TestCase {

    @Override
    public void setUp() {
        AppEntityManager.initEntityManagerFactory();
    }

    @Test
    public void testGetUserByEmail() {
        UserOperation operation = new UserOperation();
        User u = operation.getUser("abc@edf.com", "password");
        assertEquals(2, u.getUId());
    }

    @Override
    public void tearDown() {
        AppEntityManager.closeEntityManagerFactory();
    }

}
