package com.genius.payroll;

import com.genius.payroll.exception.InvalidEmployeeException;

public interface Transaction {

	void execute() throws InvalidEmployeeException;
}