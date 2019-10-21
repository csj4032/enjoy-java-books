package com.genius.condition.express;

import com.genius.condition.ExpiredType;
import com.genius.condition.IssueType;
import com.genius.condition.SafetyNumberCondition;
import org.jetbrains.annotations.NotNull;

public class Request extends AbstractExpress {

	private final ExpiredType expiredType;

	public Request(@NotNull SafetyNumberCondition safetyNumberCondition) {
		super(safetyNumberCondition);
		this.expiredType = safetyNumberCondition.getExpiredType();
	}

	@Override
	protected ExpressType onExpress() {
		if (getIssueType().equals(IssueType.UPDATE)) {
			if (expiredType.equals(ExpiredType.BEFORE)) {
				return ExpressType.ON_MODIFY;
			} else {
				return ExpressType.HYPHEN;
			}
		} else {
			return ExpressType.ON_REQUEST;
		}
	}
}
