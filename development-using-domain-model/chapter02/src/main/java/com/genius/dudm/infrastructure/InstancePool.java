package com.genius.dudm.infrastructure;

import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.domain.DomainObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class InstancePool<T extends DomainObject> {

	private static InstancePool instance;
	private static ThreadLocal threadLocal = new ThreadLocal();
	private Map<DomainKey, T> objectPool;

	private InstancePool() {

	}

	public static InstancePool getInstance() {
		if (instance == null) instance = new InstancePool();
		return instance;
	}

	public static InstancePool getInstancePool() {
		InstancePool instancePool = (InstancePool) threadLocal.get();
		if (instancePool == null) {
			instancePool = new InstancePool();
			instancePool.initPool();
			threadLocal.set(instancePool);
		}
		System.out.println();
		return instancePool;
	}

	public void addObjectToPool(T object) {
		objectPool.put(object.getKey(), object);
		log.info("objectPool : {}", objectPool);
	}

	public T getObjectFromPool(DomainKey key) {
		return objectPool.get(key);
	}

	public boolean containsInPool(DomainKey key) {
		return objectPool.containsKey(key);
	}

	private void initPool() {
		objectPool = new HashMap<>();
	}
}