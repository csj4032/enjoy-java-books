package com.genius.condition.express;

import com.genius.condition.SafetyNumberCondition;

public class OnRequest extends AbstractExpress {

	public OnRequest(SafetyNumberCondition safetyNumberCondition) {
		super(safetyNumberCondition);
	}

	@Override
	protected ExpressType onExpress() {
		return ExpressType.ON_REQUEST;
	}
}
