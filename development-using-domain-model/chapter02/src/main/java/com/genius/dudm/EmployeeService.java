package com.genius.dudm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

public class EmployeeService {

	private static final String SELECT_EMPLOYEE = """
			SELECT
				E.NO AS EMPLOYEE_NO,
				E.NAME AS EMPLOYEE_NAME,
				E.POSITION,
				D.NO AS DEPARTMENT_NO,
				D.NAME AS DEPARTMENT_NAME,
				D.ADDRESS
			FROM
				EMPLOYEE AS E, DEPARTMENT AS D
			WHERE
				E.DEPARTMENT_NO = D.NO;
			""";

	private static final String SELECT_EMPLOYEE_BY_DEPARTMENT = """
			SELECT
				E.NO AS EMPLOYEE_NO,
				E.NAME AS EMPLOYEE_NAME,
				E.POSITION,
				D.NO AS DEPARTMENT_NO,
				D.NAME AS DEPARTMENT_NAME,
				D.ADDRESS
			FROM
				EMPLOYEE AS E, DEPARTMENT AS D
			WHERE
				E.DEPARTMENT_NO = D.NO
				AND D.NO = ?;
			""";

	public List<Employee> findAllEmployee() {
		return find(SELECT_EMPLOYEE, null);
	}

	public List<Employee> findAllEmployeeByDepartment(Department department) {
		return find(SELECT_EMPLOYEE_BY_DEPARTMENT, new Object[]{department.getNo()});
	}

	private List<Employee> find(String query, @Nullable Object[] params) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> employees = new ArrayList<>();
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			int size = params == null ? 0 : params.length;
			for (int i = 0; i < size; i++) {
				preparedStatement.setObject(i + 1, params[i]);
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employees.add(load(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return employees;
	}

	private Employee load(ResultSet resultSet) throws SQLException {
		Department department = new Department(resultSet.getLong("DEPARTMENT_NO"), resultSet.getString("DEPARTMENT_NAME"), resultSet.getString("ADDRESS"));
		return new Employee(resultSet.getLong("EMPLOYEE_NO"), resultSet.getString("EMPLOYEE_NAME"), resultSet.getString("POSITION"), department);
	}
}