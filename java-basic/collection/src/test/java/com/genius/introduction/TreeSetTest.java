package com.genius.introduction;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.*;

@Slf4j
@DisplayName("TableSet")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TreeSetTest {

	// Comparable
	private static SortedSet<Article> sortedSet = new TreeSet<>();
	private static NavigableSet<Article> navigatorSet = new TreeSet<>();

	@BeforeAll
	public static void setUp() {
		for (int i = 0; i < 100; i++) {
			sortedSet.add(Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
			navigatorSet.add(Article.builder().id(i).subject("제목" + i).contents("내용" + i).build());
		}
	}

	@Test
	@Order(1)
	@DisplayName("SortedSet Last 메소드 확인")
	public void sortedSetLast() {
		Article last = sortedSet.last();
		Assertions.assertEquals(99, last.getId());
	}

	@Test
	@Order(2)
	@DisplayName("SortedSet SubSet 메소드 확인")
	public void sortedSubSet() {
		Article from = Article.builder().id(0).build();
		Article to = Article.builder().id(9).build();
		Set<Article> subSet = sortedSet.subSet(from, to);
		Assertions.assertEquals(9, subSet.size());
	}

	@Test
	@Order(3)
	@DisplayName("SortedSet Tail 메소드 확인")
	public void sortedTail() {
		Article from = Article.builder().id(50).build();
		Set<Article> tailSet = sortedSet.tailSet(from);
		Assertions.assertEquals(50, tailSet.size());
	}

	@Test
	@Order(4)
	@DisplayName("NavigatorSet descendingIterator 메소드 확인")
	public void navigatorDescendingIterator() {
		Iterator<Article> descendingIterator = navigatorSet.descendingIterator();
		Assertions.assertEquals(99, descendingIterator.next().getId());
	}

	@Test
	@Order(5)
	@DisplayName("NavigatorSet article >= ceiling 메소드 확인")
	public void navigatorCeiling() {
		Article article = Article.builder().id(0).build();
		Article ceiling = navigatorSet.ceiling(article);
		Assertions.assertEquals(0, ceiling.getId());
	}

	@Test
	@Order(6)
	@DisplayName("NavigatorSet article <= floor 메소드 확인")
	public void navigatorFloor() {
		Article article = Article.builder().id(99).build();
		Article floor = navigatorSet.floor(article);
		Assertions.assertEquals(99, floor.getId());
	}

	@Test
	@Order(7)
	@DisplayName("NavigatorSet article < higher 메소드 확인")
	public void navigatorHigher() {
		Article article = Article.builder().id(99).build();
		Article higher = navigatorSet.higher(article);
		Assertions.assertNull(higher);
	}

	@Test
	@Order(8)
	@DisplayName("NavigatorSet article > lower 메소드 확인")
	public void navigatorLower() {
		Article article = Article.builder().id(100).build();
		Article lower = navigatorSet.lower(article);
		Assertions.assertEquals(99, lower.getId());
	}

	@Test
	@Order(9)
	@DisplayName("NavigatorSet poolFirst 메소드 확인")
	public void navigatorPoolFirst() {
		Article first = navigatorSet.pollFirst();
		Assertions.assertEquals(0, first.getId());
		Assertions.assertEquals(99, navigatorSet.size());
	}

	@Test
	@Order(10)
	@DisplayName("NavigatorSet lower 메소드 확인")
	public void navigatorPollLast() {
		Article last = navigatorSet.pollLast();
		Assertions.assertEquals(99, last.getId());
		Assertions.assertEquals(98, navigatorSet.size());
	}

	@Test
	@Order(12)
	@DisplayName("NavigatorSet subSet 메소드 확인")
	public void navigatorSubSet() {
		Article from = Article.builder().id(1).build();
		Article to = Article.builder().id(4).build();
		SortedSet subSet = navigatorSet.subSet(from, to);
		SortedSet subSetFalseFalse = navigatorSet.subSet(from, false, to, false);
		SortedSet subSetTrueFalse = navigatorSet.subSet(from, true, to, false);
		SortedSet subSetTrueTrue = navigatorSet.subSet(from, true, to, true);
		SortedSet subSetFalseTrue = navigatorSet.subSet(from, false, to, true);

		Assertions.assertNotEquals(subSet, subSetFalseFalse);
		Assertions.assertIterableEquals(subSet, subSetTrueFalse);
		Assertions.assertNotEquals(subSet, subSetTrueTrue);
		Assertions.assertNotEquals(subSet, subSetFalseTrue);
		log.info("{}", subSet);
		log.info("{}", subSetFalseFalse);
		log.info("{}", subSetTrueFalse);
		log.info("{}", subSetTrueTrue);
		log.info("{}", subSetFalseTrue);
	}

	@Test
	@Order(13)
	@DisplayName("NavigatorSet tailSet 메소드 확인")
	public void navigatorTailSet() {
		Article from = Article.builder().id(96).build();
		SortedSet subSet = navigatorSet.tailSet(from);
		SortedSet subSetFalse = navigatorSet.tailSet(from, false);
		SortedSet subSetTrue = navigatorSet.tailSet(from, true);
		Assertions.assertNotEquals(subSet, subSetFalse);
		Assertions.assertEquals(subSet, subSetTrue);
		log.info("{}", subSet);
		log.info("{}", subSetFalse);
		log.info("{}", subSetTrue);
	}
}