package com.genius.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@DisplayName("LinkedList")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LinkedListTest {

	private static List<Article> arrayList;
	private static List<Article> linkedList;

	@BeforeAll
	public static void setUp() {
		arrayList = new ArrayList<>();
		linkedList = new LinkedList();
		for (int i = 0; i < 10; i++) {
			linkedList.add(Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}
	}

	@Test
	@Order(1)
	@DisplayName("사이즈 확인")
	public void size() {
		linkedList.add(Article.builder().id(10).subject("제목" + 10).contents("내용" + 10).build());
		Assertions.assertEquals(11, linkedList.size());
	}

	@Test
	@Order(2)
	@DisplayName("순차적 입력")
	public void add() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 8000000; i++) {
			arrayList.add(i, Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}
		long endTime = System.currentTimeMillis();
		long arrayListTime = endTime - startTime;

		startTime = System.currentTimeMillis();
		for (int i = 0; i < 8000000; i++) {
			linkedList.add(i, Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}
		endTime = System.currentTimeMillis();
		long linedListTime = endTime - startTime;

		log.info("ArrayList : {} , LinkedList : {}", arrayListTime, linedListTime);
		Assertions.assertTrue(linedListTime > arrayListTime);
	}

	@Test
	@Order(3)
	@DisplayName("중간 입력")
	public void add2() {
		arrayList.clear();
		linkedList.clear();
		for (int i = 0; i < 1000; i++) {
			arrayList.add(i, Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}
		for (int i = 0; i < 1000; i++) {
			linkedList.add(i, Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 200000; i++) {
			arrayList.add(100, Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}
		long endTime = System.currentTimeMillis();
		long arrayListTime = endTime - startTime;

		startTime = System.currentTimeMillis();
		for (int i = 0; i < 200000; i++) {
			linkedList.add(100, Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}

		endTime = System.currentTimeMillis();
		long linedListTime = endTime - startTime;

		log.info("ArrayList : {} , LinkedList : {}", arrayListTime, linedListTime);
		Assertions.assertTrue(linedListTime < arrayListTime);
	}
}
