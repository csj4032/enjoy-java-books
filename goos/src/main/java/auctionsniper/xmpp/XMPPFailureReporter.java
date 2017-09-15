package auctionsniper.xmpp;

/**
 * Created by Administrator on 2015-09-21.
 */
public interface XMPPFailureReporter {

	void cannotTranslateMessage(String auctionId, String failedMessage, Exception exception);
}
