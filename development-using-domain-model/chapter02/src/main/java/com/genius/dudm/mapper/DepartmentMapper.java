package com.genius.dudm.mapper;

import com.genius.dudm.domain.Department;
import com.genius.dudm.domain.DomainKey;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentMapper extends AbstractMapper<Department> {

	private static final String SELECT_SQL = """
			SELECT
				D.ID,
				D.NAME,
				D.ADDRESS
			FROM
				DEPARTMENT D
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
		return new Department(resultSet.getLong("D.ID"), resultSet.getString("D.NAME"), resultSet.getString("D.ADDRESS"));
	}

	@Override
	protected DomainKey getKey(ResultSet resultSet) throws Exception {
		return null;
	}

	protected Department load(@NotNull ResultSet resultSet) throws SQLException {
		return new Department(resultSet.getLong("D.ID"), resultSet.getString("D.NAME"), resultSet.getString("D.ADDRESS"));
	}
}