package com.genius.dudm.mapper;

import com.genius.dudm.domain.*;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.genius.dudm.infrastructure.DatabaseManager.close;

public class DepartmentMapper extends AbstractMapper<Department> {

	private static final String SELECT_SQL = """
			SELECT
				D.ID,
				D.NAME,
				D.ADDRESS,
				E.ID AS EMPLOYEE_ID,
				E.NAME AS EMPLOYEE_NAME,
				E.POSITION
			FROM
				DEPARTMENT D
				INNER JOIN EMPLOYEE E ON D.ID = E.DEPARTMENT_ID
			""";

	@Override
	protected String getFindAllSql() {
		return SELECT_SQL;
	}

	@Override
	protected String getFindByKeySql() {
		return SELECT_SQL + " WHERE D.ID = ? ";
	}

	@Override
	protected DepartmentKey getKey(ResultSet resultSet) throws Exception {
		return new DepartmentKey(resultSet.getLong("D.ID"));
	}

	@Override
	public List<Department> findAll() {
		QuerySource source = new QuerySource(getFindAllSql());
		ResultSet resultSet = null;
		try {
			resultSet = executeQuery(source);
			DepartmentLoader loader = new DepartmentLoader(this, new EmployeeMapper());
			return loader.load(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet);
			source.close();
		}
		return null;
	}

	@Override
	public Department findByKey(DomainKey key) {
		QuerySource source = new QuerySource(getFindByKeySql(), key.getKeyFields());
		ResultSet resultSet = null;
		try {
			resultSet = executeQuery(source);
			DepartmentLoader loader = new DepartmentLoader(this, new EmployeeMapper());
			List<Department> departments = loader.load(resultSet);
			return departments.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet);
			source.close();
		}
		return null;
	}

	@Override
	protected Department doLoad(ResultSet resultSet) throws Exception {
		return new Department(resultSet.getLong("D.ID"), resultSet.getString("D.NAME"), resultSet.getString("D.ADDRESS"), new ArrayList<>());
	}


	protected Department load(@NotNull ResultSet resultSet) throws SQLException {
		return new Department(resultSet.getLong("D.ID"), resultSet.getString("D.NAME"), resultSet.getString("D.ADDRESS"), new ArrayList<>());
	}
}