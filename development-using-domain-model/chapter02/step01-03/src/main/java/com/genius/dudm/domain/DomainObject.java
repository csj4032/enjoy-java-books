package com.genius.dudm.domain;

public class DomainObject {

	private DomainKey key;

	public DomainObject(DomainKey key) {
		this.key = key;
	}

	public DomainKey getKey() {
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
