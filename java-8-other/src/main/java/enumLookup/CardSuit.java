package enumLookup;

import com.google.common.collect.Maps;

import java.util.Map;

public enum CardSuit {

	SPADE("Spade", String.valueOf((char) 0x2660), CardColor.BLACK),
	HEART("Heart", String.valueOf((char) 0x2665), CardColor.RED),
	DIAMOND("Diamond", String.valueOf((char) 0x2666), CardColor.RED),
	CLUB("Club", String.valueOf((char) 0x2663), CardColor.BLACK);

	private String displayName;
	private String symbol;
	private CardColor color;

	CardSuit(String displayName, String symbol, CardColor color) {
		this.displayName = displayName;
		this.symbol = symbol;
		this.color = color;
	}


	public static CardSuit iterationFindByName(String name) {
		for (CardSuit suit : CardSuit.values()) {
			if (name.equals(suit.name())) {
				return suit;
			}
		}

		return null;
	}

	private static final Map<String, CardSuit> nameIndex = Maps.newHashMapWithExpectedSize(CardSuit.values().length);

}