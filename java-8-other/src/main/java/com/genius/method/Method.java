package com.genius.method;

public class Method {

	public static void main(String[] args) {
		String s1 = "홍길동";
		String s2 = "홍길동";
		String s3 = "홍" + "길동";
		String s4 = "홍";
		s4 = s4 + "길동";
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println(s1 == s4);
	}
}