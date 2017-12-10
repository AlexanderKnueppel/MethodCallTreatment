package de.tubs.mt.codegen.add;

import de.tubs.mt.codegen.add.methods.DummyMethodGenerator;
import de.tubs.mt.codegen.methods.MethodGenerator;


public class BroadCodeGenerator extends AddCodeGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2691519687061808372L;

	@Override
	protected MethodGenerator getNextMethodGenerator() {
		// TODO Auto-generated method stub
		return new DummyMethodGenerator();
	}
}
