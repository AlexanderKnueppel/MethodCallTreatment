package de.tubs.mt.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.WindowConstants;

import de.tubs.mt.chart.ChartResults;
import de.tubs.mt.codeanalyze.ClassMethodHandler;
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
	private List<ChartResults> rfxy = new ArrayList<>();
	private int tmpresult;
	private String folder;
	private boolean codebase = false;
	private List<PrepMethod> methodList = new ArrayList<>();




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

		//executer = new VerificationEffortMain(width, contracting);

		for (int seed = 1; seed <= runs; seed++) {
			lines.add("--run " + seed);

			for (int d = depth; d <= toDepth; d++) {
				results = "";

				System.out.println(lines.get(0) + "\n" + lines.get(1) + "\n\n");

				List<PrepMethod> pm = new ArrayList<PrepMethod>();
				for (int i = startP; i <= endP; i += gran) {
					if(program != Program.OWN) {

					}
					if(program == Program.OWN && !codebase) {

					}

					runVerify(starter, seed);
					rfxy.add(new ChartResults(seed, d, i, tmpresult));

				}

				lines.add("----depth " + d + ": " + results);
			}
		}

		FileControl.createResultFilePath();
		FileControl.createResultFile(lines);

	}

	private void runVerify(String whitelist, int seed) throws Exception {
		List<Integer> effort = null;
		//effort = executer.verifyProgram(seed, whitelist, folder);

		for (Integer nodes : effort) {
			results += nodes + ",";
			xlsList.add(nodes);
		}
		tmpresult = effort.get(effort.size() - 1);
		System.out.println("Results: " + results + "\n");
	}

	public List<ChartResults> getResultsForXY() {
		return rfxy;
	}


	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		UILaunch uil = new UILaunch(launcher);
		uil.setVisible(true);
		uil.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
