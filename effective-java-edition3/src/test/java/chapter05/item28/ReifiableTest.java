package chapter05.item28;



import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static chapter05.item28.Reifiable.varargsGenericMethod;
import static chapter05.item28.Reifiable.varargsMethod;

public class ReifiableTest {

	@Test
	public void reifiableMethod() {
		List<Object> list = new ArrayList<>();
		list.add(1);
		varargsMethod("A", "B");
		varargsGenericMethod(list);
	}
}
