package ch08;

import java.util.function.Consumer;

public class OnlineBankingLambda {

	public static void main(String[] args) {
		new OnlineBankingLambda().processCustomer(1337, System.out::print);
	}

	public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
		makeCustomerHappy.accept(Database.getCustomerWithId(id));
	}

	// dummy Customer class
	static private class Customer {
	}

	// dummy Database class
	static private class Database {
		static Customer getCustomerWithId(int id) {
			return new Customer();
		}
	}
}
