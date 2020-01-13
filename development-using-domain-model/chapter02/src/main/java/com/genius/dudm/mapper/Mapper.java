package com.genius.dudm.mapper;

import com.genius.dudm.domain.DomainKey;

import java.sql.SQLException;
import java.util.List;

public interface Mapper<T> {

	List<T> findAll();

	T findByKey(DomainKey key) throws SQLException;
}