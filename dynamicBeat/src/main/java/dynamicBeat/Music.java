package dynamicBeat;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Music extends Thread {

	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			this.file = new File(Main.class.getResource("../musics/" + name).toURI());
			this.fis = new FileInputStream(file);
			this.bis = new BufferedInputStream(fis);
			this.player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() {
		if (player == null) {
			return 0;
		}
		return player.getPosition();
	}

	public void close() {
		this.isLoop = false;
		this.player.close();
		this.interrupt();
	}

	@Override
	public void run() {
		try {
			do {
				this.player.play();
				this.fis = new FileInputStream(file);
				this.bis = new BufferedInputStream(fis);
				this.player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}