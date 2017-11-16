package chapter04.item18;

import java.util.Map;

public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {

	public abstract K getKey();
	public abstract V getValue();

	public V setValue(V value) {
		throw new UnsupportedOperationException();
	}


}
