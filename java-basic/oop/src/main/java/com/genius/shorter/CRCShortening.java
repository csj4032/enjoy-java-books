package com.genius.shorter;

import java.util.zip.CRC32C;

public class CRCShortening extends AbstractShorting {

	public CRCShortening() {
		System.out.println("CRC");
	}

	@Override
	public String shorting(String url) {
		return url + " CRC";
	}
}