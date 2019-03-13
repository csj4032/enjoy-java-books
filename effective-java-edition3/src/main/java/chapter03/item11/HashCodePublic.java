package chapter03.item11;

import chapter03.item13.PhoneNumber;

import java.util.Comparator;

import static java.util.Comparator.comparingInt;

public class HashCodePublic {

	public static void main(String[] args) {

		String s = "b+";
		String b = "aa";

		System.out.println(s.hashCode());
		System.out.println(b.hashCode());
		if (s.hashCode() == b.hashCode()) {
		}


	}
}
