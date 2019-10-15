package com.genius.shorter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ShorterTest {

	@Test
	@DisplayName("단축 URL")
	void shorting() {
		Shorten shorter = new Shorten(null, null);
		Assertions.assertEquals("http://www.naver.com", shorter.shorting("http://www.naver.com", AlgorithmType.CRC));
	}
}