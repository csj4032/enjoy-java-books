package com.genius.pubsub;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingDeque;

@Slf4j
public class Subscribe implements Runnable {

	private BlockingDeque<Message> blockingDeque;

	@Override
	public void run() {
		while (!blockingDeque.isEmpty()) {
			log.info(getClass().getName() + " Sub : "+ blockingDeque.pollFirst().getId());
		}
	}

	public void setDeque(BlockingDeque blockingDeque) {
		this.blockingDeque = blockingDeque;
	}
}