package com.genius.duality;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class IntObservable extends Observable implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			setChanged();
			notifyObservers(i);
		}
	}
}
