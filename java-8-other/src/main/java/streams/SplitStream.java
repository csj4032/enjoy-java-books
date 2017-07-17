package streams;

import java.util.Arrays;
import java.util.Comparator;

public class SplitStream {

	public static void main(String[] args) {
		solution("test");
	}

	private static int solution(String string) {
		//return Arrays.stream(string.split("[0-9]+")).filter(s -> s.matches(".*[A-Z].*")).max(Comparator.comparing(String::length)).map(String::length).orElse(-1);
		return Arrays.stream(string.split("[0-9]+")).filter(s -> s.matches(".*[A-Z].*")).map(String::length).max(Comparator.comparing(Integer::valueOf)).orElse(-1);
	}
}
