package com.genius.condition.express;

import com.genius.condition.IssueType;
import com.genius.condition.SafetyNumberCondition;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractExpress implements Express {

	private final boolean safetyNoUseYn;
	private final IssueType issueType;

	public AbstractExpress(@NotNull SafetyNumberCondition safetyNumberCondition) {
		this.safetyNoUseYn = safetyNumberCondition.isSafetyNoUseYn();
		this.issueType = safetyNumberCondition.getIssueType();
	}

	@Override
	public ExpressType express() {
		if (isSafetyNoUseYn()) return ExpressType.NO_REQUEST;
		if (isIssueTypeWaiting()) return ExpressType.ON_REQUEST;
		return onExpress();
	}

	protected abstract ExpressType onExpress();

	protected boolean isSafetyNoUseYn() {
		return safetyNoUseYn;
	}

	protected boolean isIssueTypeWaiting() {
		return issueType.equals(IssueType.WAITING);
	}

	protected IssueType getIssueType() {
		return issueType;
	}
}
