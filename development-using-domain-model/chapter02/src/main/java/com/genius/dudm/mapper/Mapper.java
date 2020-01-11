package com.genius.dudm.mapper;

import com.genius.dudm.domain.DomainKey;

import java.util.List;

public interface Mapper<T> {

	List<T> findAll();

	T findByKey(DomainKey key);
}