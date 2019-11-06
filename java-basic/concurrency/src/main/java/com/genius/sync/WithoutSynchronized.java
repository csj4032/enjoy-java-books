package com.genius.sync;

public class WithoutSynchronized {

	public static void main(String[] args) {
		Account account = new Account();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new DepositThread(account));
			t.start();
		}
	}
}
