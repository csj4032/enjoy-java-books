package com.genius.shorter;

public enum AlgorithmType {
	MD5(1, 3),
	SHA(2, 2),
	CRC(3, 1);

	private Integer normalType;
	private Integer specialType;

	AlgorithmType(int normalType, int specialType) {
		this.normalType = normalType;
		this.specialType = specialType;
	}

	public Integer getNormalType() {
		return normalType;
	}

	public Integer getSpecialType() {
		return specialType;
	}
}
