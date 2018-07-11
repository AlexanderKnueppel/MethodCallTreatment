package de.tubs.mt.codegen.structure;

import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codegen.CallHelper;


/**
 * The Class MethodCallCode.
 */
public class MethodCallCode implements Code {

	/** The variables. */
	private List<Variable> variables = new ArrayList<>();
	
	/** The name. */
	private String name;
	
	/** The result is name. */
	private boolean resultIsName = false;

	/**
	 * Gets the variables.
	 *
	 * @return the variables
	 */
	public List<Variable> getVariables() {
		return variables;
	}

	/**
	 * Adds the variable.
	 *
	 * @param variable the variable
	 */
	public void addVariable(Variable variable) {
		this.variables.add(variable);
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
	 * Sets the result is name.
	 *
	 * @param result the new result is name
	 */
	public void setResultIsName(boolean result) {
		this.resultIsName = result;
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
	 * Copy values.
	 *
	 * @param mcc the mcc
	 */
	public void copyValues(MethodCallCode mcc) {
		this.name = mcc.getName();
		variables.clear();
		for (Variable v : mcc.getVariables()) {
			variables.add(new Variable(v.getType(), v.getName()));
		}
	}

	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.structure.Code#getCode()
	 */
	@Override
	public String getCode() {
		if (resultIsName) {
			return getName();
		} else {
			return name + CallHelper.getCallUsage(variables);
		}
	}

}
