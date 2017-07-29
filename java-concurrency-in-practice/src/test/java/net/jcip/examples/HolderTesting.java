package net.jcip.examples;

public class HolderTesting {

	public static void main(String[] args) {
		Holder holder = null;
		new Thread(new HolderThread(holder, "1")).start();
		new Thread(new HolderThread(holder, "2")).start();
		new Thread(new HolderThread(holder, "3")).start();
		new Thread(new HolderThread(holder, "4")).start();
	}
}

class HolderThread implements Runnable {

	private Holder holder;
	private String name;

	public HolderThread(Holder holder, String name) {
		this.holder = holder;
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50000000; i++) {
			//System.out.println(name + " = " + i);
			holder = new Holder(i);
			holder.assertSanity();
		}
	}
}