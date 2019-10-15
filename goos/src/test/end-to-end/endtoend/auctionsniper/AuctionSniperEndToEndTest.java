package endtoend.auctionsniper;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuctionSniperEndToEndTest {

	private static final FakeAuctionServer auction = new FakeAuctionServer("item-54321");
	private static final ApplicationRunner application = new ApplicationRunner();

	@Test
	@Order(1)
	@DisplayName("동작하는 골격 테스트")
	public void sniperJoinsAuctionUntilAuctionCloses() throws Exception {
		auction.startSellingItem();
		application.startBiddingIn(auction);
		auction.hasReceivedJoinRequestFromSniper();
		auction.announceClosed();
		application.showsSniperHasLostAuction();
	}

	@AfterAll
	public static void stopAuction() {
		auction.stop();
	}

	@AfterAll
	public static void stopApplication() {
		application.stop();
	}
}