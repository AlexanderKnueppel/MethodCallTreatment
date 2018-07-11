package de.tubs.mt.result;



/**
 * The Class ChartResults.
 */
public class ChartResults {
	
	/** The run. */
	private int run;
	
	/** The depth. */
	private int depth;
	
	/** The spec. */
	private int spec;
	
	/** The result. */
	private int result;
	
	/**
	 * Instantiates a new chart results.
	 *
	 * @param run the run
	 * @param depth the depth
	 * @param spec the spec
	 * @param result the result
	 */
	ChartResults(int run, int depth, int spec, int result) {
		this.run = run;
		this.depth = depth;
		this.spec = spec;
		this.result = result;
		
	}
	
	/**
	 * Gets the run.
	 *
	 * @return the run
	 */
	public int getRun() {
		return run;
	}
	
	/**
	 * Gets the depth.
	 *
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * Gets the spec.
	 *
	 * @return the spec
	 */
	public int getSpec() {
		return spec;
	}
	
	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public int getResults() {
		return result;
	}

}
