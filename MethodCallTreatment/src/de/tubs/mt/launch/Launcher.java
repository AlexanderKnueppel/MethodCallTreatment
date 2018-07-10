package de.tubs.mt.launch;
import javax.swing.WindowConstants;

import de.tubs.mt.ui.UILaunch;


class Launcher {

	public static void main(String[] args) {
		UILaunch uil = new UILaunch();
		uil.setVisible(true);
		uil.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
