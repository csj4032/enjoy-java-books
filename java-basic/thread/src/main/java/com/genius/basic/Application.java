package com.genius.basic;

public class Application {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		Thread thread1 = new Thread(() -> calculator.setMemory(10));
		Thread thread2 = new Thread(() -> calculator.setMemory(50));

		thread1.setName("Thread1");
		thread2.setName("Thread2");

		thread1.start();
		thread2.start();
	}
}
