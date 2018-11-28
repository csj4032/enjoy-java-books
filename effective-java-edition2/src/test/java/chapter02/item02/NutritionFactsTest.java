package chapter02.item02;

import chapter02.item02.telescopingconstructor.NutritionFacts;
import org.junit.Assert;
import org.junit.Test;

public class NutritionFactsTest {

	@Test
	public void nutrition() {
		NutritionFacts nutritionFacts1 = new NutritionFacts(240, 8);
		NutritionFacts nutritionFacts2 = new NutritionFacts(240, 8, 100, 0, 35, 27);

		Assert.assertEquals(nutritionFacts1.getServingSize(), nutritionFacts2.getServingSize());
	}
}