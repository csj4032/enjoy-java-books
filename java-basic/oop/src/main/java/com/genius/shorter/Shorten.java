package com.genius.shorter;

public class Shorten {

	public String shorting(String url, AlgorithmType algorithmType) {
		return ShorteningFactory.getInstance().getShortening(algorithmType).shorting(url);
	}
}