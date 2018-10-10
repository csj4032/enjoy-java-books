package com.genius.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// lookaround, lookbehind
public class Regex {

	public static void main(String[] args) {
		String number1 = "10abcd";
		String number2 = "100000";
		String number3 = "100010";
		String number4 = "100030";
		String number5 = "101234";

		Pattern pattern = Pattern.compile("(?!(100010|100000|100030)).*(?<=(10\\d{4}))");

		Matcher matcher = pattern.matcher(number1);
		System.out.println(matcher.matches());

		Matcher matcher2 = pattern.matcher(number2);
		System.out.println(matcher2.matches());

		Matcher matcher3 = pattern.matcher(number3);
		System.out.println(matcher3.matches());

		Matcher matcher4 = pattern.matcher(number4);
		System.out.println(matcher4.matches());

		Matcher matcher5 = pattern.matcher(number5);
		System.out.println(matcher5.matches());
	}
}