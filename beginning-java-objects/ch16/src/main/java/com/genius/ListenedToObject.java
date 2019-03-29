package com.genius;

import java.beans.PropertyChangeListener;

public interface ListenedToObject {

	void addPropertyChangeListener(PropertyChangeListener l);

	void removePropertyChangeListener(PropertyChangeListener l);
}
