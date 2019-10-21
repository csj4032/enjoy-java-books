package com.genius.condition.express;

public class NoRequest implements Express {

	@Override
	public ExpressType express() {
		return ExpressType.NO_REQUEST;
	}
}
