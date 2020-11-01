package com.genius.dudm.mapper;

import com.genius.dudm.domain.*;
import com.genius.dudm.infrastructure.DatabaseManager;
import com.genius.dudm.infrastructure.QuerySource;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.genius.dudm.infrastructure.DatabaseManager.close;

public class DepartmentMapper implements Mapper<Department> {

	private static final String SELECT_SQL = """
			SELECT
				D.ID,
				D.NAME,
				D.ADDRESS
			FROM
				DEPARTMENT D
			""";

	@Override
	public DomainObject findByKey(DomainKey key) throws Exception {
		final String findByKeySql = SELECT_SQL + "	AND D.ID = ?";
		QuerySource querySource = new QuerySource(findByKeySql, key.getKeyFields());
		ResultSet resultSet = null;
		try {
			resultSet = executeQuery(querySource);
			if (resultSet.next()) {
				return load(resultSet);
			} else {
				throw new Exception("해당 객체가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			querySource.close();
			DatabaseManager.close(resultSet);
		}
	}

	@Override
	public List<Department> findAll() throws Exception {
		final String findAllSql = SELECT_SQL;
		return find(new QuerySource(findAllSql));
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
	private List<Department> find(QuerySource source) throws Exception {
		ResultSet resultSet = null;
		List<Department> result = new ArrayList();
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
	private Department load(ResultSet resultSet) throws SQLException {
		return new Department(resultSet.getLong("ID"), resultSet.getString("NAME"), resultSet.getString("ADDRESS"));
	}
}