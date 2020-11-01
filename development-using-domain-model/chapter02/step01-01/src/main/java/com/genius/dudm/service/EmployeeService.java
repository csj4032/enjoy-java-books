package com.genius.dudm.service;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.Employee;
import com.genius.dudm.infrastructure.DatabaseManager;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {

	private String selectSql = """
			SELECT 
				E.ID, 
				E.NAME, 
				E.POSITION,
				D.ID AS DEPARTMENT_ID,
				D.NAME AS DEPARTMENT_NAME,
				D.ADDRESS
			FROM 
				EMPLOYEE AS E, DEPARTMENT AS D 
			WHERE
				E.DEPARTMENT_ID = D.ID
			""";

	public List<Employee> findAllEmployee() throws SQLException {
		return find(selectSql, null);
	}

	public List<Employee> findEmployeeByDepartment(final Department parameter) throws SQLException {
		String findByDepartment = selectSql + " AND E.DEPARTMENT_ID = ?";
		return find(findByDepartment, new Object[]{parameter.getId()});
	}

	@NotNull
	private List<Employee> find(String sql, Object[] params) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> result = new ArrayList();
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			final int paramsLength = params != null ? params.length : 0;
			for (int i = 0; i < paramsLength; i++) preparedStatement.setObject(i + 1, params[i]);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = load(resultSet);
				result.add(employee);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@NotNull
	private Employee load(ResultSet resultSet) throws SQLException {
		Department department = new Department(resultSet.getLong("DEPARTMENT_ID"), resultSet.getString("DEPARTMENT_NAME"), resultSet.getString("ADDRESS"));
		Employee employee = new Employee(resultSet.getLong("ID"), resultSet.getString("NAME"), resultSet.getString("POSITION"), department);
		return employee;
	}
}