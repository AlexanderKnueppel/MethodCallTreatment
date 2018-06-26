package de.tubs.mt.codegen.add;

import de.tubs.mt.codegen.methods.MethodGenerator;


public class BroadCodeGenerator extends AddCodeGenerator {


	@Override
	protected MethodGenerator getNextMethodGenerator() {
		// TODO Auto-generated method stub
		return new DummyMethodGenerator();
	}
}
