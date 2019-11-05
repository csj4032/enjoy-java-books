package com.genius.fluxMono;

import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
public class FruitInfo {

	private final List<String> distinct;
	private final Map<String, Long> count;

	public FruitInfo(List<String> distinct, Map<String, Long> count) {
		this.distinct = distinct;
		this.count = count;
	}
}
