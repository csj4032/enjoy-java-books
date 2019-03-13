package chapter03.item10;

import java.sql.Timestamp;
import java.util.Date;

public class TimestampDate {

	public static void main(String[] args) {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		System.out.println(date.equals(timestamp));
		System.out.println(timestamp.equals(date));
	}
}
