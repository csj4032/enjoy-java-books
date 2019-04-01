package com.genius.basic.chapter01;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("Hello World! " + (args.length == 0 ? "" : args[0]));
	}
}