package auctionsniper.ui;

import auctionsniper.SniperState;

public class SnipersTableModel {
	private final static String[] STATUS_TEXT = {
			"Joining", "Bidding", "Winning", "Losing", "Lost", "Won", "Failed"
	};

	public static String textFor(SniperState state) {
		return STATUS_TEXT[state.ordinal()];
	}
}