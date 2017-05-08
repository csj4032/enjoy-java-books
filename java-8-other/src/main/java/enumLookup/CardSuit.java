package enumLookup;

import com.google.common.base.Enums;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.Function;

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

	public String getDisplayName() {
		return displayName;
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

	static {
		for (CardSuit suit : CardSuit.values()) {
			nameIndex.put(suit.name(), suit);
		}
	}

	public static CardSuit lookupByName(String name) {
		return nameIndex.get(name);
	}

	public static CardSuit getIfPresent(String name) {
		return Enums.getIfPresent(CardSuit.class, name).orNull();
	}

	private static final Map<String, CardSuit> displayNameIndex = Maps.newHashMapWithExpectedSize(CardSuit.values().length);

	static {
		for (CardSuit suit : CardSuit.values()) {
			displayNameIndex.put(suit.getDisplayName(), suit);
		}
	}

	private static final Function<String, CardSuit> func = EnumUtils.lookupMap(CardSuit.class, e -> e.getDisplayName());

	public static CardSuit lookupByDisplayNameUtil(String displayName) {
		return func.apply(displayName);
	}
}