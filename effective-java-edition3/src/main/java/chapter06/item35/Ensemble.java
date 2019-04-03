package chapter06.item35;

public enum Ensemble {

	SOLO(1),
	DUET(2),
	TRIO(3),
	SOME(4),
	QUARTET(4),
	QUINTET(5),
	SEXTET(6),
	SEPTET(7),
	OCTET(8),
	DOUBLE_QUARTET(8),
	NONET(9),
	DECTET(12),
	TRIPLE_QUARTET(12);

	private int numberOfMusicians;

	Ensemble(int numberOfMusicians) {
		this.numberOfMusicians = numberOfMusicians;
	}

	public int numberOfMusicians() {
		return numberOfMusicians;
	}
}
