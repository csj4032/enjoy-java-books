package ch05;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;
}
