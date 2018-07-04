package de.tubs.mt.programfactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.chart.ResultsForXY;
import de.tubs.mt.codeanalyze.JMLManipulator;
import de.tubs.mt.codeanalyze.ClassMethodHandler;
import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.codegen.Incrementer;
import de.tubs.mt.codegen.CallGenerator.Program;
import de.tubs.mt.evaluation.VerificationEffortMain;
import de.tubs.mt.files.FileControl;

public class Generated implements IProgram {

	private int width;
	private int depth;
	private boolean isToDepth;
	private String program;
	private String name;
	private File prepPath;
	private int startDepth;
	private List<PrepMethod> pm = new ArrayList<PrepMethod>();

	@Override
	public void setParameters(int width, int depth, boolean isToDepth,
			String program) {
		this.width = width;
		this.depth = depth;
		this.isToDepth = isToDepth;
		this.startDepth = isToDepth ? 1 : depth;
		this.program = program;
		this.name = program + "Width" + width;
	}

	@Override
	public void prepare(File file) {
		FileControl.initStructure();
		this.prepPath = new File(getDepthPrepPath(startDepth));

		for (int flowDepth = startDepth; flowDepth <= depth; flowDepth++) {
			try {
				if (program.equals("Add")) {
					Incrementer.generateProgramForAdd(width, flowDepth,
							flowDepth, FileControl.getPrepPath().getPath(),
							name);
				} else {
					Incrementer.generateProgramForBubbleSort(width, depth,
							flowDepth, FileControl.getPrepPath().getPath(),
							name);
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	/*
	 * Helper-Method to get the depth-depended Prep-path
	 */
	private String getDepthPrepPath(int depth) {
		return FileControl.getPrepPath().getPath() + "/" + depth + "/" + name
				+ ".java";
	}

	@Override
	public void manipulate(int dp, int perc) {
		FileControl.rebuildExecPath();
		JMLManipulator.setBlackList(perc, pm, null);
		JMLManipulator.iterateClasses(getClasses());
	}

	@Override
	public void verify(int runs, boolean contracting, int startPercentage,
			int endPercentage, int granulation, String starter) {

		for (int run = 1; run <= runs; run++) {
			for (int dp = startDepth; dp <= depth; dp++) {
				this.prepPath = new File(getDepthPrepPath(dp));
				System.out.println("Run: " + run + "   Depth: " + dp);
				pm = ClassMethodHandler.getMethodList(getDepthPrepPath(dp));
				JMLManipulator.setWhiteList(pm);
				JMLManipulator.setStaterMethdod(starter);
				JMLManipulator.clearBlackList();
				for (int perc = endPercentage; perc >= startPercentage; perc -= granulation) {
					manipulate(dp, perc);
					VerificationEffortMain.verifyProgram(FileControl.getExecPath().getPath(), starter, contracting);
				}
			}
		}
	}

	@Override
	public List<PrepClasses> getClasses() {
		return ClassMethodHandler.getClassList(prepPath);
	}

}
