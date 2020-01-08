package com.genius.dudm.service;

import com.genius.dudm.domain.Department;
import com.genius.dudm.mapper.DepartmentMapper;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService {

	public List<Department> moveDepartment() {
		List<Department> result = new ArrayList();
		DepartmentMapper departmentMapper = new DepartmentMapper();
		List<Department> departments = departmentMapper.findAll();
		for (Department department : departments) {
			if (department.move(2, "별관12층")) {
				result.add(department);
			}
		}
		return result;
	}
}