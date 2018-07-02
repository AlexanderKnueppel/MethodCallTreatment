package de.tubs.mt.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.WindowConstants;

import de.tubs.mt.chart.ResultsForXY;
import de.tubs.mt.codeanalyze.MethodPrinter;
import de.tubs.mt.codeanalyze.PrepClasses;
import de.tubs.mt.codeanalyze.PrepMethod;
import de.tubs.mt.codegen.CallGenerator;
import de.tubs.mt.codegen.CallGenerator.Program;
import de.tubs.mt.evaluation.VerificationEffortMain;
import de.tubs.mt.files.FileControl;

public class Launcher {

	private VerificationEffortMain executer;
	private Program program;
	private int runs;
	private int width;
	private int depth;
	private int toDepth;
	private boolean contracting;
	private File javaFilePath;
	private List<String> lines;
	private List<Integer> xlsList;
	private String name;
	private String results = "";
	private List<ResultsForXY> rfxy = new ArrayList<>();
	private int tmpresult;

	public void setParameter(Program program, int runs, int width, int depth, boolean contracting, boolean isToDepth,
			File javaFilePath) {
		this.program = program;
		this.runs = runs;
		this.width = width;
		this.depth = isToDepth && program != Program.OWN ? 1 : depth;
		this.toDepth = depth;
		this.contracting = contracting;
		this.javaFilePath = javaFilePath;
		this.name = program == Program.OWN ? javaFilePath.getName()
				: ((program == Program.ADD ? "AddDepth" : "BubbleSortDepth") + (depth) + "Width" + (width) + ".java");
	}

	private String getDepthDependedName(int d) {
		return program == Program.OWN ? javaFilePath.getName()
				: ((program == Program.ADD ? "AddDepth" : "BubbleSortDepth") + (d) + "Width" + (width) + ".java");

	}

	public void executeLauncher(String starter, int startP, int endP, int gran, boolean randomized) throws Exception {
		FileControl.rebuildExecPath();
		xlsList = new ArrayList<Integer>();
		rfxy.clear();

		lines = new LinkedList<String>();

		lines.add("\n(Verification Effort for " + (contracting ? "Method Contracting" : "Method Inlining") + ")");
		lines.add("[program=" + program + ", width=" + width + ", depthStart=" + depth + ", depthEnd=" + toDepth
				+ ", runs=" + runs + ", StartSpecification, Endspecification, Granulation (%) = " + startP + ", " + endP
				+ ", " + gran + "]");
		lines.add("==========================================================================================");

		executer = new VerificationEffortMain(width, contracting);

		for (int seed = 1; seed <= runs; seed++) {
			lines.add("--run " + seed);
			executer.setRuns(seed);
			for (int d = depth; d <= toDepth; d++) {
				results = "";
				MethodPrinter.whiteList.clear();
				MethodPrinter.whiteList.add(starter);
				executer.setDepth(d);
				System.out.println(lines.get(0) + "\n" + lines.get(1) + "\n\n");

				for (int i = startP; i <= endP; i += gran) {
					MethodPrinter.recreateJavaFile(seed, d, i, getDepthDependedName(d), starter,
							MethodPrinter.getMethodList(getDepthDependedName(d), d), randomized);
					runVerify(starter, seed);
					rfxy.add(new ResultsForXY(seed, d, i, tmpresult));

				}

				lines.add("----depth " + d + ": " + results);
			}
		}

		FileControl.createResultFilePath();
		FileControl.createResultFile(lines);

	}

	private void runVerify(String whitelist, int seed) throws Exception {
		List<Integer> effort = null;
		effort = executer.verifyProgram(seed, whitelist);

		for (Integer nodes : effort) {
			results += nodes + ",";
			xlsList.add(nodes);
		}
		tmpresult = effort.get(effort.size() - 1);
		System.out.println("Results: " + results + "\n");
	}

	public List<ResultsForXY> getResultsForXY() {
		return rfxy;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PrepMethod> runGenerate() throws Exception {
		FileControl.initStructure();
		int seed;
		for (seed = depth; seed <= toDepth; seed++) {
			if (program == Program.OWN) {
				MethodPrinter.moveOwnJavaClassToPrep(javaFilePath, seed);
				MethodPrinter.getClassList(javaFilePath, seed);
				return MethodPrinter.getMethodList(name, seed);
			} else {
				CallGenerator.callProgramGenerator(program, width, seed, seed, runs);
			}
		}
		File f = new File(FileControl.getPrepPath().getPath() + "/" + (seed-1) + "/" + getDepthDependedName(depth));
		System.out.println(f.getAbsolutePath());
		List<PrepClasses> classList = MethodPrinter.getClassList(f, (seed-1));
		System.out.println(classList.toString());
		
		
		
		return MethodPrinter.getMethodList(getDepthDependedName(depth), depth);
	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		UILaunch uil = new UILaunch(launcher);
		uil.setVisible(true);
		uil.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
