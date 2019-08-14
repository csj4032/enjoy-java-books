package com.genius.generic;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Salary extends Employee {

	private final BigDecimal money;

	public Salary(int id, String name, BigDecimal money) {
		super(id, name);
		this.money = money;
	}
}
