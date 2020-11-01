package com.genius.dudm.domain;

import lombok.ToString;

@ToString
public abstract class DomainObject {
	private DomainKey key;

	public DomainObject(DomainKey key) {
		this.key = key;
	}

	public DomainKey getKey() {
		return key;
	}
}
