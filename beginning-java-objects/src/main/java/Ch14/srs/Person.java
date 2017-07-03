package Ch14.srs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
	private String name;
	private String ssn;

	public Person() {
		this.setName("?");
		this.setSsn("???-??-????");
	}
}
