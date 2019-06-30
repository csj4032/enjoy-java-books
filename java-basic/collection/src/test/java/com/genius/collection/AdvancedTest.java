package com.genius.collection;

import org.junit.jupiter.api.*;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdvancedTest {

	private static List<Student> students = new ArrayList();

	@BeforeAll
	public static void setUp() {
		Student sf1 = Student.builder().id(1).name("가길동").grade(Grade.Freshman).point(100).build();
		Student sf2 = Student.builder().id(2).name("나길동").grade(Grade.Freshman).point(93).build();
		Student sf3 = Student.builder().id(3).name("다길동").grade(Grade.Freshman).point(98).build();
		Student sf4 = Student.builder().id(4).name("라길동").grade(Grade.Freshman).point(99).build();
		Student sf5 = Student.builder().id(5).name("마길동").grade(Grade.Freshman).point(89).build();

		Student ss1 = Student.builder().id(1).name("가철수").grade(Grade.Sophomore).point(100).build();
		Student ss2 = Student.builder().id(2).name("나철수").grade(Grade.Sophomore).point(93).build();
		Student ss3 = Student.builder().id(3).name("다철수").grade(Grade.Sophomore).point(88).build();
		Student ss4 = Student.builder().id(4).name("라철수").grade(Grade.Sophomore).point(99).build();
		Student ss5 = Student.builder().id(5).name("마철수").grade(Grade.Sophomore).point(94).build();

		Student sj1 = Student.builder().id(1).name("가영희").grade(Grade.Junior).point(100).build();
		Student sj2 = Student.builder().id(2).name("나영희").grade(Grade.Junior).point(93).build();
		Student sj3 = Student.builder().id(3).name("다영희").grade(Grade.Junior).point(88).build();
		Student sj4 = Student.builder().id(4).name("라영희").grade(Grade.Junior).point(99).build();
		Student sj5 = Student.builder().id(5).name("마영희").grade(Grade.Junior).point(94).build();

		Student se1 = Student.builder().id(1).name("가바둑").grade(Grade.Senior).point(100).build();
		Student se2 = Student.builder().id(2).name("나바둑").grade(Grade.Senior).point(93).build();
		Student se3 = Student.builder().id(3).name("다바둑").grade(Grade.Senior).point(88).build();
		Student se4 = Student.builder().id(4).name("라바둑").grade(Grade.Senior).point(99).build();
		Student se5 = Student.builder().id(5).name("마바둑").grade(Grade.Senior).point(94).build();

		students.add(sf1);
		students.add(sf2);
		students.add(sf3);
		students.add(sf4);
		students.add(sf5);

		students.add(ss1);
		students.add(ss2);
		students.add(ss3);
		students.add(ss4);
		students.add(ss5);

		students.add(sj1);
		students.add(sj2);
		students.add(sj3);
		students.add(sj4);
		students.add(sj5);

		students.add(se1);
		students.add(se2);
		students.add(se3);
		students.add(se4);
		students.add(se5);
	}

	@Test
	@Order(1)
	@DisplayName("전체 학생 중 1등")
	public void top1ByPoints() {
		Integer top1Point = 0;
		Student top1Student = new Student();
		for (Student student : students) {
			if (top1Point < student.getPoint()) {
				top1Point = student.getPoint();
				top1Student = student;
			}
		}
		Assertions.assertEquals("가길동", top1Student.getName());
	}

	@Test
	@Order(2)
	@DisplayName("전체 학생 중 1,2,3등")
	public void top3ByPoints() {
		List<Student> top3Students;
		students.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o2.getPoint().compareTo(o1.getPoint());
			}
		});
		top3Students = students.subList(0, 3);
		Assertions.assertEquals("가길동", top3Students.get(0).getName());
		Assertions.assertEquals("가철수", top3Students.get(1).getName());
		Assertions.assertEquals("가영희", top3Students.get(2).getName());
	}

	@Test
	@Order(4)
	@DisplayName("학년별 학생 리스트")
	public void mapByGrade() {
		Map<Grade, List<Student>> gradeListMap = new HashMap<>();
		for (Student student : students) {
			gradeListMap.putIfAbsent(student.getGrade(), new ArrayList<>());
			gradeListMap.get(student.getGrade()).add(student);
		}
		Assertions.assertEquals("[Sophomore, Junior, Senior, Freshman]", gradeListMap.keySet().toString());
	}
}
