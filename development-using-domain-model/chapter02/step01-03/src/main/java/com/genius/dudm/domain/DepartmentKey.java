package com.genius.dudm.domain;

import lombok.ToString;

@ToString(callSuper = true)
public class DepartmentKey extends DomainKey {

	public DepartmentKey(Long no) {
		super(new Object[]{no});
	}
}