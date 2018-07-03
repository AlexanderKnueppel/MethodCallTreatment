package de.tubs.mt.programfactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.files.FileControl;

public class SingleClass implements IProgram {

	@Override
	public void prepare(File file) {
		FileControl.initStructure();
		try {
			File destFile = new File(FileControl.getPrepPath().getPath() + "/" + file.getName());
			FileUtils.copyFile(file, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void manipulate(int startPercentage, int endPercentage,
			int granulation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void verify(int runs, boolean contracting) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<PrepClasses> getClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameters(int width, int depth, boolean isToDepth,
			String program) {
		// just for the generated Program
		
	}

}
