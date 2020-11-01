package com.genius.dudm.mapper;

import com.genius.dudm.domain.*;
import com.genius.dudm.infrastructure.DatabaseManager;
import com.genius.dudm.infrastructure.QuerySource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmployeeMapper implements Mapper<Employee> {

	private static final String SELECT_SQL = """
			SELECT
				E.ID AS EMPLOYEE_ID,
				E.NAME AS EMPLOYEE_NAME,
				E.POSITION,
				D.ID AS DEPARTMENT_ID,
				D.NAME AS DEPARTMENT_NAME,
				D.ADDRESS
			FROM
				EMPLOYEE AS E, DEPARTMENT AS D
			WHERE
				E.DEPARTMENT_ID = D.ID
			""";

	@Override
	public DomainObject findByKey(DomainKey key) throws Exception {
		return null;
	}

	@Override
	public List<Employee> findAll() throws Exception {
		final String findAllSql = SELECT_SQL;
		return find(new QuerySource(findAllSql));
	}

	public List<Employee> findByDepartment(Department department) throws Exception {
		final String findByDepartmentSql = SELECT_SQL + "	AND E.DEPARTMENT_ID = ?";
		return find(new QuerySource(findByDepartmentSql, new Object[]{department.getId()}));
	}

	@Override
	public void insert(DomainObject domainObject) throws Exception {

	}

	@Override
	public int update(DomainObject domainObject) throws Exception {
		return 0;
	}

	@Override
	public int delete(DomainObject domainObject) throws Exception {
		return 0;
	}

	@NotNull
	private List<Employee> find(QuerySource source) throws Exception {
		ResultSet resultSet = null;
		List<Employee> result = new ArrayList();
		try {
			resultSet = executeQuery(source);
			while (resultSet.next()) {
				result.add(load(resultSet));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			source.close();
			DatabaseManager.close(resultSet);
		}
		return result;
	}

	private ResultSet executeQuery(QuerySource source) throws Exception {
		PreparedStatement preparedStatement = source.getPreparedStatement();
		for (int i = 0; i < source.getParameterSize(); i++) {
			preparedStatement.setObject(i + 1, source.getParameter(i));
		}
		return preparedStatement.executeQuery();
	}

	@NotNull
	private Employee load(ResultSet resultSet) throws SQLException {
		Department department = new Department(resultSet.getLong("DEPARTMENT_ID"), resultSet.getString("DEPARTMENT_NAME"), resultSet.getString("ADDRESS"));
		Employee employee = new Employee(resultSet.getLong("ID"), resultSet.getString("NAME"), resultSet.getString("POSITION"), department);
		return employee;
	}
}