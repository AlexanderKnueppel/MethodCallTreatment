package de.tubs.mt.output.observer;

import java.util.Observable;

public class LogOutput extends Observable {

	public LogOutput() {
		this.addObserver(new LogObserver());
	}

	public void apend(String value) {
		setChanged();
		notifyObservers(value);
	}

}
