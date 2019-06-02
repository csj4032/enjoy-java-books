package com.genius.collection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.*;

@Slf4j
@DisplayName("Deque")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DequeTest {

	private static Deque<Article> arrayDeque = new ArrayDeque<>();

	@BeforeAll
	public static void setUp() {
		for (int i = 0; i < 5; i++) {
			arrayDeque.add(Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}
	}

	@Test
	public void dequePushLast() {
		arrayDeque.push(Article.builder().id(6).subject("제목" + 6).contents("내용" + 6).build());
		Assertions.assertEquals(6, arrayDeque.getFirst().getId());
		arrayDeque.addLast(Article.builder().id(7).subject("제목" + 7).contents("내용" + 7).build());
		Assertions.assertEquals(7, arrayDeque.getLast().getId());
	}
}
