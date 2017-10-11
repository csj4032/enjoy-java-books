package ch08;

abstract class OnlineBanking {

	public void processCustomer(int id) {
		Customer customer = DataBase.getCustomerWithId(id);
		makeCustomerHappy(customer);
	}

	abstract void makeCustomerHappy(Customer customer);

	static private class Customer {
	}

	static private class DataBase {
		static Customer getCustomerWithId(int id) {
			return new Customer();
		}
	}
}
