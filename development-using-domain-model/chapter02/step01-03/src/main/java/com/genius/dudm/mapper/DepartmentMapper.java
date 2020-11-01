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
	public List<Department> findAll() throws Exception {
		final String findAllSql = SELECT_SQL;
		return find(new QuerySource(findAllSql));
	}

	@Override
	protected String getFindAllSql() {
		return SELECT_SQL;
	}

	@Override
	protected String getFindByKeySql() {
		return SELECT_SQL + "	AND D.ID = ?";
	}

	@Override
	protected Department load(ResultSet resultSet) throws SQLException {
		return new Department(resultSet.getLong("DEPARTMENT_ID"), resultSet.getString("DEPARTMENT_NAME"), resultSet.getString("ADDRESS"));
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
}