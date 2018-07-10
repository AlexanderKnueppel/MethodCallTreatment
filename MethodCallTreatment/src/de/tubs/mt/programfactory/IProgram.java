package de.tubs.mt.programfactory;

import java.io.File;
import java.util.List;

import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.ui.UIModel;

public interface IProgram {
	
	public void setParameters(UIModel model);
	
	public void prepare(File file);
	
	public void manipulate(int depth, int perc);
	
	public void verify(int runs, boolean contracting, int startPercentage, int endPercentage,
			int granulation, String starter);
	
	public List<PrepClasses> getClasses();

}
