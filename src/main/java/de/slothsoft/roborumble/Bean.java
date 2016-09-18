package de.slothsoft.roborumble;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

class Bean {

	final PropertyChangeSupport propertyChangeSupport;

	public Bean() {
		this.propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(property, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(property, listener);
	}
}
