package com.genius;

import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Slf4j
public class ListeningObject implements PropertyChangeListener {

	Object objectToBeListenedTo;

	public void handInListenedToObject(ListenedToObject objectToBeListenedTo) {
		objectToBeListenedTo.addPropertyChangeListener(this);
		this.objectToBeListenedTo = objectToBeListenedTo;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("foobar")) {
			log.info("For property foobar, the old value was {} and the new value was {} ...", event.getOldValue(), event.getNewValue());
		} else if (event.getPropertyName().equals("barfoo")) {
			log.info("For property barfoo, the old value was {} and the new value was {} ...", event.getOldValue(), event.getNewValue());
		}
	}
}