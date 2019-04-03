package chapter06.item34;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class WeightTableTest {

	private WeightTable weightTable;
	private Planet planet;

	@BeforeEach
	public void setUp() {
		weightTable = new WeightTable();
	}

	@Test
	public void earthWeight_zero() {
		weightTable.tablePrint(0);
	}

	@Test
	public void mass_given_zero_surfaceWeight() {
		planet = Planet.EARTH;
		double result = planet.surfaceWeight(0L);
		assertThat(result, is(0.0));
	}
}
