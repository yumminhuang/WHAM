package wham;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ResourceBase {

    protected static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WHAM");
        return emf.createEntityManager();
    }

}
