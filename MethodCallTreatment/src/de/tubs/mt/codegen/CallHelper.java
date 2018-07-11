package de.tubs.mt.codegen;

import java.util.List;

import de.tubs.mt.codegen.structure.Variable;


/**
 * The Class CallHelper.
 */
public class CallHelper {

	/**
	 * Gets the variable definition.
	 *
	 * @param variables the variables
	 * @return the variable definition
	 */
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
	
	/**
	 * Gets the call usage.
	 *
	 * @param variables the variables
	 * @return the call usage
	 */
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
