package ch01.Q1_03_URLify;

import CtCILibrary.AssortedMethods;

/**
 * 주어진 문자열 내의 모든 공백을 '%20'으로 바꾸는 메서드를 작성하라.
 * 문자열 끝에 추가로 필요한 문자들을 더할 수 있는 충분한 공간이 있다
 * 고 가정하라. 그리고 공백을 포함하는 문자열의 길이도 함께 주어진다
 * 고 가정하라. (주의 : Java로 구현한다면, 문자 배열을 사용하여 필요한
 * 연산을 각 문자에 바로 적용할 수 있도록 하라.)
 * -예
 * 입력 : "Mr John Smith      "
 * 출력 : "Mr%20John%20Smith"
 */
public class Question {

	public static void replaceSpaces(char[] str, int trueLength) {
		int spaceCount = 0, index, i = 0;
		for (i = 0; i < trueLength; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		index = trueLength + spaceCount * 2;
		if (trueLength < str.length) str[trueLength] = '\0';
		for (i = trueLength - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[index - 1] = '0';
				str[index - 2] = '2';
				str[index - 3] = '%';
				index = index - 3;
			} else {
				str[index - 1] = str[i];
				index--;
			}
		}
	}

	private static int findLastCharacter(char[] str) {
		for (int i = str.length - 1; i >= 0; i--) {
			if (str[i] != ' ') {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String str = "Mr John Smith   ";
		char[] arr = str.toCharArray();
		int trueLength = findLastCharacter(arr) + 1;
		replaceSpaces(arr, trueLength);
		System.out.println("\"" + AssortedMethods.charArrayToString(arr) + "\"");
	}
}
