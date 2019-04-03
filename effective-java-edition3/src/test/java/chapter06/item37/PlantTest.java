package chapter06.item37;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class PlantTest {

	private Plant[] garden = new Plant[10];

	@BeforeEach
	public void setUp() {
		garden[0] = new Plant("A", Plant.LifeCycle.ANNUAL);
		garden[1] = new Plant("B", Plant.LifeCycle.BIENNIAL);
		garden[2] = new Plant("C", Plant.LifeCycle.PERENNIAL);
		garden[3] = new Plant("D", Plant.LifeCycle.ANNUAL);
		garden[4] = new Plant("E", Plant.LifeCycle.BIENNIAL);
		garden[5] = new Plant("F", Plant.LifeCycle.PERENNIAL);
		garden[6] = new Plant("G", Plant.LifeCycle.ANNUAL);
		garden[7] = new Plant("H", Plant.LifeCycle.BIENNIAL);
		garden[8] = new Plant("I", Plant.LifeCycle.PERENNIAL);
		garden[9] = new Plant("J", Plant.LifeCycle.PERENNIAL);
	}

	@Test
	public void gardenPrint() {
		Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
		for (int i = 0; i < plantsByLifeCycle.length; i++) plantsByLifeCycle[i] = new HashSet<>();
		for (Plant plant : garden) plantsByLifeCycle[plant.lifeCycle.ordinal()].add(plant);
		for (int i = 0; i < plantsByLifeCycle.length; i++) System.out.printf("%s: %s%n", Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
	}

	@Test
	public void gardenPrintByEnumMap() {
		Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
		for (Plant.LifeCycle lc : Plant.LifeCycle.values()) plantsByLifeCycle.put(lc, new HashSet<>());
		for (Plant p : garden) plantsByLifeCycle.get(p.lifeCycle).add(p);
		System.out.println(plantsByLifeCycle);
	}
}