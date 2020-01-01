package com.genius.srs;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CourseCatalog 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseCatalogTest {

	@Test
	@Order(1)
	public void display() {
		CourseCatalog courseCatalog = new CourseCatalog();
		courseCatalog.display();
	}
}