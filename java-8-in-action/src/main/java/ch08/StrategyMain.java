package ch08;

public class StrategyMain {

	public static void main(String[] args) {

		Validator validator1 = new Validator(new IsAllLowerCase());
		validator1.validate("aaaaa");

		Validator validator2 = new Validator(new IsNumeric());
		validator2.validate("bbbbb");

		Validator validator3 = new Validator((String s) -> s.matches("[a-z]+"));
		validator3.validate("aaaaa");

		Validator validator4 = new Validator((String s) -> s.matches("\\d+"));
		validator4.validate("bbbbb");

	}

	interface ValidationStrategy {
		boolean execute(String s);
	}

	static private class IsAllLowerCase implements ValidationStrategy {
		@Override
		public boolean execute(String s) {
			return s.matches("[a-z]+");
		}
	}

	static private class IsNumeric implements ValidationStrategy {
		@Override
		public boolean execute(String s) {
			return s.matches("\\d+");
		}
	}

	static private class Validator {

		private final ValidationStrategy strategy;

		public Validator(ValidationStrategy strategy) {
			this.strategy = strategy;
		}

		public boolean validate(String s) {
			return strategy.execute(s);
		}
	}
}