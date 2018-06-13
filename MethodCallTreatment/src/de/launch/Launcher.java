package de.launch;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.WindowConstants;

import de.evaluation.VerificationEffortMain;

import de.tubs.mt.CallGenerator;
import de.tubs.mt.CallGenerator.Program;
import de.tubs.mt.ExcelFile;
import de.tubs.mt.FileControl;
import de.tubs.mt.codeanalyze.MethodPrinter;
import de.tubs.mt.codeanalyze.PrepMethod;

public class Launcher {

	private VerificationEffortMain executer;
	private Program program;
	private int runs;
	private int width;
	private int depth;
	private int toDepth;
	private int seed;
	private boolean completeSpec;
	private boolean contracting;
	private boolean caching;
	private boolean isToDepth;
	private boolean saveXls;
	private String javaFilePath;
	private List<String> lines;
	private List<Integer> xlsList;
	private String name;

	public void setParameter(Program program, int runs, int width, int depth, boolean completeSpec, boolean contracting,
			boolean caching, boolean isToDepth, boolean saveXls, String javaFilePath) {
		this.program = program;
		this.runs = completeSpec ? 1 : runs;
		this.width = width;
		this.depth = isToDepth && program != Program.OWN ? 1 : depth;
		this.toDepth = depth;
		this.completeSpec = completeSpec;
		this.contracting = contracting;
		this.caching = caching;
		this.isToDepth = isToDepth;
		this.saveXls = saveXls;
		this.javaFilePath = javaFilePath;
		this.name = program == Program.OWN ? javaFilePath : ((program == Program.ADD ? "AddDepth" : "BubbleSortDepth") + (depth) + "Width" + (width) + ".java");
	}

	public void executeLauncher(String whitelist, List<PrepMethod> methodList, int startP, int endP, int gran) throws Exception {
		FileControl.rebuildExecPath();
		xlsList = new ArrayList<Integer>();

		if (caching) {
			FileControl.createFile();
		}

		for (int d = depth; d <= toDepth; d++) {
			depth = d;

			executer = new VerificationEffortMain(program, width, depth, runs, completeSpec, contracting);

			//FileControl.initStructure();

			lines = new LinkedList<String>();
			lines.add(executer.toString());

			if (completeSpec)
				lines.add("(Verification Effort for " + (contracting ? "Method Contracting" : "Method Inlining") + ")");
			lines.add("=============================================================");

			System.out.println(lines.get(0) + "\n" + lines.get(1) + "\n\n");

			if (caching) {
				try {
					Files.write(Paths.get(FileControl.FILE), lines, Charset.forName("UTF-8"),
							StandardOpenOption.APPEND);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Whitelist/Starter: " + whitelist);
			//TODO seed = runs
			for(int i = startP; i <= endP; i += gran) {
				MethodPrinter.recreateJavaFile(0, i, name, whitelist, methodList);
				runVerify(whitelist);

			}
			
			lines.add("\n");

			try {
				Files.write(Paths.get(FileControl.getResultHandle().getPath()), lines, Charset.forName("UTF-8"),
						StandardOpenOption.APPEND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (saveXls) {
			try {
				ExcelFile.createTable(xlsList, contracting ? "contracting" : "inlining");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void runVerify(String whitelist) throws Exception {
		int effortInlining = 0;
		int effortContracting = 0;

		for (int i = 0; i < runs; ++i) {
			seed = i;
			List<Integer> effort = null;


			effort = executer.verifyProgram(seed, i, whitelist);

			// Only first run needs to compute verification effort for 0% and 100%
			if (i == 0) {
				effortInlining = effort.get(0);
				effortContracting = effort.get(effort.size() - 1);
			} else {
				effort.add(0, effortInlining);
				effort.add(effortContracting);
			}

			String result = "";

			for (Integer nodes : effort) {
				result += nodes + ",";
				xlsList.add(nodes);
			}

			result = result.substring(0, result.length() - 1);

			
			lines.add(result);

			System.out.println(result + "\n");

			if (caching) {
				try {
					Files.write(Paths.get(FileControl.FILE), Arrays.asList(result), Charset.forName("UTF-8"),
							StandardOpenOption.APPEND);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	//TODO check runs and seed 
	public List<PrepMethod> runGenerate() throws Exception {
		FileControl.initStructure();
		for (int i = 0; i < runs; i++) {
			seed = i;
			if (program == Program.OWN) {
				MethodPrinter.moveOwnJavaClassToPrep(javaFilePath, seed);
			} else {
				CallGenerator.callProgramGenerator(program, width, depth, seed, runs);

			}
		}
		return MethodPrinter.getMethodList(name, seed);
	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		UILaunch uil = new UILaunch(launcher);
		uil.setVisible(true);
		uil.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
