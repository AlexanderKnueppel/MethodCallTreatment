package de.tubs.mt.result;


public class ChartResults {
	
	private int run;
	private int depth;
	private int spec;
	private int result;
	
	public ChartResults(int run, int depth, int spec, int result) {
		this.run = run;
		this.depth = depth;
		this.spec = spec;
		this.result = result;
		
	}
	
	public int getRun() {
		return run;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public int getSpec() {
		return spec;
	}
	
	public int getResults() {
		return result;
	}

}