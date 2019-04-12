package chapter07.item44;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class LinkedHashMapSubTest {

	private int capacity = 3;
	private float loadFactor = 0.75F;

	@Test
	public void entryRemoveFunction() {
		LinkedHashMapSub<Integer, String> linkedHashMapSubStatic = LinkedHashMapSub.getInstance(capacity, (a, b) -> a.size() > capacity);
		linkedHashMapSubStatic.put(1, "1");
		linkedHashMapSubStatic.put(2, "2");
		linkedHashMapSubStatic.put(3, "3");
		log.info("1. static : {}", linkedHashMapSubStatic.toString());
		linkedHashMapSubStatic.put(4, "4");
		log.info("2. static : {}", linkedHashMapSubStatic.toString());

		LinkedHashMapSub<Integer, String> linkedHashMapSub = new LinkedHashMapSub(capacity, loadFactor, (a, b) -> a.size() > capacity);
		linkedHashMapSub.put(1, "1");
		linkedHashMapSub.put(2, "2");
		linkedHashMapSub.put(3, "3");
		log.info("1. construct : {}", linkedHashMapSub.toString());
		linkedHashMapSub.put(4, "4");
		log.info("2. construct : {}", linkedHashMapSub.toString());
	}
}