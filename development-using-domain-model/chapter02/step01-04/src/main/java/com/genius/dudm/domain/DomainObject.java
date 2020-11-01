package com.genius.dudm.domain;

import lombok.ToString;

@ToString
public class DomainObject<T extends DomainKey> {

	private T key;

	public DomainObject(T key) {
		this.key = key;
	}

	public T getKey() {
		return key;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DomainKey paramKey = ((DomainObject) o).getKey();
		return this.key.equals(paramKey);
	}
}
