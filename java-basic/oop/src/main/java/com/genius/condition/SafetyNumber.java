package com.genius.condition;

import com.genius.condition.express.*;
import org.jetbrains.annotations.NotNull;

public class SafetyNumber {

	public Express getExpress(@NotNull boolean safetyNoUseYn, @NotNull IssueType issueType, @NotNull IssueStatus issueStatus, @NotNull ExpiredType expiredType, @NotNull String issuePhone) {
		return getExpress(new SafetyNumberCondition(safetyNoUseYn, issueType, issueStatus, expiredType, issuePhone));
	}

	private Express getExpress(SafetyNumberCondition safetyNumberCondition) {
		return switch (safetyNumberCondition.getIssueStatus()) {
			case REQUEST:
				yield new Request(safetyNumberCondition);
			case COMPETE:
				yield new Complete(safetyNumberCondition);
			case FAILURE:
				yield new Failure(safetyNumberCondition);
			default:
				yield new OnRequest(safetyNumberCondition);
		};
	}
}