package com.genius.shorter;

public class ShorteningFactory {

	private static volatile ShorteningFactory instance;

	private ShorteningFactory() {
	}

	public static ShorteningFactory getInstance() {
		if (instance == null) {
			synchronized (ShorteningFactory.class) {
				if (instance == null) {
					instance = new ShorteningFactory();
				}
			}
		}
		return instance;
	}

	public Shortening getShortening(AlgorithmType algorithmType) {
		return switch (algorithmType) {
			case CRC:
				yield new CRCShortening();
			case MD5:
				yield new MD5Shortening();
			case SHA:
				yield new SHAShortening();
			default:
				throw new IllegalArgumentException("Unexpected value: " + algorithmType);
		};
	}
}
