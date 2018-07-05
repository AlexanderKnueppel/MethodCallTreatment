package de.tubs.mt.evaluation;

import de.uka.ilkd.key.proof.Statistics;

class VerificationResult implements Comparable<VerificationResult> {
	
	private String target;
	private String displayName;
	private Statistics statistics;
	private boolean closed = false;

	public boolean isClosed() {
		return closed;
	}


	public void setClosed(boolean closed) {
		this.closed = closed;
	}


	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}


	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}


	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}


	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	/**
	 * @return the statistics
	 */
	public Statistics getStatistics() {
		return statistics;
	}


	/**
	 * @param statistics the statistics to set
	 */
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}


	public VerificationResult(String target, String displayName, Statistics statistics) {
		super();
		this.target = target;
		this.displayName = displayName;
		this.statistics = statistics;
	}

	public VerificationResult(String target, String displayName, Statistics statistics, boolean closed) {
		this(target, displayName, statistics);
		setClosed(closed);
	}

	@Override
	public int compareTo(VerificationResult o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
