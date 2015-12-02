package wham.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppEntityManager {
	
	private static EntityManagerFactory emf;

	private AppEntityManager() {
	}

	public static synchronized void initEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("WHAM");
		}
	}

	public static synchronized EntityManagerFactory getEntityManagerFactory() {
		initEntityManagerFactory();
		return emf;
	}

	public static void closeEntityManagerFactory() {
		if (emf != null) {
			emf.close();
		}
	}

	public static EntityManager createEntityManager() {
		if (emf == null) {
			System.err.println("Context is not initialized yet.");
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return emf.createEntityManager();
	}
}
