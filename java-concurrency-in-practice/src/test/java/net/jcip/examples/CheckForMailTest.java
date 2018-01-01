package net.jcip.examples;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CheckForMailTest {

	@Test
	public void checkForMail() throws InterruptedException {
		CheckForMail checkForMail = new CheckForMail();
		boolean result = checkForMail.checkMail(Set.of("naver", "daum","google"), 3000, TimeUnit.MILLISECONDS);
		Assert.assertTrue(result);
	}
}