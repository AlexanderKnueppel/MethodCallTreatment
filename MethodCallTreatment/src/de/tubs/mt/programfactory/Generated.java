package de.tubs.mt.programfactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codeanalyze.JMLManipulator;
import de.tubs.mt.codeanalyze.ClassMethodHandler;
import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.codegen.Incrementer;
import de.tubs.mt.evaluation.VerificationEffortMain;
import de.tubs.mt.files.FileControl;
import de.tubs.mt.output.observer.LogOutput;
import de.tubs.mt.result.ResultHandler;
import de.tubs.mt.ui.UIModel;



/**
 * The Class Generated.
 */
class Generated implements IProgram {

	/** The width. */
	private int width;
	
	/** The depth. */
	private int depth;
	
	private boolean isTree;
	
	/** The program. */
	private String program;
	
	/** The name. */
	private String name;
	
	/** The prep path. */
	private File prepPath;
	
	/** The start depth. */
	private int startDepth;
	
	/** The List of Prep-Methods. */
	private List<PrepMethod> pm = new ArrayList<PrepMethod>();
	
	/** The log output. */
	private LogOutput logOutput = new LogOutput();


	/* (non-Javadoc)
	 * @see de.tubs.mt.programfactory.IProgram#setParameters(de.tubs.mt.ui.UIModel)
	 */
	@Override
	public void setParameters(UIModel model) {
		this.width = model.getWidth();
		this.depth = model.getDepth();
		this.startDepth = model.isToDepth() ? 1 : depth;
		this.isTree = model.getTreeOrTinyCode().equals("Tree") ? true : false;
		this.program = model.getGenerateProgram();
		this.name = program + "Width" + width;
	}

	/* (non-Javadoc)
	 * @see de.tubs.mt.programfactory.IProgram#prepare(java.io.File)
	 */
	@Override
	public void prepare(File file) {
		FileControl.initStructure();
		this.prepPath = new File(getDepthPrepPath(startDepth));

		for (int flowDepth = startDepth; flowDepth <= depth; flowDepth++) {
			try {
				if (program.equals("Add")) {
					Incrementer.generateProgramForAdd(width, flowDepth,
							flowDepth, FileControl.getPrepPath().getPath(),
							name, isTree);
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

	/**
	 * Gets the depth prep path.
	 *
	 * @param depth the depth
	 * @return the depth prep path
	 */
	/*
	 * Helper-Method to get the depth-depended Prep-path
	 */
	private String getDepthPrepPath(int depth) {
		return FileControl.getPrepPath().getPath() + "/" + depth + "/" + name
				+ ".java";
	}

	/* (non-Javadoc)
	 * @see de.tubs.mt.programfactory.IProgram#manipulate(int, int)
	 */
	@Override
	public void manipulate(int dp, int perc, String strategy) {
		FileControl.rebuildExecPath();
		JMLManipulator.setBlackList(perc, pm, strategy);
		JMLManipulator.iterateClasses(getClasses());
	}

	/* (non-Javadoc)
	 * @see de.tubs.mt.programfactory.IProgram#verify(int, boolean, int, int, int, java.lang.String)
	 */
	@Override
	public void verify(UIModel model) {
		ResultHandler.initResults(model.isContracting());
		for (int run = 1; run <= model.getRun(); run++) {
			for (int dp = startDepth; dp <= depth; dp++) {
				ResultHandler.clearResultList();
				this.prepPath = new File(getDepthPrepPath(dp));
				updateOutput("\n\nStrategy: " + model.getStrategy() +  "  Run: " + run + "  Depth: " + dp +"\n");
				pm = ClassMethodHandler.getMethodList(getDepthPrepPath(dp));
				JMLManipulator.setWhiteList(pm);
				JMLManipulator.setStaterMethdod(model.getStarter());
				JMLManipulator.clearBlackList();
				for (int perc = model.getEndPerc(); perc >= model.getStartPerc(); perc -= model.getGranulation()) {
					updateOutput("\nSpecification: " + perc +"\n");
					manipulate(dp, perc, model.getStrategy());
					List<Integer> effort =VerificationEffortMain.verifyProgram(FileControl.getExecPath().getPath(), model.getStarter(), model.isContracting(), logOutput);
					ResultHandler.addResults(effort, run, dp, perc);
				}
			}
		}
		ResultHandler.printResults();
	}

	/* (non-Javadoc)
	 * @see de.tubs.mt.programfactory.IProgram#getClasses()
	 */
	@Override
	public List<PrepClasses> getClasses() {
		return ClassMethodHandler.getClassList(prepPath);
	}

	@Override
	public void updateOutput(String s) {
		logOutput.apend(s);	
	}
}
