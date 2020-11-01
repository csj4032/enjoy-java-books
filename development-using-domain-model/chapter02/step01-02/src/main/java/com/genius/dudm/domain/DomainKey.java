package com.genius.dudm.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public class DomainKey {

	Object[] keyFields;

	public DomainKey(Object[] keyFields) {
		this.keyFields = keyFields;
	}

	public Object[] getKeyFields() {
		return keyFields;
	}

	public boolean isNull() {
		return Objects.isNull(keyFields);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DomainKey domainKey = (DomainKey) o;
		return Arrays.equals(keyFields, domainKey.keyFields);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(keyFields);
	}

	@Override
	public String toString() {
		return "DomainKey{" + "keyFields=" + Arrays.toString(keyFields) + '}';
	}
}
