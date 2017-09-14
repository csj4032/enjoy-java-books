package com.genius.over;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OverYoung extends OverOld {

	@Override
	protected String send() throws RuntimeException {
		return "young";
	}

	//Overload
	private String send1(String string1, String string2) {
		return string1 + "Young" + string2;
	}

	private String send1(String string1, String string2, String string3) {
		return string1 + "Young" + string2 + string3;
	}

	public static void main(String[] args) {
		LocalDateTime.parse("20170515 12-50-50", DateTimeFormatter.ofPattern("yyyyMMdd hh-mm-ss")).getHour();
	}

}
