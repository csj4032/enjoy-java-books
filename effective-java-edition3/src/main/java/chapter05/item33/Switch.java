package chapter05.item33;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Switch {

	enum Type {
		A, B, C, D
	}

	public <K, V> Map<K, V> switchCase(Type type) {
		return switch (type) {
			case A, D -> new Hashtable();
			case B -> new HashMap();
			case C -> new ConcurrentHashMap();
			default -> null;
		};
	}

	public static void main(String[] args) {
		var map = new Switch() {{
		}}.switchCase(Type.A).get("A");
		System.out.println(map instanceof Hashtable);
	}
}
