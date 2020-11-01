package com.genius.dudm.mapper;

import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.domain.DomainObject;

import java.util.List;

public interface Mapper<T> {

	T findByKey(DomainKey key) throws Exception;

	List<T> findAll() throws Exception;

	void insert(DomainObject domainObject) throws Exception;

	int update(DomainObject domainObject) throws Exception;

	int delete(DomainObject domainObject) throws Exception;
}
