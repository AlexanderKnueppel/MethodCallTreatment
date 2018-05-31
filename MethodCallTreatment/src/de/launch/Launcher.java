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
import java.util.concurrent.TimeUnit;

import javax.swing.WindowConstants;

import de.evaluation.VerificationEffortMain;

import de.tubs.mt.CallGenerator;
import de.tubs.mt.CallGenerator.Program;
import de.tubs.mt.ExcelFile;
import de.tubs.mt.FileControl;
import de.tubs.mt.codeanalyze.MethodPrinter;

public class Launcher {

	private VerificationEffortMain executer;
	private Program program;
	private int runs;
	private int width;
	private int depth;
	private int toDepth;
	private boolean completeSpec;
	private boolean contracting;
	private boolean caching;
	private boolean isToDepth;
	private boolean saveXls;
	private String javaFilePath;
	private List<String> lines;
	private List<Integer> xlsList;

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
	}

	public void executeLauncher() throws Exception {
		
		xlsList = new ArrayList<Integer>();

		if (caching) {
			FileControl.createFile();
		}

		for (int d = depth; d <= toDepth; d++) {
			depth = d;

			executer = new VerificationEffortMain(program, width, depth, runs, completeSpec, contracting);

			FileControl.initStructure();

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

			runGenerateAndVerify();

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

	private void runGenerateAndVerify() throws Exception {
		long start = System.currentTimeMillis();
		int effortInlining = 0;
		int effortContracting = 0;
		

		for (int i = 0; i < runs; ++i) {
			long diff = System.currentTimeMillis() - start;
			String currentTime = String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(diff),
					TimeUnit.MILLISECONDS.toSeconds(diff)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)));

			System.out.println("Run " + (i + 1) + " [Time: " + currentTime + "]");

			int seed = Math.abs((int) System.currentTimeMillis());

			List<Integer> effort = null;

			generateProgram(seed, i);

			effort = executer.verifyProgram(seed, i);

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

			System.out.println(result + "\n\n");

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

	private void generateProgram(int seed, int run) throws Exception {
		if (program == Program.OWN) {
			MethodPrinter.recreateJavaFile(javaFilePath, seed);
		} else {
			if (!completeSpec) {
				CallGenerator.callRandomSpecifiedProgramGenerator(program, width, depth, seed, run);
			} else {
				CallGenerator.callFullSpecifiedProgramGenerator(program, width, depth, seed);
			}
		}
	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		UILaunch uil = new UILaunch(launcher);
		uil.setVisible(true);
		uil.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
