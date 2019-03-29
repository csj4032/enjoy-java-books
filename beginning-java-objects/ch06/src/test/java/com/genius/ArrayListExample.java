package com.genius;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class ArrayListExample {

	@Test
	public void arrayListTest() {
		var students = List.of(new Student("홍길등"), new Student("안철수"));
		for (var student : students) {
			log.info("{}", student);
		}
	}
}