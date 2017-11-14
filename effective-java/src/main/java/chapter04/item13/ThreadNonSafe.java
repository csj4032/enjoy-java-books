package chapter04.item13;

import java.util.ArrayList;

public class ThreadNonSafe implements Runnable {

	private int index;
	private Member member;

	public ThreadNonSafe(int index, Member member) {
		this.index = index;
		this.member = member;
	}

	@Override
	public void run() {
		try {
			member.age++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(this.index + " " + member.age);
	}


	public static void main(String[] args) throws InterruptedException {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		Member member = Member.builder().id(1L).name("Genius").age(37).build();
		for (int i = 0; i < 5000; i++) {
			Thread t = new Thread(new ThreadNonSafe(i, member));
			t.start();
			threads.add(t);
		}

		for (int i = 0; i < threads.size(); i++) {
			Thread t = threads.get(i);
			try {
				t.join();
			} catch (Exception e) {
			}
		}

		System.out.println(member.age);
	}
}