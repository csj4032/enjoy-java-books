package com.genius.sync;

public class DepositThread implements Runnable {

	private Account account;

	DepositThread(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		synchronized (account) {
			for (int i = 0; i < 1000; i++) {
				account.deposit(1L);
			}
			account.inquiry();
		}
	}
}
