package com.genius.condition.express;

public class Failure implements Express {

	@Override
	public ExpressType express() {
		return ExpressType.FAILURE;
	}
}