package com.genius.reactive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Message {
	private long id;
	private String text;

	public Message(long id, String text) {
		this.id = id;
		this.text = text;
	}
}
