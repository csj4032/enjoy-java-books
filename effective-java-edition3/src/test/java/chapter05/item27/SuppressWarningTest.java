package chapter05.item27;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SuppressWarningTest {

	@Test
	public void suppressWarning() {
		var suppressWarning = new SuppressWarning();
		suppressWarning.setElements(new Number[]{1,2,3});
		var stings = suppressWarning.toArray(new Number[]{});
		System.out.println(Arrays.toString(stings));
	}
}