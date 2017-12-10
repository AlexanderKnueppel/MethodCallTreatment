package de.tubs.mt.codegen;

import java.util.List;

import de.tubs.mt.codegen.structure.Variable;

public class CallHelper {

	public static String getVariableDefinition(List<Variable> variables){
		StringBuilder b = new StringBuilder();
		b.append("(");
		if (!variables.isEmpty()) {
			for (int i = 0; i < variables.size() - 1; i++) {
				b.append(variables.get(i).getDefinitionCode());
				b.append(", ");
			}
			b.append(variables.get(variables.size() - 1).getDefinitionCode());
		}
		b.append(")");
		return b.toString();
	}
	
	public static String getCallUsage(List<Variable> variables){
		StringBuilder b = new StringBuilder();
		b.append("(");
		if (!variables.isEmpty()) {
			for (int i = 0; i < variables.size() - 1; i++) {
				b.append(variables.get(i).getUsageCode());
				b.append(", ");
			}
			b.append(variables.get(variables.size() - 1).getUsageCode());
		}
		b.append(")");
		return b.toString();
	}
}
