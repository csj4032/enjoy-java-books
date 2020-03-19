package com.genius;

import lombok.*;

@AllArgsConstructor
public class Recommend {

	@ToString
	@AllArgsConstructor
	public static class Content {
		private String subject;
	}

	public static void main(String[] args) {
		Content content = new Content("A");
		temp(content);
		System.out.println(content);
	}

	private static void temp(Content content) {
		content = new Content("B");
	}
}