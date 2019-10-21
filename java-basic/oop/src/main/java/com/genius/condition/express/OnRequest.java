package com.genius.condition.express;

public class OnRequest implements Express {

	@Override
	public ExpressType express() {
		return ExpressType.ON_REQUEST;
	}
}
