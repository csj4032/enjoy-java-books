package com.genius.payroll;

import lombok.Getter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {

	@Getter
	private final double rate;
	private Map<LocalDate, TimeCard> timeCards = new HashMap<>();

	public HourlyClassification(double hourlyRate) {
		this.rate = hourlyRate;
	}

	@Override
	public double calculatePay(Paycheck paycheck) {
		timeCards.values().stream()
				.filter(e -> (paycheck.getPayPeriodStartDate().isAfter(e.getDate())))
				.filter(e -> (paycheck.getPayPeriodEndDate().isBefore(e.getDate())))
				.mapToDouble(e -> calculatePayForTime(e))
				.sum();
		return 0;
	}

	private double calculatePayForTime(TimeCard timeCard) {
		double hours = timeCard.getHours();
		double overtime = Math.max(0.0, hours - 8.0);
		double straightTime = hours - overtime;
		return (straightTime * rate) + (overtime * hours * 1.5);
	}

	public void addTimeCard(TimeCard timeCard) {
		timeCards.put(timeCard.getDate(), timeCard);
	}

	public TimeCard getTimeCard(LocalDate date) {
		return timeCards.get(date);
	}
}
