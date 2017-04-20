package ch05;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Trader {
	private final String name;
	private final String city;
}