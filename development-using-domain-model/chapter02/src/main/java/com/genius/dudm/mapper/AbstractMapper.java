package com.genius.dudm.mapper;

import com.genius.dudm.infrastructure.DatabaseManager;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<T> implements Mapper {

	protected abstract String getFindByKey();
	protected abstract T load(ResultSet resultSet) throws SQLException;

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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return employees;
	}

	@Override
	public T findByKey(long key) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseManager.getConnection();
			preparedStatement = connection.prepareStatement(getFindByKey());
			preparedStatement.setObject(1, key);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return load(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.close(connection, preparedStatement, resultSet);
		}
		return null;
	}
}
