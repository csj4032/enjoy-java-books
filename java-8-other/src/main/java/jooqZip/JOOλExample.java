package jooqZip;

import org.jooq.lambda.Seq;

import java.util.List;

public class JOOλExample {

	public static void main(String[] args) {
		//Generating the Alphabet
		// List<String> alphabet = Arrays.asList("A", "B", "Z");
		List<String> alphabet = Seq.rangeClosed('A', 'Z').map(Object::toString).toList();
	}
}
