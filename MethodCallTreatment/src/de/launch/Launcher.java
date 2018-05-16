package de.launch;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.evaluation.VerificationEffortMain;
import de.tubs.mt.CallGenerator;
import de.tubs.mt.CallGenerator.Program;
import de.tubs.mt.FileControl;

public class Launcher {


	static boolean caching = true;

	static int runs = 3;
	static Program program = Program.ADD;
	static int width = 3;
	static int depth = 2;
	static boolean completeSpec = true; // used for method inlining vs contracting
	static boolean contracting = true;
	static VerificationEffortMain executer;

	/**
	 * call:
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (completeSpec) {
			runs = 1;
		}

		if (caching) {
			FileControl.createFile();
		}

		// for(int d = depth; d <= 30; ++d) {
		executer = new VerificationEffortMain(program, width, depth, runs, completeSpec);

		try {
			FileControl.initStructure();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<String> lines = new LinkedList<String>();
		lines.add(executer.toString());
		if (completeSpec)
			lines.add("(Verification Effort for " + (contracting ? "Method Contracting" : "Method Inlining") + ")");
		lines.add("=============================================================");

		System.out.println(lines.get(0) + "\n" + lines.get(1) + "\n\n");

		
		if (caching) {
			try {
				Files.write(Paths.get(FileControl.FILE), lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		long start = System.currentTimeMillis();

		int effortInlining = 0, effortContracting = 0;

		for (int i = 0; i < runs; ++i) {
			long diff = System.currentTimeMillis() - start;
			String currentTime = String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(diff),
					TimeUnit.MILLISECONDS.toSeconds(diff)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)));

			System.out.println("Run " + (i + 1) + " [Time: " + currentTime + "]");
			int seed = Math.abs((int) System.currentTimeMillis());

			List<Integer> effort = null;

			
			if (!completeSpec) {
				CallGenerator.callRandomSpecifiedProgramGenerator(program, width, depth, seed, i);;
			} else {
				CallGenerator.callFullSpecifiedProgramGenerator(program, seed, width, depth);
			}
			
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
			}
			result = result.substring(0, result.length() - 1);

			lines.add(result);

			System.out.println(result);

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

		lines.add("\n");

		try {
			Files.write(Paths.get(FileControl.getResultHandle().getPath()), lines, Charset.forName("UTF-8"),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }
	}

}
