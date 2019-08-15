package com.genius.generic;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static com.genius.generic.Employee.DEFAULT_EMPLOYEE;

public class MaxEmployees {

	public Employee maxId(List<Employee> employees) {
		employees.stream().max(Comparator.comparing(new Function<Employee, Integer>() {
			@Override
			public Integer apply(Employee employee) {
				return employee.getNumber();
			}
		}, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		}));

		employees.stream().max(Comparator.comparing(new Function<Person, Integer>() {
			@Override
			public Integer apply(Person employee) {
				return employee.getNumber();
			}
		}, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		}));

		return employees.stream().max((Employee e1, Employee e2) -> e1.getId() - e2.getId()).orElse(DEFAULT_EMPLOYEE);
	}

	public Employee maxName(List<Employee> employees) {
		return employees.stream().max((Employee e1, Employee e2) -> e1.getName().compareTo(e2.getName())).orElse(DEFAULT_EMPLOYEE);
	}
}