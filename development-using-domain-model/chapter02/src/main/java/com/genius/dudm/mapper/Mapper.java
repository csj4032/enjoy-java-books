package com.genius.dudm.mapper;

import java.util.List;

public interface Mapper<T> {

	List<T> findAll();

	T findById(long id);

	int insert(T t);

	int update(T t);

	int delete(T t);
}