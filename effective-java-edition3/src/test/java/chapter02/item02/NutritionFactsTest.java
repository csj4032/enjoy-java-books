package chapter02.item02;

import chapter02.item02.builder.NutritionFacts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NutritionFactsTest {

	@Test
	public void nutritionFactsBuilder() {
		NutritionFacts cocaCola = new NutritionFacts.Builder(240, 0).calories(100).sodium(35).carbohydrate(27).build();
		Assertions.assertEquals(20, cocaCola.getCalories());
	}

	@Test
	public void nutritionFactsJavaBeans() {
		chapter02.item02.javabeans.NutritionFacts cocaCola = new chapter02.item02.javabeans.NutritionFacts();
		cocaCola.setServingSize(240);
		cocaCola.setServings(8);
		cocaCola.setCalories(100);
		cocaCola.setSodium(35);
		cocaCola.setCarbohydrate(27);
		Assertions.assertEquals(200, cocaCola.getCalories());
	}

	@Test
	public void nutritionFactsTelescoping() {
		chapter02.item02.telescopingconstructor.NutritionFacts cocaCola = new chapter02.item02.telescopingconstructor.NutritionFacts(240, 8, 100, 0, 35, 27);
		Assertions.assertEquals(240, cocaCola.getServingSize());
	}
}