package com.genius.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Slf4j
@DisplayName("PriorityQueue")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PriorityQueueTest {

	private static Queue<Article> priorityQueueId = new PriorityQueue<>(Comparator.comparing(Article::getId));
	private static Queue<Article> priorityQueueSubject = new PriorityQueue<>(Comparator.comparing(Article::getSubject));
	private static Queue<Article> priorityQueueContents = new PriorityQueue<>(Comparator.comparing(article -> article.getContents(), String::compareTo));

	@BeforeAll
	public static void setUp() {
		for (int i = 0; i < 5; i++) {
			priorityQueueId.add(Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
			priorityQueueSubject.add(Article.builder().id(i).subject("제목" + (4 - i)).contents("내용" + i).build());
			priorityQueueContents.add(Article.builder().id(i).subject("제목" + (4 - i)).contents("내용" + i).build());
		}
	}

	@Test
	public void sort() {
		log.info("priorityQueueId : {} ", priorityQueueId);
		log.info("priorityQueueSubject : {}", priorityQueueSubject);
		log.info("priorityQueueSubject : {}", priorityQueueContents);
	}
}
