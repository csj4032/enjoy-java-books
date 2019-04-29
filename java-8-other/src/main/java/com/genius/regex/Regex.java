package com.genius.regex;

import javax.script.ScriptException;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	public static void main(String[] args) throws ScriptException {
		Predicate<String> predicate = Pattern.compile("(?!^\\d+$)(?!^_+$)^[\\w]{1,50}$").asPredicate();
		List<String> list = List.of("1", "_", "1_", "_1", "_event", "event_", "event", "201901_event", "event_201901");
		list.stream().filter(predicate).forEach(System.out::println);

		Pattern pattern = Pattern.compile("((a)(b)(c))");
		Matcher matcher = pattern.matcher("abc");
		while (matcher.find()) {
			System.out.println("group 1: " + matcher.group(1));
			System.out.println("group 2: " + matcher.group(2));
			System.out.println("group 3: " + matcher.group(3));
			System.out.println("group 4: " + matcher.group(4));
		}
	}
}