package ch07;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Spliterator {

	final static String SENTENCE = "Nel mezzo del cammin di nostra";

	public static void main(String[] args) {
		System.out.println(countWordsIteratively(SENTENCE));
		Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
		//System.out.println(counterWords(stream));
		System.out.println(counterWords(stream.parallel()));
	}

	private static int counterWords(Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
		return wordCounter.getCounter();
	}

	public static int countWordsIteratively(String s) {
		int counter = 0;
		boolean lastSpace = true;
		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if (lastSpace) {
					counter++;
				}
				lastSpace = false;
			}
		}
		return counter;
	}
}
