package endtoend.auctionsniper;

import auctionsniper.Main;
import auctionsniper.ui.MainWindow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationRunner {

	public static final String SNIPER_ID = "sniper";
	public static final String SNIPER_PASSWORD = "sniper";
	public static final String XMPP_HOSTNAME = "localhost";
	private AuctionLogDriver logDriver = new AuctionLogDriver();
  	private AuctionSniperDriver driver;

	public void startBiddingIn(final FakeAuctionServer auction) throws InterruptedException {
		log.info("2단계");
		startSniper(auction);
	}

	public void showsSniperHasLostAuction() {
		log.info("5단계");
		driver.showsSniperStatus(MainWindow.STATUS_LOST);
	}

	public void stop() {
		if (driver != null) {
			driver.dispose();
		}
	}

	private void startSniper(FakeAuctionServer auction) {
		logDriver.clearLog();
		Thread thread = new Thread("Test Application") {
			@Override
			public void run() {
				try {
					Main.main(XMPP_HOSTNAME, SNIPER_ID, SNIPER_PASSWORD, auction.getItemId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
		driver = new AuctionSniperDriver(1000);
		driver.showsSniperStatus(MainWindow.STATUS_JOINING);
	}
}
