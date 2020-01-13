package com.genius.dudm.mapper;

import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.domain.DomainObject;
import com.genius.dudm.domain.QuerySource;
import com.genius.dudm.infrastructure.DatabaseManager;
import com.genius.dudm.infrastructure.InstancePool;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<T extends DomainObject> implements Mapper {

	protected abstract T doLoad(ResultSet resultSet) throws Exception;

	protected abstract DomainKey getKey(ResultSet resultSet) throws Exception;

	protected abstract String getFindAllSql();

	protected abstract String getFindByKeySql();

	public ResultSet executeQuery(QuerySource source) throws Exception {
		PreparedStatement preparedStatement = source.getPrepareStatement();
		for (int i = 0; i < source.getParameterSize(); i++) {
			preparedStatement.setObject(i + 1, source.getParameter(i));
		}
		return preparedStatement.executeQuery();
	}

	protected T load(ResultSet resultSet) throws Exception {
		DomainKey key = getKey(resultSet);
		if (InstancePool.getInstancePool().containsInPool(key)) {
			return InstancePool.getInstancePool().getObjectFromPool(key);
		} else {
			T result = doLoad(resultSet);
			InstancePool.getInstancePool().addObjectToPool(result);
			return result;
		}
	}

	protected List<T> find(String query, @Nullable Object[] params) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<T> employees = new ArrayList<>();
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(query);
			int size = params == null ? 0 : params.length;
			for (int i = 0; i < size; i++) {
				preparedStatement.setObject(i + 1, params[i]);
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employees.add(load(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return employees;
	}
}
