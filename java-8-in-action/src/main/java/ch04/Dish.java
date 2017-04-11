package ch04;

import lombok.*;

@Data
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

	public enum Type {
		MEAT, FISH, OTHER;
	}

	private transient String name;
	private boolean vegetarian;
	private int calories;
	private Type type;
}
