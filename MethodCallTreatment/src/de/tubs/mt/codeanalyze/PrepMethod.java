package de.tubs.mt.codeanalyze;


/**
 * The Class PrepMethod.
 */
public class PrepMethod {
	
	/**
	 * Instantiates a new preparation method with info: name of the method, has jml-notation
	 *
	 * @param name the name
	 * @param jml the jml
	 */
	PrepMethod(String name, boolean jml) {
		this.name = name;
		this.jml = jml;	
	}
	
	/** The name. */
	public String name;
	
	/** The jml. */
	public boolean jml;
}
