package com.genius;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SomeObject implements ListenedToObject {

	private String foobar;
	private String barfoo;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public void setFoobar(String newValue) {
		String oldValue = this.foobar;
		this.foobar = newValue;
		pcs.firePropertyChange("barfoo", oldValue, newValue);
	}

	public void setBarfoo(String newValue) {
		String oldValue = this.barfoo;
		this.barfoo = newValue;
		pcs.firePropertyChange("barfoo", oldValue, newValue);
	}
}
