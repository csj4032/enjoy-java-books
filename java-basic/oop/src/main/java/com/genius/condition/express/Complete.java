package com.genius.condition.express;

import com.genius.condition.ExpiredType;
import com.genius.condition.IssueType;
import com.genius.condition.SafetyNumberCondition;
import org.jetbrains.annotations.NotNull;

public class Complete extends AbstractExpress {

	private final ExpiredType expiredType;
	private final String number;

	public Complete(@NotNull SafetyNumberCondition safetyNumberCondition) {
		super(safetyNumberCondition);
		this.expiredType = safetyNumberCondition.getExpiredType();
		this.number = safetyNumberCondition.getNumber();
	}

	protected ExpressType onExpress() {
		if (getIssueType().equals(IssueType.WAITING)) return ExpressType.ON_REQUEST;
		if (expiredType.equals(ExpiredType.BEFORE)) {
			ExpressType type = ExpressType.COMPLETE_NUMBER;
			type.setMessage(number);
			return type;
		} else {
			return ExpressType.HYPHEN;
		}
	}
}