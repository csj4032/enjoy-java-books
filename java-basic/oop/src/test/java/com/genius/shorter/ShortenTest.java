package com.genius.shorter;

import org.junit.jupiter.api.*;

import java.util.Comparator;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShortenTest {

	@Test
	@Order(1)
	@DisplayName("단축 URL")
	void shorting() {
		Shorten shorter = new Shorten((url) -> true, null);
		Assertions.assertEquals("http://www.naver.com CRC", shorter.shorting("http://www.naver.com", AlgorithmType.CRC));
	}

	@Test
	@Order(2)
	@DisplayName("CRC 단축 URL")
	void shortingForCrc() {
		Shorten shorter = new Shorten((url) -> true, (o1, o2) -> o1.compareTo(o2));
		Assertions.assertEquals("http://www.naver.com CRC", shorter.shorting("http://www.naver.com", AlgorithmType.CRC));
	}

	@Test
	@Order(3)
	@DisplayName("모든 알고리즘 일반 정렬 단축 URL")
	void shortingForAllAlgorithmSpecialTypeOrder() {
		Shorten shorter = new Shorten((url) -> true, (o1, o2) -> o1.getSpecialType().compareTo(o2.getSpecialType()));
		Assertions.assertEquals("http://www.naver.com CRC", shorter.shorting("http://www.naver.com"));
	}
}