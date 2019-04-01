package chapter02.item01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Item01Test {

	@Test
	public void item01Test() {
		Services.registerDefaultProvider(DEFAULT_PROVIDER);
		Services.registerProvider("comp", COMP_PROVIDER);
		Services.registerProvider("armed", ARMED_PROVIDER);

		Service s1 = Services.newInstance();
		Service s2 = Services.newInstance("comp");
		Service s3 = Services.newInstance("comp");

		assertEquals(s1.toString(), "Default Service");
		assertEquals(s2, s3);

		System.out.printf("%s, %s, %s%n", s1, s2, s3);
	}

	private static Provider DEFAULT_PROVIDER = (() ->
			(new Service() {
				@Override
				public String toString() {
					return "Default Service";
				}
			}));

	private static Provider COMP_PROVIDER = (() ->
			new Service() {
				@Override
				public String toString() {
					return "Complementary service";
				}
			});

	private static Provider ARMED_PROVIDER = (() ->
			new Service() {
				@Override
				public String toString() {
					return "Armed service";
				}
			});
}

