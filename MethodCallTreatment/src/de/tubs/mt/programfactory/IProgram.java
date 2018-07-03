package de.tubs.mt.programfactory;

import java.io.File;
import java.util.List;

import de.tubs.mt.codeanalyze.PrepClasses;

public interface IProgram {
	
	public void setParameters(int width, int depth, boolean isToDepth, String program);
	
	public void prepare(File file);
	
	public void manipulate(int startPercentage, int endPercentage,  int granulation);
	
	public void verify(int runs, boolean contracting);
	
	public List<PrepClasses> getClasses();

}
