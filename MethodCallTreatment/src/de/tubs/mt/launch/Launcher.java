package de.tubs.mt.launch;
import javax.swing.WindowConstants;

import de.tubs.mt.ui.UIView;


/**
 * The Class Launcher.
 */
class Launcher {

	/**
	 * The main method.
	 * 
	 * 
	 * Steps of the program:
	 * 
	 * de.tubs.mt.launch
	 * -> de.tubs.mt.ui
	 * ->-> de.tubs.mt.programfactory
	 * ->->-> de.tubs.mt.codeanalyze || de.tubs.mt.codegen
	 * 
	 * ->-> de.tubs.mt.programfactory
	 * ->->-> de.tubs.mt.codeanalyze
	 * ->->-> de.tubs.mt.evaluation
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		UIView uiv = new UIView();
		uiv.setVisible(true);
		uiv.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
