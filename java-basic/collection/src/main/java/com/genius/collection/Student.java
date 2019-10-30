package com.genius.collection;

import lombok.*;

import java.util.Comparator;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "grade"})
public class Student implements Comparator {
	private long id;
	private String name;
	private Grade grade;
	private Integer point;

	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
}