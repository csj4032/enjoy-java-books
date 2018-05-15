package bigO;

public class Practise12 {

	int numChars = 26;

	void printSortedStrings(int remaining, String prefix) {
		if (remaining == 0) {
			if (isInOrder(prefix)) {
				System.out.println(prefix);
			}
		} else {
			for (int i = 0; i < numChars; i++) {
				char c = ithLetter(i);
				printSortedStrings(remaining - 1, prefix + c);
			}
		}
	}

	boolean isInOrder(String s) {
		for (int i = 1; i < s.length(); i++) {
			int prev = ithLetter(s.charAt(i - 1));
			int curr = ithLetter(s.charAt(i));
			if (prev > curr) {
				return false;
			}
		}
		return true;
	}

	char ithLetter(int i) {
		return (char) (((int) 'a') + i);
	}

	public static void main(String[] args) {
		Practise12 practise = new Practise12();
		practise.printSortedStrings(2, "");
	}
}