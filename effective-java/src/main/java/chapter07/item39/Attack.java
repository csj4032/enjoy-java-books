package chapter07.item39;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Date;

@Slf4j
public class Attack {

	public static void main(String[] args) {
		Date start = new Date();
		Date end = new Date();
		Period p = new Period(start, end);
		end.setYear(78);
		System.out.println(p.end());

		start = new Date();
		end = new Date();
		p = new Period(start, end);
		p.end().setYear(78);
		System.out.println(p);

		val foo = "Final";
		val boo = 1;

		log.info("{}, {}", foo, boo);
	}
}
