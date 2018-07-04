package de.tubs.mt.codeanalyze;

import java.util.List;

public class PrepClasses {
	public PrepClasses(String name, List<PrepMethod> prepMethods, String path) {
		this.name = name;
		this.prepMethods = prepMethods;	
		this.path = path;
	}
	
	public String name;
	public List<PrepMethod> prepMethods;
	public String path;
}
