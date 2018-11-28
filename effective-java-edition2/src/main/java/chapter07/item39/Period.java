package chapter07.item39;

import lombok.Data;

import java.util.Date;

@Data
public final class Period {
	private final Date start;
	private final Date end;

	public Period(Date start, Date end) {
		if (start.compareTo(end) > 0) throw new IllegalArgumentException(start + "after" + end);
		this.start = start;
		this.end = end;
	}

	// public Period(Date start, Date end) {
	// this.start = new Date(start.getTime());
	// this.end = new Date(end.getTime());
	//
	// if (this.start.compareTo(this.end) > 0)
	// throw new IllegalArgumentException(start +" after "+ end);
	// }

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