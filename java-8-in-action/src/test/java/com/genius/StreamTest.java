package com.genius;

import org.junit.jupiter.api.*;
import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreamTest {

	@Test
	@Order(1)
	public void what() {
		LocalDateTime start = LocalDateTime.of(2019, 04, 22, 10, 00);
		LocalDateTime end = LocalDateTime.of(2019, 04, 22, 11, 00);
	}
}

