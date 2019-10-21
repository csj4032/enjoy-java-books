package com.genius.condition.express;

public enum ExpressType {
	NO_REQUEST("신청안함"),
	ON_REQUEST("발급요청중"),
	ON_MODIFY("변경요청중"),
	COMPLETE_NUMBER("완료"),
	HYPHEN("-"),
	FAILURE("발급실패");

	private String message;

	ExpressType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}