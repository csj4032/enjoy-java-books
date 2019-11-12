package com.genius.shorter;

public class SHAShortening extends AbstractShorting {

	public SHAShortening() {
		System.out.println("SHA");
	}

	@Override
	public String shorting(String url) {
		return url + " SHA";
	}
}