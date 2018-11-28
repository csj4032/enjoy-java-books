package chapter07.item39;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateSub extends Date {

	private static List<Date> list = new ArrayList();

	@Override
	public Object clone() {
		Date date = new Date();
		list.add(date);
		return date;
	}
}
