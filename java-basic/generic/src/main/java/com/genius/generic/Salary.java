package com.genius.generic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
public class Salary extends Employee {

	private static final BigDecimal DEFAULT_MONEY = new BigDecimal(1000);
	private final BigDecimal money;

	public Salary() {
		this.money = DEFAULT_MONEY;
	}

	public Salary(int id, LocalDate birth, String name, BigDecimal money) {
		super(birth, name, id);
		this.money = money;
	}
}
