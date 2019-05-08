package com.genius.introduction;

import org.junit.jupiter.api.*;

import java.util.*;

@DisplayName("HashSet")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HashSetTest {

	private static Set<Article> hashSet = new HashSet();
	private static Set<Article> keySet = new HashSet();
	// Comparable
	private static TreeSet<Article> sortedSet = new TreeSet<>();

	@BeforeAll
	public static void setUp() {
		for (int i = 0; i < 100; i++) {
			keySet.add(Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
			sortedSet.add(Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}
	}

	@Test
	@Order(1)
	@DisplayName("중복 확인 해시코드")
	public void uniqueOfHashCode() {
		Article article = new Article(0, "제목", "내용");
		Article article1 = new Article(0, "제목1", "내용1");
		article.equals(article1);
		hashSet.add(article);
		hashSet.add(article1);
		Assertions.assertEquals(1, hashSet.size());
	}

	@Test
	@Order(2)
	@DisplayName("중복 확인")
	public void unique() {
		List<String> list = List.of("A", "B", "C", "D", "A");
		Set<String> set = new HashSet(list);
		Assertions.assertEquals(5, list.size());
		Assertions.assertEquals(4, set.size());
	}
}
