package com.genius.payroll.transaction;

import com.genius.payroll.exception.InvalidEmployeeException;

public interface Transaction {

	void execute() throws InvalidEmployeeException;
}