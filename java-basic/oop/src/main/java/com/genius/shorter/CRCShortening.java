package com.genius.shorter;

import java.util.zip.CRC32C;

public class CRCShortening extends AbstractShorting {

	@Override
	public String shorting(String url) {
		validation();
		CRC32C crc32C = new CRC32C();
		crc32C.update(url.getBytes());
		return crc32C.toString();
	}
}