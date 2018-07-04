package de.tubs.mt.programfactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import de.tubs.mt.codeanalyze.ClassMethodHandler;
import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.files.FileControl;
import de.tubs.mt.files.ListFilesUtil;

public class CodeBase implements IProgram {
	
	private File prepPath;
	public static List<PrepClasses> classList = new ArrayList<PrepClasses>();

	@Override
	public void prepare(File file) {
		FileControl.initStructure();
		try {
			this.prepPath = new File(FileControl.getPrepPath().getPath() + "/" + file.getName());
			FileUtils.copyDirectory(file, prepPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void manipulate(int depth, int perc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void verify(int runs, boolean contracting, int startPercentage, int endPercentage,
			int granulation, String starter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PrepClasses> getClasses() {
		return ClassMethodHandler.getClassList(prepPath);
	}

	@Override
	public void setParameters(int width, int depth, boolean isToDepth,
			String program) {
		// just for the generated Program
		
	}

}
