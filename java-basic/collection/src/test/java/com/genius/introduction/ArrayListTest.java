package com.genius.introduction;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArrayListTest {

	private static List<Article> arrayList;
	private static List<Article> subList;
	private static List<Article> clear;
	private static List<Article> contain;
	private static List<Article> retailAll;
	private static List<Integer> integers;

	@BeforeAll
	public static void setUp() {
		arrayList = new ArrayList(10);
		subList = new ArrayList(10);
		clear = new ArrayList(10);
		contain = new ArrayList(10);
		retailAll = new ArrayList(10);
		integers = new ArrayList(10);

		for (int i = 0; i < 10; i++) {
			Article article = Article.builder().id(i).subject("제목" + i).contents("내용" + i).build();
			subList.add(article);
			clear.add(article);
			contain.add(article);
			retailAll.add(article);
			integers.add(Integer.valueOf(i));
		}
	}

	@Test
	@Order(1)
	@DisplayName("리스트에 아티클 객체를 하나 추가하고 사이즈를 비교")
	public void add() {
		Article article = Article.builder().id(1).subject("제목1").contents("내용1").build();
		arrayList.add(article);
		Assertions.assertEquals(1, arrayList.size());
		Assertions.assertEquals(1, arrayList.get(0).getId());
	}

	@Test
	@Order(2)
	@DisplayName("새로운 아티클 객체를 리스트에 추가")
	public void addAll() {
		Article article2 = Article.builder().id(2).subject("제목2").contents("내용2").build();
		Article article3 = Article.builder().id(3).subject("제목3").contents("내용2").build();
		Article article4 = Article.builder().id(4).subject("제목4").contents("내용2").build();
		arrayList.addAll(Arrays.asList(article2, article3, article4));
		Assertions.assertEquals(4, arrayList.size());
	}

	@Test
	@Order(3)
	@DisplayName("첫번째 인덱스의 아티클 아이디 비교")
	public void get() {
		Assertions.assertEquals(1, arrayList.get(0).getId());
	}

	@Test
	@Order(4)
	@DisplayName("첫번째 인덱스에 아티클 객체를 추가")
	public void set() {
		Article article = Article.builder().id(2).subject("제목2_2").contents("내용").build();
		arrayList.set(0, article);
		Assertions.assertEquals(4, arrayList.size());
		Assertions.assertEquals("제목2_2", arrayList.get(0).getSubject());
	}

	@Test
	@Order(5)
	@DisplayName("특정 인덱스에 아티클 객체를 추가")
	public void addIndex() {
		Article article = Article.builder().id(0).subject("제목0").contents("내용0").build();
		arrayList.add(0, article);
		Assertions.assertEquals(5, arrayList.size());
		Assertions.assertEquals("제목0", arrayList.get(0).getSubject());
	}

	@Test
	@Order(6)
	@DisplayName("특정 인덱스 아티클 객체를 삭제")
	public void remove() {
		arrayList.remove(0);
		Assertions.assertEquals(4, arrayList.size());
		Assertions.assertEquals("제목2_2", arrayList.get(0).getSubject());
	}

	@Test
	@Order(7)
	@DisplayName("특정 아티클 객체를 삭제")
	public void removeObject() {
		Article article = Article.builder().id(2).subject("제목2_2").contents("내용").build();
		arrayList.remove(article);
		Assertions.assertEquals(3, arrayList.size());
	}

	@Test
	@Order(8)
	@DisplayName("특정 리스트의 동등한 아티클 객체 삭제")
	public void removeAll() {
		Article article = Article.builder().id(2).subject("제목2").contents("내용2").build();
		Assertions.assertTrue(arrayList.removeAll(List.of(article)));
		Assertions.assertFalse(arrayList.removeAll(List.of(article)));
	}

	@Test
	@Order(9)
	@DisplayName("모든 아티클 객체 삭제")
	public void clear() {
		clear.clear();
		Assertions.assertEquals(0, clear.size());
	}

	@Test
	@Order(10)
	@DisplayName("특정 아티클 객체 확인")
	public void contain() {
		Article article2 = Article.builder().id(2).subject("제목2_2").contents("내용").build();
		boolean bool = contain.contains(article2);
		Assertions.assertTrue(bool);
	}

	@Test
	@Order(11)
	@DisplayName("특정 리스트 아티클 객체 확인")
	public void containAll() {
		boolean bool = contain.containsAll(retailAll);
		Assertions.assertTrue(bool);
	}

	@Test
	@Order(12)
	@DisplayName("특정 리스트의 다른 아티클 객체 삭제")
	public void retailAll() {
		Article article = Article.builder().id(0).subject("제목0").contents("내용0").build();
		Article article1 = Article.builder().id(1).subject("제목0").contents("내용0").build();
		List retail = List.of(article, article1);
		boolean bool = retailAll.retainAll(retail);
		Assertions.assertTrue(bool);
		Assertions.assertEquals(retail, retailAll);
	}

	@Test
	@Order(13)
	@DisplayName("지정한 범위의 아티클 리스트 반환")
	public void subList() {
		Article article2 = Article.builder().id(2).subject("제목2").contents("내용2").build();
		Article article3 = Article.builder().id(3).subject("제목3").contents("내용2").build();
		Article article4 = Article.builder().id(4).subject("제목4").contents("내용2").build();
		List sub = subList.subList(2, 5);
		Assertions.assertEquals(3, sub.size());
		Assertions.assertEquals(List.of(article2, article3, article4), sub);
	}

	@Test
	@Order(14)
	@DisplayName("삭제 메소드 오버로딩")
	public void removeIntObject() {
		Integer index = 1;
		// remove object
		integers.remove(index);
		// remove index
		integers.remove(1);
		Assertions.assertIterableEquals(integers, List.of(0, 3, 4, 5, 6, 7, 8, 9));
	}
}
