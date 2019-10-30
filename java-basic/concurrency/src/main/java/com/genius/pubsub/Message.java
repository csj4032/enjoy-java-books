package com.genius.pubsub;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Message {

	private final long id;

	public Message(long id) {
		this.id = id;
	}
}
