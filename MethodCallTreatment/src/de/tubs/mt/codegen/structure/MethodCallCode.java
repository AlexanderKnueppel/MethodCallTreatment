package de.tubs.mt.codegen.structure;

import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codegen.CallHelper;

public class MethodCallCode implements Code {

	private List<Variable> variables = new ArrayList<>();
	private String name;
	private boolean resultIsName = false;

	public List<Variable> getVariables() {
		return variables;
	}

	public void addVariable(Variable variable) {
		this.variables.add(variable);
	}

	public String getName() {
		return name;
	}

	public void setResultIsName(boolean result) {
		this.resultIsName = result;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void copyValues(MethodCallCode mcc) {
		this.name = mcc.getName();
		variables.clear();
		for (Variable v : mcc.getVariables()) {
			variables.add(new Variable(v.getType(), v.getName()));
		}
	}

	@Override
	public String getCode() {
		if (resultIsName) {
			return getName();
		} else {
			return name + CallHelper.getCallUsage(variables);
		}
	}

}
