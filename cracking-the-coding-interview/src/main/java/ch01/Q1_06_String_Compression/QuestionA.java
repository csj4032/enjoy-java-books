package ch01.Q1_06_String_Compression;

import java.util.stream.Stream;

/**
 * 같은 문자가 연속으로 반복될 경우, 그 횟수를 사용해 문자열을 압축하
 * 는 메서드를 구현하라. 가령 압축해야 할 문자열이 aabccccccccaa라면
 * a2b1c8a3과 같이 압축되어야 한다. 압축 결과로 만들어지는 문자열이
 * 원래 문자열보다 짧아지지 않는 경우, 이 메서드는 원래 문자열을 그대
 * 로 반환해야 한다.
 */
public class QuestionA {

	public static String compress(String str) {
		Stream.of(str.split("")).forEach(e -> System.out.println(e));
		return str;
	}

	public static void main(String[] args) {
		compress("aabccccccccaa");
	}
}
