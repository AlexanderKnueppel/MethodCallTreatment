package de.tubs.mt.codegen.structure;


/**
 * The Class Variable.
 */
public class Variable {

	/** The type. */
	private String type;
	
	/** The name. */
	private String name;

	/**
	 * Instantiates a new variable.
	 *
	 * @param type the type
	 * @param name the name
	 */
	public Variable(String type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the definition code.
	 *
	 * @return the definition code
	 */
	public String getDefinitionCode() {
		return getType() + " " + getName();
	}
	
	/**
	 * Gets the usage code.
	 *
	 * @return the usage code
	 */
	public String getUsageCode() {
		return getName();
	}
	
}
