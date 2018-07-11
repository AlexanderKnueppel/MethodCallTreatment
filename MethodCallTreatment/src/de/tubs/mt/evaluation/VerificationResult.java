package de.tubs.mt.evaluation;

import de.uka.ilkd.key.proof.Statistics;


/**
 * The Class VerificationResult.
 */
class VerificationResult implements Comparable<VerificationResult> {
	
	/** The target. */
	private String target;
	
	/** The display name. */
	private String displayName;
	
	/** The statistics. */
	private Statistics statistics;
	
	/** The closed. */
	private boolean closed = false;

	/**
	 * Checks if is closed.
	 *
	 * @return true, if is closed
	 */
	public boolean isClosed() {
		return closed;
	}


	/**
	 * Sets the closed.
	 *
	 * @param closed the new closed
	 */
	public void setClosed(boolean closed) {
		this.closed = closed;
	}


	/**
	 * Gets the target.
	 *
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}


	/**
	 * Sets the target.
	 *
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}


	/**
	 * Gets the display name.
	 *
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}


	/**
	 * Sets the display name.
	 *
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	/**
	 * Gets the statistics.
	 *
	 * @return the statistics
	 */
	public Statistics getStatistics() {
		return statistics;
	}


	/**
	 * Sets the statistics.
	 *
	 * @param statistics the statistics to set
	 */
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}


	/**
	 * Instantiates a new verification result.
	 *
	 * @param target the target
	 * @param displayName the display name
	 * @param statistics the statistics
	 */
	private VerificationResult(String target, String displayName, Statistics statistics) {
		super();
		this.target = target;
		this.displayName = displayName;
		this.statistics = statistics;
	}

	/**
	 * Instantiates a new verification result.
	 *
	 * @param target the target
	 * @param displayName the display name
	 * @param statistics the statistics
	 * @param closed the closed
	 */
	public VerificationResult(String target, String displayName, Statistics statistics, boolean closed) {
		this(target, displayName, statistics);
		setClosed(closed);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(VerificationResult o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
