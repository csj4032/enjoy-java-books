package com.genius.dudm.service;

import com.genius.dudm.domain.*;
import com.genius.dudm.infrastructure.DatabaseManager;
import com.genius.dudm.mapper.EmployeeMapper;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService<T extends DomainKey> {

	public Employee findEmployeeByKey(T employeeKey) throws Exception {
		EmployeeMapper employeeMapper = new EmployeeMapper();
		return (Employee) employeeMapper.findByKey(employeeKey);
	}

	public List<Employee> findAllEmployee() throws Exception {
		final var employeeMapper = new EmployeeMapper();
		return employeeMapper.findAll();
	}

	public List<Employee> findEmployeeByDepartment(final Department department) throws Exception {
		final var employeeMapper = new EmployeeMapper();
		return employeeMapper.findByDepartment(department);
	}

	public void printForMove(List<Employee> employees) {
		for (Employee employee : employees) {
			employee.printForMove();
		}
	}

}