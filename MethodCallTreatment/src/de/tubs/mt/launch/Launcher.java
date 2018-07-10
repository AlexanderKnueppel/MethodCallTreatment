package de.tubs.mt.launch;
import javax.swing.WindowConstants;

import de.tubs.mt.ui.UIView;


class Launcher {

	public static void main(String[] args) {
		UIView uil = new UIView();
		uil.setVisible(true);
		uil.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
