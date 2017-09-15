package com.software.string.problem;

// 빈 문자열 확인
public class EmptyStringExample {

	public static void main(String[] args) {

		String emptyString = "";

		if (emptyString.trim().length() == 0) {
			System.out.println("emptyString은 비어있다.");
		} else {
			System.out.println("emptyString은 비어있지 않다.");
		}
	}
}
