package chapter12.item85;

import java.io.Serializable;

public class Genius implements Serializable {

	private String name;
	private int age;

	public Genius(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}