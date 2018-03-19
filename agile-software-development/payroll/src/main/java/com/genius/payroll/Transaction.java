package com.genius.payroll;

public interface Transaction {

	void execute() throws InvalidEmployeeException;
}