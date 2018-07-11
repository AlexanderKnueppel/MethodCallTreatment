package de.tubs.mt.codeanalyze;

import java.util.List;

/**
 * The Class PrepClasses.
 */
public class PrepClasses {
	
	/**
	 * Instantiates a new preparation class.
	 *
	 * @param name the name
	 * @param prepMethods the prep methods
	 * @param path the path
	 */
	PrepClasses(String name, List<PrepMethod> prepMethods, String path) {
		this.name = name;
		this.prepMethods = prepMethods;	
		this.path = path;
	}
	
	/** The name. */
	public String name;
	
	/** The prep methods. */
	public List<PrepMethod> prepMethods;
	
	/** The path. */
	public String path;
}
