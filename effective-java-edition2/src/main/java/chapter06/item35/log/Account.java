package chapter06.item35.log;

public class Account {

	int balance = 20;

	public boolean withdraw(int amount) {
		if (balance < amount) {
			return false;
		}
		balance = balance - amount;
		return true;
	}
}
