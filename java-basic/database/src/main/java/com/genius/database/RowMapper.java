package com.genius.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<E> {

	E mapRow(ResultSet resultSet, int rowNum) throws SQLException;
}
