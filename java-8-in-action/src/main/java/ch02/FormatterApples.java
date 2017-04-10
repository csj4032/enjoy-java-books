package ch02;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FormatterApples {

	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
		filter(inventory, new AppleSimpleFormatter());
	}

	static void filter(List<Apple> inventory, AppleFormatter f) {
		for (Apple apple : inventory) {
			System.out.println(f.accept(apple));
		}
	}

	public interface AppleFormatter {
		String accept(Apple a);
	}

	static class AppleSimpleFormatter implements AppleFormatter {
		@Override
		public String accept(Apple apple) {
			return "An apple of " + apple.getWeight() + " g";
		}
	}
}
