package com.genius.dudm.mapper;

import com.genius.dudm.domain.Department;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DepartmentMapper implements Mapper<Department> {

	@Override
	public List<Department> findAll() {
		return Collections.emptyList();
	}

	@Override
	public Department findByKey() {
		return new Department(0, "", "");
	}

	@Override
	public int insert(Department department) {
		return 0;
	}

	@Override
	public int update(Department department) {
		return 0;
	}

	@Override
	public int delete(Department department) {
		return 0;
	}

	private List<Department> find(String query, @Nullable Object[] params) {
		return Collections.emptyList();
	}

	private Department load(ResultSet resultSet) throws SQLException {
		return new Department(0, "", "");
	}
}