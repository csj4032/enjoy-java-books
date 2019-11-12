package com.genius.reactive;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
public class MessageExtend extends Message {

	private final LocalDateTime dateTime;

	MessageExtend(Long id, String text, LocalDateTime dateTime) {
		super(id, text);
		this.dateTime = dateTime;
	}
}
