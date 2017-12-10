package de.tubs.mt.codegen.bubble.methods;

import java.util.Arrays;

import de.tubs.mt.codegen.methods.Method;
import de.tubs.mt.codegen.methods.MethodGenerator;
import de.tubs.mt.codegen.structure.MethodCallCode;
import de.tubs.mt.codegen.structure.TextCode;
import de.tubs.mt.codegen.structure.Variable;

public class BubbleLayerGenerator implements MethodGenerator {

	private static final String ARRAY_NAME = "a";
	private static final String POSITION = "pos";
	
	@Override
	public Method getMethod(int currentDepth, int number, int depth, int width,
			Method blatt) {
		Method m = new Method(currentDepth, number, "void",
				Arrays.asList(new Variable[] { new Variable("int[]", ARRAY_NAME),
						new Variable("int", POSITION)}), width);
		MethodCallCode methodCall = m.getNextMethodCall();
		methodCall.setResultIsName(true);
		int offset = 0;
		int nextOffset = LayerSizeGenerator.calculateNextOffset(currentDepth, depth, width);
		for (int i = 0; i < width; i++) {
			m.addCode(new TextCode("		"));
			m.addCode(m.getNextMethodCall());
			m.addCode(new TextCode("("+ARRAY_NAME+","+POSITION+"+"+offset+");\n"));
			offset += nextOffset;
		}
		methodCall = blatt.getMethodCallCode();
		methodCall.setResultIsName(true);
		m.addCode(new TextCode("		"));
		m.addCode(methodCall);
		m.addCode(new TextCode("("+ARRAY_NAME+","+POSITION+"+"+offset+");\n"));
		offset += 1;

		int comingDepth = depth - currentDepth;

		int comingIncrease = width;
		for (int i = 0; i < comingDepth; i++) {
			comingIncrease = comingIncrease * width + width;
		}
		m.getJML().addRequires("pos >= 0 && pos + "+offset+" < a.length");
		m.getJML().addRequires("(pos > 0 ==>(\\forall int y; 0 <= y && y < a.length - pos; a[y] <= a[a.length - pos]))");
		m.getJML().addRequires("(\\forall int z; a.length - pos <= z && z < a.length -1; a[z] <= a[z+1])");
		m.getJML().addEnsures("(\\forall int o; 0 <= o && o < a.length - ("+offset+"+pos); a[o] <= a[a.length - ("+offset+"+pos)])");
		m.getJML().addEnsures("(\\forall int p; a.length - ("+offset+"+pos) <= p && p < a.length -1; a[p] <= a[p+1])");

		return m;
	}
}
