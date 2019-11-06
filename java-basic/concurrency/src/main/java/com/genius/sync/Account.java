package com.genius.sync;

public class Account {

	private Long balance = 0L;

	public void deposit(Long amount) {
		balance = balance + amount;
	}

	public void inquiry() {
		System.out.println("잔액 : " + balance);
	}
}
