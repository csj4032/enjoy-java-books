package com.genius.payroll.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

	private static final String PERSISTENCE_UNIT_NAME = "PAYROLL";
	private static EntityManagerFactory entityManagerFactory;

	private PersistenceManager() {

	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			synchronized (PersistenceManager.class){
				entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			}
		}
		return entityManagerFactory;
	}

	public static void shutdown() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
}
