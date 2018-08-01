package de.tubs.mt.programfactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import de.tubs.mt.codeanalyze.ClassMethodHandler;
import de.tubs.mt.codeanalyze.JMLManipulator;
import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.evaluation.VerificationEffortMain;
import de.tubs.mt.files.FileControl;
import de.tubs.mt.output.observer.LogOutput;
import de.tubs.mt.result.ResultHandler;
import de.tubs.mt.ui.UIModel;



/**
 * The Class CodeBase.
 */
class CodeBase implements IProgram {
	
	/** The prep path. */
	private File prepPath;
	
	/** The class list. */
	private List<PrepClasses> classList = new ArrayList<PrepClasses>();
	
	/** The pm. */
	private List<PrepMethod> pm = new ArrayList<PrepMethod>();
	
	/** The log output. */
	private LogOutput logOutput = new LogOutput();

	/* (non-Javadoc)
	 * @see de.tubs.mt.programfactory.IProgram#prepare(java.io.File)
	 */
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

	/* (non-Javadoc)
	 * @see de.tubs.mt.programfactory.IProgram#manipulate(int, int)
	 */
	@Override
	public void manipulate(int depth, int perc, String strategy) {
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
			ResultHandler.clearResultList();
			updateOutput("\n\nRun: " + run);
			setMethodList();
			JMLManipulator.setWhiteList(pm);
			JMLManipulator.setStaterMethdod(model.getStarter());
			JMLManipulator.clearBlackList();
			for (int perc = model.getEndPerc(); perc >= model.getStartPerc(); perc -= model.getGranulation()) {
				manipulate(0, perc, model.getStrategy());
				List<Integer> effort = VerificationEffortMain.verifyProgram(FileControl.getExecPath()
						.getPath(), model.getStarter(), model.isContracting(), logOutput);
				ResultHandler.addResults(effort, run, 0, perc);

			}
		}
		ResultHandler.printResults();
	}
	
	/**
	 * Sets the method list.
	 */
	private void setMethodList() {
		pm.clear();
		classList = getClasses();
		for(PrepClasses pc : classList) {
			pm.addAll(pc.prepMethods);
		}
		
	}
	

	/* (non-Javadoc)
	 * @see de.tubs.mt.programfactory.IProgram#getClasses()
	 */
	@Override
	public List<PrepClasses> getClasses() {
		return ClassMethodHandler.getClassList(prepPath);
	}

	/* (non-Javadoc)
	 * @see de.tubs.mt.programfactory.IProgram#setParameters(de.tubs.mt.ui.UIModel)
	 */
	@Override
	public void setParameters(UIModel model) {
		// just for the generated Program
		
	}

	@Override
	public void updateOutput(String s) {
		logOutput.apend(s);	
		
	}

}
