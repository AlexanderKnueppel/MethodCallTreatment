package de.tubs.mt.codegen.add.methods;

import de.tubs.mt.codegen.add.methods.DummyMethodGenerator;
import de.tubs.mt.codegen.methods.MethodGenerator;

public class MethodGeneratorFactory {

	public static MethodGenerator getNextMethodGenerator(){
		return new DummyMethodGenerator();
	}
}
