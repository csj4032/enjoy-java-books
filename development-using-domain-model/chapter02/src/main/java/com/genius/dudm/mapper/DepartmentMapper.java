package com.genius.dudm.mapper;

import com.genius.dudm.domain.Department;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DepartmentMapper implements Mapper<Department> {

	private static final String selectSql = """
			SELECT
				NO,
				NAME,
				ADDRESS
			FROM
				DEPARTMENT
			""";

	@Override
	public List<Department> findAll() {
		return Collections.emptyList();
	}

	@Override
	public Department findById(long id) {
		return new Department(0, "", "");
	}

	@Override
	public int insert(@NotNull Department department) {
		return 0;
	}

	@Override
	public int update(@NotNull Department department) {
		return 0;
	}

	@Override
	public int delete(@NotNull Department department) {
		return 0;
	}

	private List<Department> find(@NotNull String query, @Nullable Object[] params) {
		return Collections.emptyList();
	}

	private Department load(@NotNull ResultSet resultSet) throws SQLException {
		return new Department(resultSet.getLong("NO"), resultSet.getString("NAME"), resultSet.getString("ADDRESS"));
	}
}