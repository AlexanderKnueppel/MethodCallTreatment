package de.tubs.mt.programfactory;

import java.io.File;
import java.util.List;

import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.ui.UIModel;


/**
 * The Interface IProgram.
 */
public interface IProgram {
	
	/**
	 * Sets the parameters.
	 *
	 * @param model the new parameters
	 */
	public void setParameters(UIModel model);
	
	/**
	 * Prepare.
	 *
	 * @param file the file
	 */
	public void prepare(File file);
	
	/**
	 * Manipulate.
	 *
	 * @param depth the depth
	 * @param perc the perc
	 */
	public void manipulate(int depth, int perc, String strategy);
	
	/**
	 * Verify.
	 *
	 * @param runs the runs
	 * @param contracting the contracting
	 * @param startPercentage the start percentage
	 * @param endPercentage the end percentage
	 * @param granulation the granulation
	 * @param starter the starter
	 */
	public void verify(int runs, boolean contracting, int startPercentage, int endPercentage,
			int granulation, String starter, String startegy);
	
	/**
	 * Gets the classes.
	 *
	 * @return the classes
	 */
	public List<PrepClasses> getClasses();

}
