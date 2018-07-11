package de.tubs.mt.codegen.add;

import de.tubs.mt.codegen.methods.MethodGenerator;


/**
 * The Class BroadCodeGenerator.
 */
public class BroadCodeGenerator extends AddCodeGenerator {


	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.TreeCodeGenerator#getNextMethodGenerator()
	 */
	@Override
	protected MethodGenerator getNextMethodGenerator() {
		return new DummyMethodGenerator();
	}
}
