package com.genius;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class ArrayListExample {

	@Test
	public void arrayListTest() {
		var students = List.of(new Student("Herbie"), new Student("Clem"), new Student("Oscar"));
		for (var student : students) {
			log.info("{}", student);
		}
	}
}