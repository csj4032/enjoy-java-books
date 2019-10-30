package com.genius.shorter;

public class StringTest {

	public static void main(String[] args) {
		String str1  =  "홍길동";
		String str2  =  "홍길동";
		String str3  =  new String("홍길동");

		if(str1 == str2) {
			System.out.println("!");
		}

		if(str1.equals(str2)) {
			System.out.println("!!");
		}

		if(str1 == str3) {
			System.out.println("#");
		}

		if(str1.equals(str3)) {
			System.out.println("##");
		}
	}
}
