package auctionsniper.xmpp;

import auctionsniper.AuctionEventListener;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import java.util.HashMap;
import java.util.Map;

public class AuctionMessageTranslator implements MessageListener {

	private final AuctionEventListener listener;
	private final String sniperId;
	private final XMPPFailureReporter failureReporter;

	public AuctionMessageTranslator(String sniperId, AuctionEventListener listener, XMPPFailureReporter failureReporter) {
		this.sniperId = sniperId;
		this.listener = listener;
		this.failureReporter = failureReporter;
	}

	@Override
	public void processMessage(Chat chat, Message message) {
		String messageBody = message.getBody();
		try {
			translate(messageBody);
		} catch (Exception parseException) {
			failureReporter.cannotTranslateMessage(sniperId, messageBody, parseException);
			listener.auctionFailed();
		}
	}

	private void translate(String messageBody) throws Exception {
		AuctionEvent event = AuctionEvent.from(messageBody);

		String eventType = event.type();
		if ("CLOSE".equals(eventType)) {
			listener.auctionClosed();
		}
		if ("PRICE".equals(eventType)) {
			listener.currentPrice(event.currentPrice(), event.increment(), event.isFrom(sniperId));
		}
	}

	private static class AuctionEvent {
		private final Map<String, String> fields = new HashMap<String, String>();

		public String type() throws MissingValueException {
			return get("Event");
		}

		public int currentPrice() throws Exception {
			return getInt("CurrentPrice");
		}

		public int increment() throws Exception {
			return getInt("Increment");
		}

		public AuctionEventListener.PriceSource isFrom(String sniperId) throws MissingValueException {
			return sniperId.equals(bidder()) ? AuctionEventListener.PriceSource.FromSniper : AuctionEventListener.PriceSource.FromOtherBidder;
		}

		private String bidder() throws MissingValueException {
			return get("Bidder");
		}

		private int getInt(String fieldName) throws Exception {
			return Integer.parseInt(get(fieldName));
		}

		private String get(String fieldName) throws MissingValueException {
			final String value = fields.get(fieldName);
			if (value == null) {
				throw new MissingValueException(fieldName);
			}
			return value;
		}

		private void addField(String field) {
			String[] pair = field.split(":");
			fields.put(pair[0].trim(), pair[1].trim());
		}

		static AuctionEvent from(String messageBody) {
			AuctionEvent event = new AuctionEvent();
			for (String field : fieldsIn(messageBody)) {
				event.addField(field);
			}
			return event;
		}

		static String[] fieldsIn(String messageBody) {
			return messageBody.split(";");
		}
	}

	private static class MissingValueException extends Exception {
		public MissingValueException(String fieldName) {
			super("Missing value for " + fieldName);
		}
	}
}