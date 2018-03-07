package com.genius;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTest {

	private Game game;

	@Before
	public void setUp() {
		game = new Game();
	}

	@Test
	public void canRoll() {
		game.roll(0);
	}

	@Test
	public void gutterGame() {
		rollMany(0, 20);
		assertThat(game.getScore(), is(0));
	}

	@Test
	public void allOnes() {
		rollMany(1, 20);
		assertThat(game.getScore(), is(20));
	}

	@Test
	public void oneSpare() {
		rollSpare();
		game.roll(3);
		rollMany(0, 17);
		assertThat(game.getScore(), is(16));
	}

	@Test
	public void oneStrike() {
		rollStrike(10);
		game.roll(5);
		game.roll(3);
		rollMany(0, 16);
		assertThat(game.getScore(), is(26));
	}

	@Test
	public void testSampleGame() throws Exception {
		game.roll(1);
		game.roll(4);

		game.roll(4);
		game.roll(5);

		game.roll(6);
		game.roll(4);

		game.roll(5);
		game.roll(5);

		game.roll(10);

		game.roll(0);
		game.roll(1);

		game.roll(7);
		game.roll(3);

		game.roll(6);
		game.roll(4);

		game.roll(10);

		game.roll(2);
		game.roll(8);
		game.roll(6);
		assertThat(game.getScore(), is(133));
	}

	@Test
	public void tenthFrameSpare() {
		for (int i = 0; i < 18; i++) {
			game.roll(4);
		}
		game.roll(10);
		game.roll(10);
		game.roll(10);
		assertThat(game.getScore(), is(102));
	}

	@Test
	public void perfectGame() {
		rollMany(10, 10);
		game.roll(10);
		game.roll(10);
		assertThat(game.getScore(), is(300));
	}

	private void rollStrike(int i) {
		game.roll(i);
	}

	private void rollSpare() {
		game.roll(5);
		game.roll(5);
	}

	private void rollMany(int pins, int frames) {
		for (int i = 0; i < frames; i++) {
			game.roll(pins);
		}
	}
}