package de.tubs.mt.result;

import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.files.FileControl;

// TODO: Auto-generated Javadoc
/**
 * The Class ResultHandler.
 */
public abstract class ResultHandler {
	
	/** The res list. */
	private static List<Integer> resList = new ArrayList<Integer>();
	
	/** The rfxy. */
	private static List<ChartResults> rfxy = new ArrayList<>();
	
	/** The xls list. */
	private static List<Integer> xlsList = new ArrayList<>();
	
	/** The lines. */
	private static List<String> lines = new ArrayList<>();

	
	/**
	 * Inits the results.
	 *
	 * @param contracting the contracting
	 */
	public static void initResults(boolean contracting) {
		lines.clear();
		lines.add("\n(Verification Effort for " + (contracting ? "Method Contracting" : "Method Inlining") + ")");
		lines.add("==========================================================================================");
		
		xlsList.clear();
		rfxy.clear();
		resList.clear();
	}
	
	
	/**
	 * Adds the results.
	 *
	 * @param effort the effort
	 * @param run the run
	 * @param depth the depth
	 * @param perc the perc
	 */
	public static void addResults(List<Integer> effort, int run, int depth, int perc) {
		lines.add("Run: " + run + ", Depth: " + depth + ", Spec: " + perc + ", Result: " + effort.get(effort.size()-1));
		resList.addAll(effort);
		rfxy.add(new ChartResults(run, depth, perc, effort.get(effort.size()-1)));
		xlsList.add(effort.get(effort.size()-1));
		System.out.println("Results: "  + resList.toString() + "\n");
	}
	
	/**
	 * Clear result list.
	 */
	public static void clearResultList() {
		resList.clear();
	}
	
	/**
	 * Prints the results.
	 */
	public static void printResults() {
		FileControl.createResultFilePath();
		FileControl.createResultFile(lines);
	}
	
	/**
	 * Gets the results for XY.
	 *
	 * @return the results for XY
	 */
	public static List<ChartResults> getResultsForXY() {
		return rfxy;
	}
	
}
