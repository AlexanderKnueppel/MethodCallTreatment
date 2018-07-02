package de.tubs.mt.codeanalyze;

import java.util.List;

public class PrepClasses {
	public PrepClasses(String name, List<PrepMethod> prepMethods) {
		this.name = name;
		this.prepMethods = prepMethods;	
	}
	
	public String name;
	public List<PrepMethod> prepMethods;
}
