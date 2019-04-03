package chapter06.item34;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum EnumType {

	CAT("cat", 1),
	DOG("dog", 2);
	//public static final EnumType PIG = new EnumType("pig", 3);

	private String name;
	private int ordinal;

	private static final Map<String, EnumType> stringToEnum = Stream.of(values()).collect(toMap(Object::toString, e -> e));

	public static Optional<EnumType> fromString(String symbol) {
		return Optional.ofNullable(stringToEnum.get(symbol));
	}

	EnumType(String name, int ordinal) {
		this.name = name;
		this.ordinal = ordinal;
	}
}