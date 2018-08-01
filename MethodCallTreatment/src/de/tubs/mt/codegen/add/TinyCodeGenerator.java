package de.tubs.mt.codegen.add;

import de.tubs.mt.codegen.add.TinyMethodGenerator;
import de.tubs.mt.codegen.methods.MethodGenerator;

public class TinyCodeGenerator extends AddCodeGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2857443343252145060L;

	@Override
	protected MethodGenerator getNextMethodGenerator() {
		return new TinyMethodGenerator();
	}
}
