package com.genius.pubsub;

import java.util.concurrent.*;

public class Manager {

	private final BlockingDeque blockingDeque;
	private final Publish publish;
	private final Subscribe subscribe;

	public Manager(BlockingDeque blockingDeque, Publish publish, Subscribe subscribe) {
		this.blockingDeque = blockingDeque;
		this.publish = publish;
		this.subscribe = subscribe;
		init();
	}

	public void run() {
		ScheduledExecutorService pub = Executors.newSingleThreadScheduledExecutor();
		ScheduledExecutorService sub = Executors.newSingleThreadScheduledExecutor();
		pub.schedule(publish, 0, TimeUnit.MILLISECONDS);
		sub.schedule(subscribe, 100, TimeUnit.MILLISECONDS);
		pub.shutdown();
		sub.shutdown();
	}

	private void init() {
		publish.setDeque(blockingDeque);
		subscribe.setDeque(blockingDeque);
	}

	public static void main(String[] args) {
		new Manager(new LinkedBlockingDeque<Message>(), new Publish(), new Subscribe()).run();
	}
}