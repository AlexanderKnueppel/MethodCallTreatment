package de.tubs.mt.codegen.structure;

public class Variable {

	private String type;
	private String name;

	public Variable(String type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefinitionCode() {
		return getType() + " " + getName();
	}
	public String getUsageCode() {
		return getName();
	}
	
}
