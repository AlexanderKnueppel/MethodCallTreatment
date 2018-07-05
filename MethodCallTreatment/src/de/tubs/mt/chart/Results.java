package de.tubs.mt.chart;

import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.files.FileControl;

public abstract class Results {
	private static List<Integer> resList = new ArrayList<Integer>();
	private static List<ChartResults> rfxy = new ArrayList<>();
	private static List<Integer> xlsList = new ArrayList<>();
	private static List<String> lines = new ArrayList<>();

	
	public static void initResults(boolean contracting) {
		lines.clear();
		lines.add("\n(Verification Effort for " + (contracting ? "Method Contracting" : "Method Inlining") + ")");
		lines.add("==========================================================================================");
		
		xlsList.clear();
		rfxy.clear();
		resList.clear();
	}
	
	
	public static void addResults(List<Integer> effort, int run, int depth, int perc) {
		lines.add("Run: " + run + ", Depth: " + depth + ", Spec: " + perc + "Result: " + effort.get(effort.size()-1));
		resList.addAll(effort);
		rfxy.add(new ChartResults(run, depth, perc, effort.get(effort.size()-1)));
		xlsList.add(effort.get(effort.size()-1));
		
		System.out.println("Results: "  + resList.toString() + "\n");
	}
	
	
	public static void printResults() {
		FileControl.createResultFilePath();
		FileControl.createResultFile(lines);
	}
	
	public static List<ChartResults> getResultsForXY() {
		return rfxy;
	}
	
}
