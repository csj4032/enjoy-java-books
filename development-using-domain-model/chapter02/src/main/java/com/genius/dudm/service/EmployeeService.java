package com.genius.dudm.service;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.Employee;
import com.genius.dudm.mapper.EmployeeMapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EmployeeService {

	public List<Employee> findAllEmployee() {
		return new EmployeeMapper().findAll();
	}

	public List<Employee> findAllEmployeeByDepartment(@NotNull Department department) {
		return new EmployeeMapper().findByDepartment(department);
	}
}