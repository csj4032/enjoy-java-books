package chapter07.item39;

import lombok.Data;

import java.util.Date;

@Data
public final class PeriodClone {
	private final Date start;
	private final Date end;

	public PeriodClone(Date start, Date end) {
		if (start.compareTo(end) > 0) throw new IllegalArgumentException(start + "after" + end);
		this.start = (Date) start.clone();
		this.end = (Date) end.clone();
	}

	public Date start() {
		return start;
	}

	public Date end() {
		return end;
	}

	@Override
	public String toString() {
		return start + " - " + end;
	}
}