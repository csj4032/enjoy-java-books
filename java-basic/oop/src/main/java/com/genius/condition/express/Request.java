package com.genius.condition.express;

import com.genius.condition.ExpiredType;
import com.genius.condition.IssueType;
import org.jetbrains.annotations.NotNull;

public class Request implements Express {

	private final ExpiredType expiredType;
	private final IssueType issueType;

	public Request(@NotNull ExpiredType expiredType, @NotNull IssueType issueType) {
		this.expiredType = expiredType;
		this.issueType = issueType;
	}

	@Override
	public ExpressType express() {
		if (issueType.equals(IssueType.UPDATE)) {
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
