package chapter03.item10;

public class PrivateClass {

	private class Private {
		@Override
		public boolean equals(Object o) {
			throw new AssertionError();
		}
	}

	public void privateEquals() {
		Private p1 = new Private();
		Private p2 = new Private();
		//p1.equals(p2);
	}

	public static void main(String[] args) {
		PrivateClass privateClass = new PrivateClass();
		privateClass.privateEquals();
	}
}