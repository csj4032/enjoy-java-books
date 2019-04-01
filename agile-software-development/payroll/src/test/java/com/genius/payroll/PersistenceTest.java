package com.genius.payroll;

import com.genius.payroll.domain.Employee;
import com.genius.payroll.domain.Member;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceTest {

	@Test
	public void databaseConnectionTest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Payroll");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.find(Member.class, 1L);
		entityManager.find(Employee.class, 1L);
	}
}