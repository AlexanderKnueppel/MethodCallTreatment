package de.tubs.mt.programfactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import de.tubs.mt.chart.Results;
import de.tubs.mt.codeanalyze.ClassMethodHandler;
import de.tubs.mt.codeanalyze.JMLManipulator;
import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.evaluation.VerificationEffortMain;
import de.tubs.mt.files.FileControl;

public class SingleClass implements IProgram {

	private File prepPath;
	private List<PrepMethod> pm = new ArrayList<PrepMethod>();

	@Override
	public void prepare(File file) {
		FileControl.initStructure();
		try {
			this.prepPath = new File(FileControl.getPrepPath().getPath() + "/"
					+ file.getName());
			FileUtils.copyFile(file, prepPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void manipulate(int depth, int perc) {
		FileControl.rebuildExecPath();
		JMLManipulator.setBlackList(perc, pm, null);
		JMLManipulator.iterateClasses(getClasses());
	}
	

	@Override
	public void verify(int runs, boolean contracting, int startPercentage,
			int endPercentage, int granulation, String starter) {
		for (int run = 1; run <= runs; run++) {
			System.out.println("Run: " + run);
			pm = ClassMethodHandler.getMethodList(prepPath.getAbsolutePath());
			JMLManipulator.setWhiteList(pm);
			JMLManipulator.setStaterMethdod(starter);
			JMLManipulator.clearBlackList();
			for (int perc = endPercentage; perc >= startPercentage; perc -= granulation) {
				manipulate(0, perc);
				List<Integer> effort = VerificationEffortMain.verifyProgram(FileControl.getExecPath().getPath(), starter, contracting);
				Results.addResults(effort);
			}
		}
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
