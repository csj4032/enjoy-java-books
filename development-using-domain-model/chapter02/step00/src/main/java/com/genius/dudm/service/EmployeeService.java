package com.genius.dudm.service;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.Employee;
import com.genius.dudm.infrastructure.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {

	public List<Employee> findEmployeeByDepartment(final Department department) throws SQLException {
		return findAllEmployee().stream().filter(e-> e.isBelongTo(department)).collect(Collectors.toList());
	}

	public List<Employee> findAllEmployee() throws SQLException {
		String findAllSql = "SELECT ID, NAME, POSITION FROM EMPLOYEE";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List result = new ArrayList();
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(findAllSql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.add(new Employee(resultSet.getLong("ID"), resultSet.getString("NAME"), resultSet.getString("POSITION")));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return result;
	}
}