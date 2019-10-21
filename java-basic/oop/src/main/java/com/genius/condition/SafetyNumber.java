package com.genius.condition;

import com.genius.condition.express.*;
import org.jetbrains.annotations.NotNull;

public class SafetyNumber {

	public Express getExpress(@NotNull boolean safetyNoUseYn, @NotNull IssueType issueType, @NotNull IssueStatus issueStatus, @NotNull ExpiredType expiredType, @NotNull String issuePhone) {
		return getExpress(new SafetyNumberCondition(safetyNoUseYn, issueType, issueStatus, expiredType, issuePhone));
	}

	private Express getExpress(SafetyNumberCondition safetyNumberCondition) {
		if (safetyNumberCondition.isSafetyNoUseYn()) return new NoRequest();
		return switch (safetyNumberCondition.getIssueStatus()) {
			case REQUEST:
				yield new Request(safetyNumberCondition.getExpiredType(), safetyNumberCondition.getIssueType());
			case COMPETE:
				yield new Complete(safetyNumberCondition.getExpiredType(), safetyNumberCondition.getIssueType(), safetyNumberCondition.getIssuePhone());
			case FAILURE:
				yield new Failure();
			default:
				yield new OnRequest();
		};
	}
}