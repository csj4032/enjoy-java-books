package chapter05.item28;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReifiableTest {

	@Test
	public void reifiableMethod() {
		Reifiable reifiable = new Reifiable();
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		reifiable.varargsMethod(list1, list2);
	}
}
