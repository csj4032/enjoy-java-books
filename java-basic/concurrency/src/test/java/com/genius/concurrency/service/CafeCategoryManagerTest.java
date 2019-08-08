package com.genius.concurrency.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Slf4j
@Execution(CONCURRENT)
class CafeCategoryManagerTest {

	private static CafeCategoryManager cafeCategoryManager;

	@BeforeAll
	public static void getInstance() {
		cafeCategoryManager = CafeCategoryManager.INSTANCE;
		Assertions.assertNotNull(cafeCategoryManager);
	}

	@Test
	@Disabled
	void getAll_A() throws InterruptedException {
		log.info("A");
		Assertions.assertNotNull(cafeCategoryManager.getAll());
	}

	@Test
	@Disabled
	void getAll_B() {
		log.info("B");
		Assertions.assertNotNull(cafeCategoryManager.getAll());
	}

	@RepeatedTest(10)
	void getFullName_A() {
		Assertions.assertEquals("컴퓨터/인터넷", cafeCategoryManager.getFullName("1"));
	}

	@RepeatedTest(10)
	void getFullName_B() {
		Assertions.assertEquals("컴퓨터/인터넷", cafeCategoryManager.getFullName("1"));
	}

	@RepeatedTest(10)
	void get_A() {
		Assertions.assertEquals("영화", cafeCategoryManager.get("2").catename);
	}

	@RepeatedTest(10)
	void get_B() {
		Assertions.assertEquals("영화", cafeCategoryManager.get("2").catename);
	}
}