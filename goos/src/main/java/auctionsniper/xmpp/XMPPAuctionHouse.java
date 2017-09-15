package auctionsniper.xmpp;

import auctionsniper.Auction;
import auctionsniper.AuctionHouse;
import auctionsniper.UserRequestListener;

public class XMPPAuctionHouse implements AuctionHouse {

	private static final String LOGGER_NAME = "auction-sniper";
	public static final String LOG_FILE_NAME = "auction-sniper.log";
	public static final String ITEM_ID_AS_LOGIN = "auction-%s";
	public static final String AUCTION_ID_FORMAT = ITEM_ID_AS_LOGIN + "@%s/" + XMPPAuctionHouse.AUCTION_RESOURCE;
	public static final String AUCTION_RESOURCE = "Auction";

	@Override
	public Auction auctionFor(UserRequestListener.Item item) {
		return null;
	}
}
