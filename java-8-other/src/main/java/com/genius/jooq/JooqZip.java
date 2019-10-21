package com.genius.jooq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jooq.lambda.Seq;

import java.util.List;
import java.util.stream.Collectors;

public class JooqZip {

	public static void main(String[] args) {
		Integer[] nos = new Integer[]{1, 2, 3, 4};
		String[] names = new String[]{"A", "B", "C", "D"};
		Integer[] ages = new Integer[]{10, 20, 30, 40};
		List<Joo> joos = Seq.zip(Seq.of(nos), Seq.of(names), Seq.of(ages)).map(t -> new Joo(t.v1, t.v2, t.v3)).collect(Collectors.toList());
		//Seq.of("2017/06/27", "2017/06/26", "2017/06/27", "2017/06/26", "2017/06/28").zip(Seq.of(30, 50, 90, 120, 20)).groupBy(x -> x.v1, Collectors.summingInt(x -> x.v2));
	}
}

@Getter
@Setter
@ToString
@AllArgsConstructor
class Joo {
	private int no;
	private String name;
	private int age;
}
