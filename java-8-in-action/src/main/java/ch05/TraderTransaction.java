package ch05;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class TraderTransaction {

	static Trader raoul = new Trader("Raoul", "Cambridge");
	static Trader mario = new Trader("Mario", "Milan");
	static Trader alan = new Trader("Alan", "Cambridge");
	static Trader brian = new Trader("Brian", "Cambridge");

	static List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
	);

	List<Transaction> tr2011 = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(comparing(Transaction::getValue)).collect(toList());
	//List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity()).collect(toList());
	Set<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity()).collect(toSet());

	List<Trader> traders = transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Cambridge")).distinct().sorted(comparing(Trader::getName)).collect(toList());

	String tranderStr = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().collect(joining());

	//boolean milanBased = transactions.stream().filter(t->t.getTrader().getCity().equals("Milan")).findAny().isPresent();
	boolean milanBased = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));

	Optional<Transaction> smallestTransaction = transactions.stream().min(Comparator.comparing(Transaction::getValue));

}