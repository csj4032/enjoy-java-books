package com.software.basic.problem;

public class LoopExample {

	public static void main(String[] args) {
		for (int i = 2; i < 10; i++) {
			for (int j = 1; j < 10; i++) {
				System.out.println(i + "X " + j + " = " + i * j);
			}
		}
	}
}