package com.genius.dudm.mapper;

import com.genius.dudm.domain.Department;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentMapper extends AbstractMapper<Department> {

	private static final String selectSql = """
			SELECT
				NO,
				NAME,
				ADDRESS
			FROM
				DEPARTMENT
			""";

	@Override
	protected String getFindByKey() {
		return selectSql + """
				WHERE
					NO = ?
				""";
	}

	@Override
	public List<Department> findAll() {
		return find(selectSql, null);
	}

	protected Department load(@NotNull ResultSet resultSet) throws SQLException {
		return new Department(resultSet.getLong("NO"), resultSet.getString("NAME"), resultSet.getString("ADDRESS"));
	}
}