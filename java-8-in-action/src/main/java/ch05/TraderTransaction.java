package ch05;

import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class TraderTransaction {
	Trader raoul = new Trader("Raoul", "Cambridge");
	Trader mario = new Trader("Mario", "Milan");
	Trader alan = new Trader("Alan", "Cambridge");
	Trader brian = new Trader("Brian", "Cambridge");

	List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(brian, 2012, 1000),
			new Transaction(brian, 2011, 400),
			new Transaction(brian, 2012, 710),
			new Transaction(brian, 2012, 700),
			new Transaction(brian, 2012, 950)
	);

	List<Transaction> tr2011 = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(comparing(Transaction::getValue)).collect(toList());
	//List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity()).collect(toList());
	Set<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity()).collect(toSet());
}

@Data
@ToString
class Trader {
	private final String name;
	private final String city;
}

@Data
@ToString
class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;
}