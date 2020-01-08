package com.genius.dudm.domain;

public abstract class DomainKey {
	private Object[] keyFields;

	public DomainKey(Object[] keyFields) {
		this.keyFields = keyFields;
	}

	public Object[] getKeyFields() {
		return keyFields;
	}
}
