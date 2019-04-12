package chapter07.item44;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class LinkedHashMapSub<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 1L;
	protected int capacity;
	protected EldestEntryRemovalFunction<K, V> function;
	protected BiPredicate<Map, Map.Entry> biFunction;

	public static <K, V> LinkedHashMapSub getInstance(int capacity, BiPredicate<Map<K, V>, Map.Entry<K, V>> biFunction) {
		return new LinkedHashMapSub(capacity, biFunction);
	}

	public LinkedHashMapSub(int capacity, BiPredicate<Map, Map.Entry> biFunction) {
		super(capacity, 0.75F, true);
		this.capacity = capacity;
		this.biFunction = biFunction;
	}

	protected LinkedHashMapSub(int capacity, float loadFactor, EldestEntryRemovalFunction<K, V> function) {
		super(capacity, loadFactor, true);
		this.capacity = capacity;
		this.function = function;
	}

	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		if (biFunction == null) {
			return function.remove(this, eldest);
		} else {
			return biFunction.test(this, eldest);
		}
	}
}
