package auctionsniper.util;

/**
 * Created by Administrator on 2015-09-23.
 */
public class Defect extends RuntimeException {

	public Defect() {
		super();
	}

	public Defect(String message, Throwable cause) {
		super(message, cause);
	}

	public Defect(String message) {
		super(message);
	}

	public Defect(Throwable cause) {
		super(cause);
	}
}