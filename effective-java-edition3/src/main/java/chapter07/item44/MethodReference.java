package chapter07.item44;

import java.util.LinkedHashMap;
import java.util.Map;

public class MethodReference {

	private void mergeAndIncrement(Map<String, Integer> map, String key, Integer value) {
		map.merge(key, value, Integer::sum);

		LinkedHashMap linkedHashMap;
	}
}
