package enumLookup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardMain {

	public static void main(String[] args) {
		List<String> names = Arrays.stream(CardSuit.values()).map(e -> e.name()).collect(Collectors.toList());
		System.out.println(names);
	}
}