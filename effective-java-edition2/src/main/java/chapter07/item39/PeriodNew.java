package chapter07.item39;

import lombok.Data;

import java.util.Date;

@Data
public final class PeriodNew {
	private final Date start;
	private final Date end;

	public PeriodNew(Date start, Date end) {
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());

		if (this.start.compareTo(this.end) > 0)
			throw new IllegalArgumentException(start + " after " + end);
	}

	public Date start() {
		return new Date(start.getTime());
	}

	public Date end() {
		return new Date(end.getTime());
	}

	@Override
	public String toString() {
		return start + " - " + end;
	}
}