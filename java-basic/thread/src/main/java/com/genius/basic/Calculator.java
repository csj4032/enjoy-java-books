package com.genius.basic;

public class Calculator {

	private int memory;

	public int getMemory() {
		return this.memory;
	}

	public synchronized void setMemory(int memory) {
		this.memory = memory;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
		System.out.println(Thread.currentThread().getName() + " : " + this.memory);
	}
}
