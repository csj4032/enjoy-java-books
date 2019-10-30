package com.genius.condition.express;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str;
		int size = 0;
		int i = 0;
		boolean in_char = false;

		System.out.println(multiple(0));

	}

	static boolean multiple(int number) {
		return (number != 0 && number % 3 == 0) ? true : false;
	}
}
