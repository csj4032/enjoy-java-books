package ch06;

import com.google.common.collect.Lists;

import java.util.List;

public class ArrayListExample {

	public static void main(String[] args) {
		List<Student> students = Lists.newArrayList(new Student("Herbie"), new Student("Herbie"), new Student("Herbie"));
		students.forEach(e -> System.out.println(e.getName()));
	}
}