package com.genius.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MedianStream {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 3, 2, 4, 5, 8, 9);
		IntStream.range(0, list.size() - 4).mapToObj(i -> list.subList(i, list.size() - 3 >= i ? i + 3 : list.size()).stream().sorted().collect(Collectors.toList()).get(1)).collect(Collectors.toList());
	}
}
