package com.genius.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.concurrent.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WeAreTheFutureTest {

	@Test
	@Order(1)
	public void getFuture() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> f = new CompletableFuture<>();
		f.completeExceptionally(new RuntimeException());
		//System.out.println(f.get());
	}

	@Test
	@Order(2)
	public void runAsyncThenRun() throws InterruptedException {
		CompletableFuture.runAsync(() -> log.info("runAsync"))
				.thenRun(() -> log.info("then run 1"))
				.thenRun(() -> log.info("then run 2"));

		ForkJoinPool.commonPool().shutdown();
		ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
	}

	@Test
	@Order(3)
	public void supplyAsyncThenApplyThenAccept() throws InterruptedException {
		CompletableFuture.supplyAsync(() -> {
			log.info("supplyAsync");
			return 1;
		}).thenApply(s -> {
			log.info("then Apply {}", s);
			return s + 1;
		}).thenAccept(s2 -> log.info("then Apply {}", s2));
		log.info("exit");
		ForkJoinPool.commonPool().shutdown();
		ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
	}

	@Test
	@Order(4)
	public void supplyAsyncThenComposeThenAccept() throws InterruptedException {
		CompletableFuture.supplyAsync(() -> {
			log.info("supplyAsync");
			return 1;
		}).thenCompose(s -> {
			log.info("then Compose {}", s);
			return CompletableFuture.completedFuture(s + 1);
		}).thenApply(s2 -> {
			log.info("then Apply {}", s2);
			return CompletableFuture.completedFuture(s2 + 1);
		}).thenAccept(s3 -> log.info("then Apply {}", s3));
		log.info("exit");
		ForkJoinPool.commonPool().shutdown();
		ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
	}

	@Test
	@Order(5)
	public void supplyAsyncThenComposeThenApplyExceptionallyThenAccept() throws InterruptedException {
		CompletableFuture.supplyAsync(() -> {
			log.info("supplyAsync");
			return 1;
		}).thenCompose(s -> {
			log.info("then Compose {}", s);
			return CompletableFuture.completedFuture(s + 1);
		}).thenApply(s2 -> {
			log.info("then Apply {}", s2);
			return s2 + 1;
		}).exceptionally(e -> 10).thenAccept(s3 -> log.info("then Apply {}", s3));
		log.info("exit");
		ForkJoinPool.commonPool().shutdown();
		ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
	}

	@Test
	@Order(6)
	public void supplyAsyncRuntimeExceptionThenComposeThenApplyExceptionallyThenAccept() throws InterruptedException {
		CompletableFuture.supplyAsync(() -> {
			log.info("supplyAsync");
			if (1 == 1) throw new RuntimeException();
			return 1;
		}).thenCompose(s -> {
			log.info("then Compose {}", s);
			return CompletableFuture.completedFuture(s + 1);
		}).thenApply(s2 -> {
			log.info("then Apply {}", s2);
			return s2 + 1;
		}).exceptionally(e -> 10).thenAccept(s3 -> log.info("then Apply {}", s3));
		log.info("exit");
		ForkJoinPool.commonPool().shutdown();
		ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS);
	}

	@Test
	@Order(7)
	public void poolSupplyAsyncRuntimeExceptionThenComposeThenApplyExceptionallyThenAccept() throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		CompletableFuture
				.supplyAsync(() -> {
					log.info("supplyAsync");
					return 1;
				}, executorService)
				.thenCompose(s -> {
					log.info("thenCompose {}", s);
					return CompletableFuture.completedFuture(s + 1);
				})
				.thenApplyAsync(s2 -> {
					log.info("thenApply {}", s2);
					return s2 * 3;
				}, executorService)
				.thenAcceptAsync(s3 -> log.info("thenAcceptAsync {}", s3), executorService);
		executorService.shutdown();
		executorService.awaitTermination(10, TimeUnit.SECONDS);
	}
}