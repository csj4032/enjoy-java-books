package com.genius.duality;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sample {

	// 학생(점수), 반, 반평균, 1등반 꼴등반
	public static void main(String[] args) {
		List<Clazz> clazzes = new ArrayList<>();
		clazzes.add(new Clazz("1반", List.of(new Student("201901", 10), new Student("201902", 10), new Student("201903", 10))));
		clazzes.add(new Clazz("2반", List.of(new Student("201904", 10), new Student("201905", 100), new Student("201906", 10))));

		Clazz first = new Clazz();
		Clazz last = new Clazz();
		int max = 0;
		int min = 100000000;
		for (Clazz clazz : clazzes) {
			int sum = clazz.getTotalPoint();
			if(sum > max) {
				max = sum;
				first = clazz;
			}
			if(sum < min) {
				last = clazz;
				min = sum;
			}
		}

		System.out.println(first.getName());
		System.out.println(last.getName());
	}
}