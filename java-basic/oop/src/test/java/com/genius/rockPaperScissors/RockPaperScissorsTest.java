package com.genius.rockPaperScissors;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RockPaperScissorsTest {

	@Test
	@Order(1)
	@DisplayName("바위 기준")
	public void winnerWithRockTest() {
		Assertions.assertFalse(new Rock().beats(new Paper()));
		Assertions.assertFalse(new Rock().beats(new Rock()));
		Assertions.assertTrue(new Rock().beats(new Scissors()));
	}

	@Test
	@Order(2)
	@DisplayName("보 기준")
	public void paperWithRockTest() {
		Assertions.assertFalse(new Paper().beats(new Paper()));
		Assertions.assertTrue(new Paper().beats(new Rock()));
		Assertions.assertFalse(new Paper().beats(new Scissors()));
	}

	@Test
	@Order(3)
	@DisplayName("가위 기준")
	public void scissorsWithRockTest() {
		Assertions.assertTrue(new Scissors().beats(new Paper()));
		Assertions.assertFalse(new Scissors().beats(new Rock()));
		Assertions.assertFalse(new  Scissors().beats(new Scissors()));
	}
}
