package com.genius.condition.express;

import com.genius.condition.ExpiredType;
import com.genius.condition.IssueType;
import org.jetbrains.annotations.NotNull;

public class Complete implements Express {

	private final ExpiredType expiredType;
	private final IssueType issueType;
	private final String number;

	public Complete(@NotNull ExpiredType expiredType, @NotNull IssueType issueType, @NotNull String number) {
		this.expiredType = expiredType;
		this.issueType = issueType;
		this.number = number;
	}

	@Override
	public ExpressType express() {
		if(issueType.equals(IssueType.WAITING)) return ExpressType.ON_REQUEST;
		if (expiredType.equals(ExpiredType.BEFORE)) {
			ExpressType type = ExpressType.COMPLETE_NUMBER;
			type.setMessage(number);
			return type;
		} else {
			return ExpressType.HYPHEN;
		}
	}
}