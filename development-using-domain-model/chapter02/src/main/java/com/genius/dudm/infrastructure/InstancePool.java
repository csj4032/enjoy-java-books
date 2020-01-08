package com.genius.dudm.infrastructure;

import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.domain.DomainObject;

import java.util.HashMap;

public class InstancePool {

	private static InstancePool instance;
	private static ThreadLocal threadLocal = new ThreadLocal();
	private HashMap<DomainKey, DomainObject> objectPool;

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
		return instancePool;
	}

	public void addObjectToPool(DomainObject object) {
		objectPool.put(object.getKey(), object);
	}

	public DomainObject getObjectFromPool(DomainKey key) {
		return objectPool.get(key);
	}

	public boolean containsInPool(DomainKey key) {
		return objectPool.containsKey(key);
	}

	private void initPool() {
		objectPool = new HashMap<>();
	}
}