package com.genius.dudm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			WHERE E.DEPARTMENT_NO = D.NO;
			""";

	public List<Employee> findAllEmployee() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> employees = new ArrayList<>();
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Department department = new Department(resultSet.getLong("DEPARTMENT_NO"), resultSet.getString("DEPARTMENT_NAME"), resultSet.getString("ADDRESS"));
				employees.add(new Employee(resultSet.getLong("EMPLOYEE_NO"), resultSet.getString("EMPLOYEE_NAME"), resultSet.getString("POSITION"), department));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return employees;
	}

	public List<Employee> findAllEmployeeByDepartment(Department department) {
		return findAllEmployee().stream().filter(e -> e.isBelongsTo(department)).collect(toList());
	}
}