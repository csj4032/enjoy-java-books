package ch01.Q1_01_Is_Unique;

import java.util.Arrays;

/**
 * 1.1 문자열에 포함된 문자들이 전부 유일한지를 검사하는 알고리즘을 구현하라.
 * 다른 자료구조를 사용할 수 없는 상황이라면 어떻게 하겠는가?
 */
public class QuestionA {

	public static boolean isUniqueChars(String str) {
		boolean[] char_set = new boolean[100000];
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			if (char_set[val]) return false;
			char_set[val] = true;
		}
		return true;
	}

	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle", "최봄사랑해해~"};
		Arrays.asList(words).stream().forEach(w -> System.out.println(isUniqueChars(w)));
	}
}