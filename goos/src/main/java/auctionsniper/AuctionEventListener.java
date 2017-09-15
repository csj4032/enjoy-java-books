package auctionsniper;

import java.util.EventListener;

/**
 * Created by Administrator on 2015-09-21.
 */
public interface AuctionEventListener extends EventListener {

	enum PriceSource {
		FromSniper, FromOtherBidder;
	}

	void auctionClosed();

	void currentPrice(int price, int increment, PriceSource priceSource);

	void auctionFailed();
}
