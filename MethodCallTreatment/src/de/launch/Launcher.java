package de.launch;

import java.io.File;
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
import de.evaluation.VerificationEffortMain.Program;

public class Launcher {

	static final String PATH = "Results";
	static final String FILE = PATH + "/cache.txt";

	static boolean caching = true;

	static int runs = 10;
	static Program p = Program.ADD;
	static int width = 5;
	static int depth = 3;
	static boolean completeSpec = false; // used for method inlining vs contracting
	static boolean contracting = false;
	
	static VerificationEffortMain executer;

	
	static void createFile() {
		if (!new File(PATH).exists())
			new File(PATH).mkdir();

		if (caching && !new File(FILE).exists()) {
			try {
				new File(FILE).createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}

	
	/**
	 * call:
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		

		if (completeSpec) {
			runs = 1;
		}

		createFile();
		// for(int d = depth; d <= 30; ++d) {
		executer = new VerificationEffortMain(p, PATH, width, depth, runs);

		try {
			executer.initStructure();
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
				Files.write(Paths.get(FILE), lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
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
				effort = executer.verifyProgram(seed, i);
			} else {
				effort = executer.verifySingleProgram(p, contracting);
			}

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
					Files.write(Paths.get(FILE), Arrays.asList(result), Charset.forName("UTF-8"), StandardOpenOption.APPEND);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		lines.add("\n");

		try {
			Files.write(Paths.get(executer.getResultHandle().getPath()), lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }
	}

}
