package com.genius.dudm.service;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.mapper.DepartmentMapper;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService<T extends DomainKey> {

	public List<Department> moveDepartment() throws Exception {
		List<Department> result = new ArrayList<>();
		DepartmentMapper departmentMapper = new DepartmentMapper();
		List<Department> departments = departmentMapper.findAll();
		for (Department department : departments) {
			if (department.move(2, "별층3층")) {
				result.add(department);
			}
		}
		return result;
	}

	public Department findDepartmentByKey(T departmentKey) throws Exception {
		DepartmentMapper departmentMapper = new DepartmentMapper();
		return (Department) departmentMapper.findByKey(departmentKey);
	}
}
