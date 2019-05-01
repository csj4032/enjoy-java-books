package com.genius.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.*;

@Slf4j
@DisplayName("Queue")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QueueTest {

	private static Queue<Article> priorityQueue = new PriorityQueue<>();

	@BeforeAll
	public static void setUp() {
		for (int i = 0; i < 100; i++) {
			priorityQueue.add(Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}
	}


}