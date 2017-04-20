package ch01;

import org.apache.catalina.startup.Bootstrap;

/**
 * Created by Genius on 2017-04-15.
 */
public class TomcatServer {

	public static void main(String[] args) throws Exception {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.start();
	}
}