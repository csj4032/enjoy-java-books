package com.genius.shorter;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.stream.Stream;

public class Shorten {

	private ShortenValidation shortenValidation;
	private Comparator<AlgorithmType> shortenPriorityPolicy;

	public Shorten(@NotNull ShortenValidation shortenValidation, @NotNull ShortenPriorityPolicy shortenPriorityPolicy) {
		this.shortenValidation = shortenValidation;
		this.shortenPriorityPolicy = shortenPriorityPolicy;
	}

	public String shorting(String url) {
		return Stream.of(AlgorithmType.values())
				.sorted(shortenPriorityPolicy)
				.map(algorithmType -> ShorteningFactory.getInstance().getShortening(algorithmType).shorting(url))
				.filter(sUrl -> shortenValidation.validation(sUrl))
				.findFirst()
				.orElseThrow(() -> new ShortenException("모든 알고리즘에서 중복이 발생하는 URL : " + url));
	}

	public String shorting(String url, AlgorithmType algorithmType) {
		String sUrl = ShorteningFactory.getInstance().getShortening(algorithmType).shorting(url);
		if (shortenValidation.validation(sUrl)) {
			return sUrl;
		} else {
			throw new ShortenException(algorithmType + "알고리즘에서 중복이 발생하는 URL : " + url);
		}
	}
}