package chapter05.item33;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// https://docs.oracle.com/javase/tutorial/java/generics/wildcardGuidelines.html
public class Favorites {

	private Map<Class<?>, Object> favorites = new HashMap<>();

	public <T> void putFavorite(Class<T> type, T instance) {
		favorites.put(Objects.requireNonNull(type), instance);
	}

	public <T> T getFavorite(Class<T> type) {
		return type.cast(favorites.get(type));
	}

	public static void main(String[] args) {
		Favorites favorites = new Favorites();
	}
}