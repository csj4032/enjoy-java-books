package jooqZip;

import org.jooq.lambda.Seq;

import java.util.stream.Collectors;

public class JooqZip {

	public static void main(String[] args) {
		Seq.of("2017/06/27", "2017/06/26", "2017/06/27", "2017/06/26", "2017/06/28").zip(Seq.of(30, 50, 90, 120, 20)).groupBy(x -> x.v1, Collectors.summingInt(x -> x.v2));
	}
}
