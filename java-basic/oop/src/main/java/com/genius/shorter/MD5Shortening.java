package com.genius.shorter;

public class MD5Shortening extends AbstractShorting {

	public MD5Shortening() {
		System.out.println("MD5");
	}

	@Override
	public String shorting(String url) {
		return url + " MD5";
	}
}
