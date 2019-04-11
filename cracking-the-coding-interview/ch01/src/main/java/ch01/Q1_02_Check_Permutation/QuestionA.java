package ch01.Q1_02_Check_Permutation;

import java.util.Arrays;

/**
 * 1.3 문자열 두 개를 입력으로 받아 그중 하나가 다른 하나의 순열인지 판별하는 메서드를 작성하라.
 */
public class QuestionA {

	public static String sort(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}
}
