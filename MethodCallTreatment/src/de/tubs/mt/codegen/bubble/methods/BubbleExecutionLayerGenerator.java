package de.tubs.mt.codegen.bubble.methods;

import java.util.Arrays;

import de.tubs.mt.codegen.methods.Method;
import de.tubs.mt.codegen.methods.MethodGenerator;
import de.tubs.mt.codegen.structure.MethodCallCode;
import de.tubs.mt.codegen.structure.TextCode;
import de.tubs.mt.codegen.structure.Variable;

public class BubbleExecutionLayerGenerator implements MethodGenerator {

	private static final String ARRAY_NAME = "a";

	@Override
	public Method getMethod(int currentDepth, int number, int depth, int width,
			Method blatt) {
		Method m = new Method(
				"execute",
				"void",
				Arrays.asList(new Variable[] { new Variable("int[]", ARRAY_NAME) }),
				1);
		MethodCallCode methodCall = blatt.getMethodCallCode();
		methodCall.setResultIsName(true);
		m.addCode(new TextCode("		"));
		m.addCode(methodCall);
		m.addCode(new TextCode("(" + ARRAY_NAME + ",0);\n"));

		int size = LayerSizeGenerator.calculateNextOffset(currentDepth, depth,
				width) +1;

		m.getJML().addRequires(ARRAY_NAME + ".length == " + size);
		m.getJML().addEnsures(
				"(\\forall int i; 0 <= i && i < a.length -1; a[i] <= a[i+1])");

		return m;
	}

}
