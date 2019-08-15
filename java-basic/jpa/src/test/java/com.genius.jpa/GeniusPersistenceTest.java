package com.genius.jpa;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GeniusPersistenceTest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	@BeforeAll
	public static void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("genius");
	}

	@Test
	@Order(1)
	@DisplayName("EntityManager 생성 확인")
	public void entityManagerTest() {
		entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager);
	}

	@Test
	@Order(2)
	@DisplayName("EntityTransaction 생성 확인")
	public void entityTransactionTest() {
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		assertNotNull(entityTransaction);
	}
}