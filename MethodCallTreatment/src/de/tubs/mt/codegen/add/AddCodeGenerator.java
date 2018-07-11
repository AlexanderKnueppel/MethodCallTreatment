package de.tubs.mt.codegen.add;

import de.tubs.mt.codegen.TreeCodeGenerator;
import de.tubs.mt.codegen.methods.MethodGenerator;


/**
 * The Class AddCodeGenerator.
 */
abstract class AddCodeGenerator extends TreeCodeGenerator{

	/** The Constant serialVersionUID. */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -8365329902054023961L;

	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.TreeCodeGenerator#getLeafMethodGenerator()
	 */
	@Override
	protected MethodGenerator getLeafMethodGenerator() {
		return new LeafMethodGenerator();
	}


	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.TreeCodeGenerator#getExecutionMethodGenerator()
	 */
	@Override
	protected MethodGenerator getExecutionMethodGenerator() {
		return null;
	}
	
}
