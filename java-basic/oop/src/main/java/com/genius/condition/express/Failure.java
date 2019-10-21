package com.genius.condition.express;

import com.genius.condition.SafetyNumberCondition;

public class Failure extends AbstractExpress {

	public Failure(SafetyNumberCondition safetyNumberCondition) {
		super(safetyNumberCondition);
	}

	@Override
	protected ExpressType onExpress() {
		return ExpressType.FAILURE;
	}
}