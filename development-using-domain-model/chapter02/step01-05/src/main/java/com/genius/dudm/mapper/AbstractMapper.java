package com.genius.dudm.mapper;

import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.domain.DomainObject;
import com.genius.dudm.infrastructure.DatabaseManager;
import com.genius.dudm.infrastructure.QuerySource;
import com.genius.dudm.infrastructure.TransactionManager;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<T extends DomainObject, K extends DomainKey> implements Mapper {

	protected abstract String getFindAllSql();

	protected abstract String getFindByKeySql();

	protected abstract T doLoad(ResultSet resultSet) throws Exception;

	protected abstract K getKey(ResultSet resultSet) throws SQLException;

	public T load(ResultSet resultSet) throws Exception {
		K domainKey = getKey(resultSet);
		if (TransactionManager.getTransactionManager().containsInPool(domainKey)) {
			return (T) TransactionManager.getTransactionManager().getObjectFromPool(domainKey);
		} else {
			T domainObject = doLoad(resultSet);
			TransactionManager.getTransactionManager().addObjectToPool(domainObject);
			return domainObject;
		}
	}

	@NotNull
	protected List<T> find(QuerySource source) throws Exception {
		ResultSet resultSet = null;
		List<T> result = new ArrayList();
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

	public T findByKey(DomainKey domainKey) throws Exception {
		QuerySource querySource = new QuerySource(getFindByKeySql(), domainKey.getKeyFields());
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

	public List<T> findAll() throws Exception {
		return find(new QuerySource(getFindAllSql()));
	}

	protected ResultSet executeQuery(QuerySource source) throws Exception {
		PreparedStatement preparedStatement = source.getPreparedStatement();
		for (int i = 0; i < source.getParameterSize(); i++) {
			preparedStatement.setObject(i + 1, source.getParameter(i));
		}
		return preparedStatement.executeQuery();
	}

}
