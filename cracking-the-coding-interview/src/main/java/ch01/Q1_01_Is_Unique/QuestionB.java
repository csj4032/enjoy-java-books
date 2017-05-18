package ch01.Q1_01_Is_Unique;

public class QuestionB {

	private static boolean isUniqueChars(String str) {
		if (str.length() > 26) return false;
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0) return false;
			checker |= (1 << val);
			System.out.println(Integer.toBinaryString(checker));
		}
		return true;
	}

	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle"};
		for (String word : words) {
			System.out.println(word + ": " + isUniqueChars(word));
		}
	}
}
