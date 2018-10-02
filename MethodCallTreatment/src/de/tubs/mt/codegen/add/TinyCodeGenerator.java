package de.tubs.mt.codegen.add;

import de.tubs.mt.codegen.add.TinyMethodGenerator;
import de.tubs.mt.codegen.methods.MethodGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class TinyCodeGenerator.
 */
public class TinyCodeGenerator extends AddCodeGenerator {

	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.TreeCodeGenerator#getNextMethodGenerator()
	 */
	@Override
	protected MethodGenerator getNextMethodGenerator() {
		return new TinyMethodGenerator();
	}
}
