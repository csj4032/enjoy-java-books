package com.genius.dudm.infrastructure;

import com.genius.dudm.domain.DomainKey;
import com.genius.dudm.domain.DomainObject;

import java.util.HashMap;
import java.util.Map;

public class TransactionManager<K extends DomainKey, V extends DomainObject> {

	private static ThreadLocal<TransactionManager> threadLocal = new ThreadLocal();
	private Map<K, V> objectPool;

	public static TransactionManager getTransactionManager() {
		TransactionManager transactionManager = threadLocal.get();
		if (transactionManager == null) {
			transactionManager = new TransactionManager();
			transactionManager.initPool();
			threadLocal.set(transactionManager);
		}
		return transactionManager;
	}

	public void initPool() {
		objectPool = new HashMap<>();
	}

	public void addObjectToPool(V domainObject) {
		objectPool.put((K) domainObject.getKey(), domainObject);
	}

	public V getObjectFromPool(K key) {
		return objectPool.get(key);
	}

	public boolean containsInPool(K key) {
		return objectPool.containsKey(key);
	}
}
