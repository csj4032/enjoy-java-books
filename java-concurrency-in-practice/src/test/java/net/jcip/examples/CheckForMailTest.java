package net.jcip.examples;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckForMailTest {

	@Test
	public void checkForMail() throws InterruptedException {
		CheckForMail checkForMail = new CheckForMail();
		boolean result = checkForMail.checkMail(Set.of("naver", "daum","google"), 3000, TimeUnit.MILLISECONDS);
		assertTrue(result);
	}
}