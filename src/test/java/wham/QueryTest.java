package wham;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import wham.model.User;
import wham.operation.UserOperation;

public class QueryTest {

    private EntityManager em;

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WHAM");
        this.em = emf.createEntityManager();
    }

//    @Test
//    public void testGetUserByEmail() {
//        UserOperation operation = new UserOperation(em);
//        User u = operation.getUser("abc@edf.com", "password");
//        assertEquals(2, u.getUId());
//    }

}
