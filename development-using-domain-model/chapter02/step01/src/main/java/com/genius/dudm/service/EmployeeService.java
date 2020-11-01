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

	public List<Employee> findAllEmployee() throws SQLException {
		String findAllSql = """
				SELECT 
					ID, 
					NAME, 
					POSITION
				FROM 
					EMPLOYEE 
				""";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List result = new ArrayList();
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(findAllSql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee(resultSet.getLong("ID"), resultSet.getString("NAME"), resultSet.getString("POSITION"), null);
				result.add(employee);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	public List<Employee> findEmployeeByDepartment(final Department parameter) throws SQLException {
		String findAllSql = """
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
					E.DEPARTMENT_ID = D.ID;
				""";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> result = new ArrayList();
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(findAllSql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Department department = new Department(resultSet.getLong("DEPARTMENT_ID"), resultSet.getString("DEPARTMENT_NAME"), resultSet.getString("ADDRESS"));
				Employee employee = new Employee(resultSet.getLong("ID"), resultSet.getString("NAME"), resultSet.getString("POSITION"), department);
				result.add(employee);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return result.stream().filter(e -> e.isBelongTo(parameter)).collect(Collectors.toList());
	}
}