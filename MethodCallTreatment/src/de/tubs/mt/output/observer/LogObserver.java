package de.tubs.mt.output.observer;

import java.util.Observable;
import java.util.Observer;


import de.tubs.mt.ui.UIView;

public class LogObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		UIView.outputText.append((String) arg);
	}
}
