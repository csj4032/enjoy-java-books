package com.genius.future;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Slf4j
public class WeAreTheFuture {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		System.out.println(getString());
		System.out.println(getString2());
	}

	private static String getString() throws InterruptedException, ExecutionException, TimeoutException {
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			log.info("supplyAsync 1");
			return "1";
		}).orTimeout(1, TimeUnit.SECONDS).handle((s, t) -> s);

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			log.info("supplyAsync 2");
			if (1 == 1) throw new RuntimeException("error2");
			return "2";
		}).orTimeout(1, TimeUnit.SECONDS).exceptionally((t) -> "b");

		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
			log.info("supplyAsync 3-1");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("supplyAsync 3-2");
			return "3";
		}).orTimeout(1, TimeUnit.SECONDS).exceptionally((t) -> "c");

		CompletableFuture.anyOf(future1, future2, future3).get(1, TimeUnit.SECONDS);
		return List.of(future1, future2, future3).stream().map(CompletableFuture::join).collect(Collectors.joining(""));
	}

	private static String getString2() throws InterruptedException, ExecutionException, TimeoutException {
		return CompletableFuture.supplyAsync(() -> {
			log.info("parent");
			String f1 = "A";
			try {
				f1 = CompletableFuture.supplyAsync(() -> {
					log.info("child 1");
					return "1";
				}).exceptionally((t) -> "A").get(1, TimeUnit.SECONDS);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				e.printStackTrace();
			}

			String f2 = "B";
			try {
				f2 = CompletableFuture.supplyAsync(() -> {
					log.info("child1 2-1");
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					log.info("child1 2-2");
					return "2";
				}).handle((s, t) -> s == null ? "B" : s).get(500, TimeUnit.MILLISECONDS);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				e.printStackTrace();
			}
			return f1 + f2;
		}).get(1, TimeUnit.SECONDS);
	}
}