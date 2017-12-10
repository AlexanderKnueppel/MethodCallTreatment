package de.tubs.mt.codegen.add.methods;

import java.util.Arrays;

import de.tubs.mt.Incrementer;
import de.tubs.mt.codegen.methods.Method;
import de.tubs.mt.codegen.methods.MethodGenerator;
import de.tubs.mt.codegen.structure.Code;
import de.tubs.mt.codegen.structure.TextCode;
import de.tubs.mt.codegen.structure.Variable;

public class TinyMethodGenerator implements MethodGenerator{

	@Override
	public Method getMethod(int currentDepth, int number, int depth, int width, Method blatt) {
		Method m = new Method(currentDepth, number, "int" ,Arrays.asList(new Variable[]{new Variable("int", "i")}),1);
		boolean first = true;
		Code methodCall = m.getNextMethodCall();
		for (int i = 0; i < width; i++) {
			String code ="		";
			if(first){
				code += "int ";
				first = false;
			}
			code += "j = ";
			m.addCode(new TextCode(code));
			m.addCode(methodCall);
			m.addCode(new TextCode(";\n		i = j+1;\n"));
		}
		m.addCode(new TextCode("		return i;"));
		
		int comingDepth = depth - currentDepth;
		
		int comingIncrease = width;
		for (int i = 0; i < comingDepth; i++) {
			comingIncrease = comingIncrease * width + width;
		}
		
		
		if(!Incrementer.jmlWhiteList.contains(currentDepth)) {
			m.getJML().include = false;
		}
		m.getJML().addRequires("i < "+(Integer.MAX_VALUE-comingIncrease));
		m.getJML().addEnsures("\\result == \\old(i)+"+comingIncrease);
		
		return m;
	}

}
