package com.genius.collection;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "grade"})
public class Student {
	private long id;
	private String name;
	private Grade grade;
	private int point;
}