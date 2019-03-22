package chapter05.item30;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recursive implements Comparable<Recursive> {

	private int id;

	public Recursive(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Recursive o) {
		return (this.id == o.id) ? 0 : (this.id > o.id) ? 1 : -1;
	}
}