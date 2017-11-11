package removeControlFlag.findIntBefore;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

	@Test
	public void testFound() {
		int[] data = {1, 9, 0, 2, 6, 7, 4, 5};
		boolean actual = FindInt.find(data, 5);
		boolean expected = true;
		assertEquals(expected, actual);
	}

	@Test
	public void testNoFound() {
		int[] data = {1, 9, 0, 2, 5, 6, 7, 4, 3};
		assertEquals(false, FindInt.find(data, 10));
	}
}