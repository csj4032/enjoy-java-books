package chapter07.item43;

import java.util.ArrayList;
import java.util.List;

public class Cheese {

	private final List<Cheese> cheeseInStock = new ArrayList<>();
	private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];

	public Cheese[] getCheeses() {
		return cheeseInStock.toArray(EMPTY_CHEESE_ARRAY);
	}
}