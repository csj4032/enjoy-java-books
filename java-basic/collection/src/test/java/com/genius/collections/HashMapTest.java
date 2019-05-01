package com.genius.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@DisplayName("HashMap")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HashMapTest {

	private static Map<Writer, List<Article>> hashMap = new HashMap();
	private static Map<Writer, List<Article>> identityHashMap = new IdentityHashMap();
	private static Map<Writer, List<Article>> weakHashMap = new WeakHashMap<>();
	private static Map<Writer, List<Article>> concurrentHashMap = new ConcurrentHashMap<>();
	private static Writer weakHash = new Writer(10000, "");
	private static Writer identity = new Writer(10000, "");

	@BeforeAll
	public static void setUp() {
		for (int i = 0; i < 10; i++) {
			Writer writer = new Writer(i, "name" + i);
			List<Article> articles = new ArrayList<>();
			for (int j = 0; j < 100; j++) {
				int index = (j + 1) * (100 * i);
				articles.add(new Article(index, "제목" + i + "_" + j, "내용"));
			}
			hashMap.put(writer, articles);
			concurrentHashMap.put(writer, articles);
		}
		weakHashMap.put(weakHash, null);
	}

	@Test
	@Order(1)
	@DisplayName("키를 이용한 값 가져오기")
	public void get() {
		Writer writer = new Writer(1, "name1");
		List<Article> articles = hashMap.get(writer);
		assertEquals(100, articles.size());
	}

	@Test
	@Order(2)
	@DisplayName("IdentityMap, HashMap 키를 이용한 값 가져오기")
	public void identityHashMapContainsKey() {
		Writer writer = new Writer(1, "name1");
		assertTrue(hashMap.containsKey(writer));
		writer.setId(10000);
		Assertions.assertFalse(hashMap.containsKey(writer));

		identityHashMap.put(identity, null);
		assertTrue(identityHashMap.containsKey(identity));
		identity.setId(100000);
		assertTrue(identityHashMap.containsKey(identity));
	}

	@Test
	@Order(3)
	@DisplayName("HashMap Thread Safe ?")
	public void hashMap() throws Exception {
		Map<String, Integer> map = new HashMap<>();
		List<Integer> sumList = parallelSum100(map, 100);
		log.info("{}", sumList.stream().distinct().count());
		Assertions.assertNotEquals(1, sumList.stream().distinct().count());
		long wrongResultCount = sumList.stream().filter(num -> num != 100).count();
		Assertions.assertTrue(wrongResultCount > 0);
	}

	@Test
	@Order(4)
	@DisplayName("ConcurrentHashMap Thread Safe ?")
	public void currentHashMap() throws Exception {
		Map<String, Integer> map = new ConcurrentHashMap<>();
		List<Integer> sumList = parallelSum100(map, 1000);
		log.info("{}", sumList.stream().distinct().count());
		Assertions.assertEquals(1, sumList.stream().distinct().count());
		long wrongResultCount = sumList.stream().filter(num -> num != 100).count();
		Assertions.assertEquals(0, wrongResultCount);
	}

	@Test
	@Order(5)
	@DisplayName("약한참조")
	public void weakHashMap() {
		weakHash = null;
		System.gc();
		log.info("{}", weakHashMap);
		Assertions.assertTrue(weakHashMap.isEmpty());
	}

	private List<Integer> parallelSum100(Map<String, Integer> map, int executionTimes) throws InterruptedException {
		List<Integer> sumList = new ArrayList<>(1000);
		for (int i = 0; i < executionTimes; i++) {
			map.put("test", 0);
			ExecutorService executorService = Executors.newFixedThreadPool(4);
			for (int j = 0; j < 10; j++) {
				executorService.execute(() -> {
					for (int k = 0; k < 10; k++)
						map.computeIfPresent("test", (key, value) -> value + 1);
				});
			}
			executorService.shutdown();
			executorService.awaitTermination(5, TimeUnit.SECONDS);
			sumList.add(map.get("test"));
		}
		return sumList;
	}
}
