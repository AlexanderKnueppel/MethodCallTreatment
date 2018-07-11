package de.tubs.mt.codegen.add;

import java.util.Arrays;

import de.tubs.mt.codegen.Incrementer;
import de.tubs.mt.codegen.methods.Method;
import de.tubs.mt.codegen.methods.MethodGenerator;
import de.tubs.mt.codegen.structure.TextCode;
import de.tubs.mt.codegen.structure.Variable;

/**
 * The Class DummyMethodGenerator.
 */
class DummyMethodGenerator implements MethodGenerator{

	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.methods.MethodGenerator#getMethod(int, int, int, int, de.tubs.mt.codegen.methods.Method)
	 */
	@Override
	public Method getMethod(int currentDepth, int number, int depth, int width, Method blatt) {
		Method m = new Method(currentDepth, number, "int" ,Arrays.asList(new Variable[]{new Variable("int", "i")}),width);
		boolean first = true;
		for (int i = 0; i < width; i++) {
			String code ="		";
			if(first){
				code += "int ";
				first = false;
			}
			code += "j = ";
			m.addCode(new TextCode(code));
			m.addCode(m.getNextMethodCall());
			m.addCode(new TextCode(";\n		i = j+1;\n"));
		}
		m.addCode(new TextCode("		return i;"));
		
		int comingDepth = depth - currentDepth;
		
		int comingIncrease = width;
		for (int i = 0; i < comingDepth; i++) {
			comingIncrease = comingIncrease * width + width;
		}
		
		m.getJML().addRequires("i < "+(Integer.MAX_VALUE-comingIncrease));
		m.getJML().addEnsures("\\result == \\old(i)+"+comingIncrease);
		
		Incrementer.counter++;
		return m;
	}

}
