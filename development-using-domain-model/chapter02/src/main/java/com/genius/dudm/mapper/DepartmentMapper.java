package com.genius.dudm.mapper;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.Employee;
import com.genius.dudm.infrastructure.DatabaseManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		return find(selectSql, null);
	}

	@Override
	public Department findById(long id) {
		return new Department(0, "", "");
	}

	private List<Department> find(@NotNull String query, @Nullable Object[] params) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Department> departments = new ArrayList<>();
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			int size = params == null ? 0 : params.length;
			for (int i = 0; i < size; i++) {
				preparedStatement.setObject(i + 1, params[i]);
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				departments.add(load(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return departments;
	}

	private Department load(@NotNull ResultSet resultSet) throws SQLException {
		return new Department(resultSet.getLong("NO"), resultSet.getString("NAME"), resultSet.getString("ADDRESS"));
	}
}