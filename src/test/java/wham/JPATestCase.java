package wham;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import wham.config.AppEntityManager;

public class JPATestCase {

    @BeforeClass
    public static void setUpClass() {
        AppEntityManager.initEntityManagerFactory();
    }

    @AfterClass
    public static void tearDownClass() {
        AppEntityManager.closeEntityManagerFactory();
    }

}
