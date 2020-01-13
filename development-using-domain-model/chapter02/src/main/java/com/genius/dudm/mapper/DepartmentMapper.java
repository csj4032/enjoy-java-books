package com.genius.dudm.mapper;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.infrastructure.DatabaseManager;
import com.genius.dudm.domain.QuerySource;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	public List<Department> findAll() {
		return find(SELECT_SQL, null);
	}

	@Override
	protected Department doLoad(ResultSet resultSet) throws Exception {
		return new Department(resultSet.getLong("D.ID"), resultSet.getString("D.NAME"), resultSet.getString("D.ADDRESS"), null);
	}

	@Override
	protected DomainKey getKey(ResultSet resultSet) throws Exception {
		return null;
	}

	protected Department load(@NotNull ResultSet resultSet) throws SQLException {
		return new Department(resultSet.getLong("D.ID"), resultSet.getString("D.NAME"), resultSet.getString("D.ADDRESS"), null);
	}

	@Override
	public Department findByKey(DomainKey key) {
		QuerySource source = new QuerySource(getFindByKeySql(), key.getKeyFields());
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(getFindByKeySql());
			for (int i = 0; i < key.getKeyFields().length; i++)
				preparedStatement.setObject(i + 1, key.getKeyFields()[i]);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) return load(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}
}