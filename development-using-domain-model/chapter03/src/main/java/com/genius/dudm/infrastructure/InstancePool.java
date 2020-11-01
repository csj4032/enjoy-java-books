package com.genius.dudm.infrastructure;

import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.domain.DomainObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class InstancePool {

	private static InstancePool instance;
	private static ThreadLocal threadLocal = new ThreadLocal();
	private Map<DomainKey, ? super DomainObject> objectPool;

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

	public <T extends DomainObject> void addObjectToPool(T object) {
		objectPool.put(object.getKey(), object);
		log.info("objectPool : {}", objectPool);
	}

	public <T extends DomainObject> T getObjectFromPool(DomainKey key) {
		return (T) objectPool.get(key);
	}

	public boolean containsInPool(DomainKey key) {
		return objectPool.containsKey(key);
	}

	private void initPool() {
		objectPool = new HashMap<>();
	}

	public void unload() {
		threadLocal.remove();
	}
}