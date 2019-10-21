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

	private ExpressType getExpressIf(SafetyNumberCondition safetyNumberCondition) {
		ExpressType expressType = ExpressType.ON_REQUEST;
		if (safetyNumberCondition.isSafetyNoUseYn()) {
			if (safetyNumberCondition.getIssueType().equals(IssueType.NEW)) {
				switch (safetyNumberCondition.getIssueStatus()) {
					case COMPETE:
						if (safetyNumberCondition.getExpiredType().equals(ExpiredType.BEFORE)) {
							expressType = ExpressType.HYPHEN;
						} else {
							expressType = ExpressType.COMPLETE_NUMBER;
						}
						break;
					case REQUEST:
						expressType = ExpressType.ON_REQUEST;
						break;
					case FAILURE:
						expressType = ExpressType.FAILURE;
						break;
				}
			} else if (safetyNumberCondition.getIssueType().equals(IssueType.UPDATE)) {
				switch (safetyNumberCondition.getIssueStatus()) {
					case COMPETE:
						if (safetyNumberCondition.getExpiredType().equals(ExpiredType.BEFORE)) {
							expressType = ExpressType.COMPLETE_NUMBER;
						} else {
							expressType = ExpressType.HYPHEN;
						}
						break;
					case REQUEST:
						expressType = ExpressType.ON_REQUEST;
						break;
					case FAILURE:
						expressType = ExpressType.FAILURE;
						break;
				}
			} else {
				expressType = ExpressType.ON_REQUEST;
			}
		} else {
			expressType = ExpressType.NO_REQUEST;
		}
		return expressType;
	}
}