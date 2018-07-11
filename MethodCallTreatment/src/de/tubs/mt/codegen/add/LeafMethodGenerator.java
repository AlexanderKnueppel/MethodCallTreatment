package de.tubs.mt.codegen.add;

import java.util.Arrays;

import de.tubs.mt.codegen.methods.Method;
import de.tubs.mt.codegen.methods.MethodGenerator;
import de.tubs.mt.codegen.structure.TextCode;
import de.tubs.mt.codegen.structure.Variable;

/**
 * The Class LeafMethodGenerator.
 */
class LeafMethodGenerator implements MethodGenerator {

	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.methods.MethodGenerator#getMethod(int, int, int, int, de.tubs.mt.codegen.methods.Method)
	 */
	@Override
	public Method getMethod(int methodCall, int number, int depth, int width, Method blatt) {
		Method m = new Method(methodCall, number, "int" ,Arrays.asList(new Variable[]{new Variable("int", "i")}),width);
		TextCode tc = new TextCode();
		tc.setCode("		return i;");
		m.addCode(tc);
	
		m.getJML().addEnsures("\\result == \\old(i)");
		return m;
	}

}
