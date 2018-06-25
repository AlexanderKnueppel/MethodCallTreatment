package de.tubs.mt.codegen.add;

import de.tubs.mt.codegen.TreeCodeGenerator;
import de.tubs.mt.codegen.methods.MethodGenerator;

public abstract class AddCodeGenerator extends TreeCodeGenerator{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8365329902054023961L;

	@Override
	protected MethodGenerator getLeafMethodGenerator() {
		return new LeafMethodGenerator();
	}


	@Override
	protected MethodGenerator getExecutionMethodGenerator() {
		return null;
	}
	
}
