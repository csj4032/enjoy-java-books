package ch06;

import ch04.Dish;
import ch04.LowCaloricDishes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.reducing;

public class GroupingTransactions {

	public static List<Transaction> transactions = Arrays.asList(
			new Transaction(Currency.EUR, 1500.0),
			new Transaction(Currency.USD, 2300.0),
			new Transaction(Currency.GBP, 9900.0),
			new Transaction(Currency.EUR, 1100.0),
			new Transaction(Currency.JPY, 7800.0),
			new Transaction(Currency.CHF, 6700.0),
			new Transaction(Currency.EUR, 5600.0),
			new Transaction(Currency.USD, 4500.0),
			new Transaction(Currency.CHF, 3400.0),
			new Transaction(Currency.GBP, 3200.0),
			new Transaction(Currency.USD, 4600.0),
			new Transaction(Currency.JPY, 5700.0),
			new Transaction(Currency.EUR, 6800.0));

	public enum Currency {
		EUR, USD, JPY, GBP, CHF
	}

	public static class Transaction {
		private final Currency currency;
		private final double value;

		public Transaction(Currency currency, double value) {
			this.currency = currency;
			this.value = value;
		}

		public Currency getCurrency() {
			return currency;
		}

		public double getValue() {
			return value;
		}

		@Override
		public String toString() {
			return currency + " " + value;
		}
	}

	public static void main(String[] args) {
		//Comparator<Dish> dishCaloriesComparator = Comparator.comparing(Dish::getCalories);
		//Optional<Dish> mostCalorieDish = LowCaloricDishes.menu.stream().collect(maxBy(dishCaloriesComparator));
		Optional<Dish> mostCalorieDish = LowCaloricDishes.menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
		int totalCalorites = LowCaloricDishes.menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
	}
}