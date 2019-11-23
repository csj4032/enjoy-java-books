package com.genius.reactive.callbacks;

import com.genius.reactive.futures.FutureShoppingCardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdersServiceTest {

	private static ShoppingCardService asyncShoppingCardService;
	private static ShoppingCardService syncShoppingCardService;
	private static ShoppingCardService futureShoppingCardService;

	@BeforeAll
	public static void shoppingCardService() {
		asyncShoppingCardService = new AsyncShoppingCardService();
		syncShoppingCardService = new SyncShoppingCardService();
		futureShoppingCardService = new FutureShoppingCardService();
	}

	@Test
	@Order(1)
	@DisplayName("Callback")
	public void orderService() throws InterruptedException {
		long start = System.currentTimeMillis();
		OrdersService ordersServiceAsync = new OrdersService(asyncShoppingCardService);
		OrdersService ordersServiceSync = new OrdersService(syncShoppingCardService);
		ordersServiceAsync.process();
		ordersServiceSync.process();
		log.info("Total elapsed time in millis is : {}", (System.currentTimeMillis() - start));
		Thread.sleep(5000);
	}

	@Test
	@Order(2)
	@DisplayName("Future")
	public void orderServiceFuture() {
		OrdersService ordersServiceFuture = new OrdersService(futureShoppingCardService);
		ordersServiceFuture.processFuture();
	}
}