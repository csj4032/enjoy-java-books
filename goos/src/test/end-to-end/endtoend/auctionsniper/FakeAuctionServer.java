package endtoend.auctionsniper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class FakeAuctionServer {

	public static final String ITEM_ID_AS_LOGIN = "auction-%s";
	public static final String AUCTION_RESOURCE = "Auction";
	public static final String XMPP_HOSTNAME = "localhost";
	private static final String AUCTION_PASSWORD = "auction";

	private final SingleMessageListener messageListener = new SingleMessageListener();

	@Getter
	private final String itemId;
	private final XMPPConnection connection;
	private Chat currentChat;

	public FakeAuctionServer(String itemId) {
		this.itemId = itemId;
		this.connection = new XMPPConnection(XMPP_HOSTNAME);
	}

	public void startSellingItem() throws XMPPException {
		log.info("1단계");
		connection.connect();
		connection.login(String.format(ITEM_ID_AS_LOGIN, itemId), AUCTION_PASSWORD, AUCTION_RESOURCE);
		connection.getChatManager().addChatListener((chat, createdLocally) -> {
			currentChat = chat;
			chat.addMessageListener(messageListener);
		});
	}

	public void hasReceivedJoinRequestFromSniper() throws InterruptedException {
		log.info("3단계");
		messageListener.receivesAMessage();
	}

	public void announceClosed() throws XMPPException {
		log.info("4단계");
		currentChat.sendMessage(new Message());
	}

	public void stop() {
		connection.disconnect();
	}

	public class SingleMessageListener implements MessageListener {
		private final ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<>(1);

		public void processMessage(Chat chat, Message message) {
			messages.add(message);
		}

		public void receivesAMessage() throws InterruptedException {
			assertThat("Message", messages.poll(5, TimeUnit.SECONDS), is(notNullValue()));
		}

		public void receivesAMessage(Matcher<? super String> messageMatcher) throws InterruptedException {
			final Message message = messages.poll(5, TimeUnit.SECONDS);
			assertThat(message, hasProperty("body", messageMatcher));
		}
	}
}
