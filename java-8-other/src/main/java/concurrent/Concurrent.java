package concurrent;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Concurrent {

	public static void main(String[] args) {
		List list = Collections.synchronizedList(Arrays.asList(1, 2, 3, 4));

		LinkedList<String> linkedList = new LinkedList(Arrays.asList("A", "B"));
		linkedList.forEach(System.out::print);


		ConcurrentMap concurrentMap = new ConcurrentHashMap();
	}
}
