package com.genius.jpa.domain;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MemberTest {

	private static String ID = "ID1";

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	@BeforeAll
	public static void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("genius");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	@Test
	@Order(1)
	@DisplayName("Member 영속성 저장 및 확인 테스트")
	public void memberPersistTest() {
		Member member = new Member();
		member.setId(ID);
		member.setPassword("1234");
		member.setName("Genius");
		member.setEmail("genius@genius.com");
		member.setAge(10);
		member.setTeam(new Team());
		entityManager.persist(member);
		Member findMember = entityManager.find(Member.class, ID);
		Assertions.assertEquals(member.getAge(), findMember.getAge());
	}
}