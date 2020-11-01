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
public class EmployeeMapper extends AbstractMapper {

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

	public List<Employee> findByDepartment(Department department) throws Exception {
		final String findByDepartmentSql = SELECT_SQL + "	AND E.DEPARTMENT_ID = ?";
		return find(new QuerySource(findByDepartmentSql, new Object[]{department.getId()}));
	}

	@Override
	protected String getFindAllSql() {
		return SELECT_SQL;
	}

	@Override
	protected String getFindByKeySql() {
		return SELECT_SQL + "	AND E.DEPARTMENT_ID = ?";
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

	protected ResultSet executeQuery(QuerySource source) throws Exception {
		PreparedStatement preparedStatement = source.getPreparedStatement();
		for (int i = 0; i < source.getParameterSize(); i++) {
			preparedStatement.setObject(i + 1, source.getParameter(i));
		}
		return preparedStatement.executeQuery();
	}

	@NotNull
	protected Employee load(ResultSet resultSet) throws SQLException {
		DepartmentMapper departmentMapper = new DepartmentMapper();
		Employee employee = new Employee(resultSet.getLong("ID"), resultSet.getString("NAME"), resultSet.getString("POSITION"), departmentMapper.load(resultSet));
		return employee;
	}
}