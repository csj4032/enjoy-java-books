package com.genius.pubsub;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class Publish implements Runnable {

	private static final long MAX = 10000l;
	private BlockingDeque<Message> blockingDeque;
	private AtomicLong atomicLong = new AtomicLong();

	@Override
	public void run() {
		while (true) {
			blockingDeque.push(new Message(atomicLong.getAndIncrement()));
			log.info(getClass().getName() + " Pub : " + atomicLong.get());
			if (atomicLong.get() == MAX) break;
		}
	}

	public void setDeque(BlockingDeque blockingDeque) {
		this.blockingDeque = blockingDeque;
	}
}