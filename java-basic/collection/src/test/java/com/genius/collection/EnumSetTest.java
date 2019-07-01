package com.genius.collection;

import org.junit.jupiter.api.*;

import java.util.EnumSet;
import java.util.Set;

@DisplayName("EnumSet")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EnumSetTest {

	@Test
	@Order(1)
	@DisplayName("EnumSet 확인")
	public void enumSet() {
		Set<CollectionEnumSet> set = EnumSet.allOf(CollectionEnumSet.class);
		Assertions.assertEquals(10, set.size());
		Assertions.assertTrue(set.contains(CollectionEnumSet.List));
	}
}