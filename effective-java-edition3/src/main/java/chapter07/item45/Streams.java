package chapter07.item45;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Streams {

	public Stream<String> matcher(Pattern pattern, String input) {
		return pattern.splitAsStream(input);
	}
}
