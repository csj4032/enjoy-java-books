package com.genius.quiz;

public class Tweet {

	public static void main(String[] args) {
		int x = 1;
		int y = x;
		x++;

		StringBuilder s = new StringBuilder("test");
		StringBuilder t = s;
		s.append("Tweet");

		if (x != y) {
			System.out.println("why is equal to y?");
			System.out.println(x);
			System.out.println(y);
		}

		System.out.println(s);
		System.out.println(t);

		if (s != t) {
			System.out.println("impossible, they are equal");
		}
	}
}
