package de.tubs.mt.codegen.bubble.methods;

import java.util.Arrays;

import de.tubs.mt.Incrementer;
import de.tubs.mt.codegen.methods.Method;
import de.tubs.mt.codegen.methods.MethodGenerator;
import de.tubs.mt.codegen.structure.MethodCallCode;
import de.tubs.mt.codegen.structure.TextCode;
import de.tubs.mt.codegen.structure.Variable;

public class LeaflessBubbleLayerGenerator implements MethodGenerator {

	private static final String ARRAY_NAME = "a";
	private static final String POSITION = "pos";
	
	@Override
	public Method getMethod(int currentDepth, int number, int depth, int width,
			Method blatt) {
		Method m = new Method(currentDepth, number, "void",
				Arrays.asList(new Variable[] { new Variable("int[]", ARRAY_NAME),
						new Variable("int", POSITION)}), width);

		int offset = 0;
		int nextOffset = LayerSizeGenerator.calculateNextOffset(currentDepth, depth, width);
		for (int i = 0; i < width; i++) {
			MethodCallCode methodCall = m.getNextMethodCall();
			methodCall.setResultIsName(true);
			m.addCode(new TextCode("		"));
			m.addCode(methodCall);
			m.addCode(new TextCode("("+ARRAY_NAME+","+POSITION+"+"+offset+");\n"));
			offset += nextOffset;
		}
		addLeafCode(offset, m);
//		m.addCode(new TextCode("		"));
//		m.addCode(methodCall);
//		m.addCode(new TextCode("("+ARRAY_NAME+","+POSITION+"+"+offset+");\n"));
		offset += 1;

		int comingDepth = depth - currentDepth;

		int comingIncrease = width;
		for (int i = 0; i < comingDepth; i++) {
			comingIncrease = comingIncrease * width + width;
		}
		
		if(!Incrementer.randomList.contains(Incrementer.counter)) {
			System.out.println(Incrementer.randomList + "\n" + Incrementer.counter + "\n" + number);
			m.getJML().include = false;
		}
		m.getJML().addRequires("pos >= 0 && pos + "+offset+" < a.length");
		m.getJML().addRequires("(pos > 0 ==>(\\forall int y; 0 <= y && y < a.length - pos; a[y] <= a[a.length - pos]))");
		m.getJML().addRequires("(\\forall int z; a.length - pos <= z && z < a.length -1; a[z] <= a[z+1])");
		m.getJML().addEnsures("(\\forall int o; 0 <= o && o < a.length - ("+offset+"+pos); a[o] <= a[a.length - ("+offset+"+pos)])");
		m.getJML().addEnsures("(\\forall int p; a.length - ("+offset+"+pos) <= p && p < a.length -1; a[p] <= a[p+1])");
		Incrementer.counter++;
		return m;
	}
	
	private void addLeafCode(int field, Method m){
		m.addCode(new TextCode("		int b = 0;\n"));
		m.addCode(new TextCode("		/*@ loop_invariant\n"));
		m.addCode(new TextCode("		  @ 0 <= b && b < a.length - (pos + "+field+") &&\n"));
		m.addCode(new TextCode("		  @ (\\forall int k; 0 <= k && k < b; a[k] <= a[b]) &&\n"));
		m.addCode(new TextCode("		  @ (\\forall int l; a.length - (pos + "+field+") <= l && l < a.length -1; a[l] <= a[l+1]) &&\n"));
		m.addCode(new TextCode("		  @ ((pos + "+field+") > 0 ==> (\\forall int m; 0 <= m && m < a.length - (pos + "+field+"); a[m] <= a[a.length - (pos + "+field+")]));\n"));
		m.addCode(new TextCode("		  @ decreasing a.length - b;\n"));
		m.addCode(new TextCode("		  @ assignable a[*];\n"));
		m.addCode(new TextCode("		  @*/\n"));
		m.addCode(new TextCode("		while(b < a.length-(1+(pos + "+field+"))){\n"));
		m.addCode(new TextCode("			if(a[b] > a[b+1]){\n"));
		m.addCode(new TextCode("				int x = a[b];\n"));
		m.addCode(new TextCode("				a[b] = a[b+1];\n"));
		m.addCode(new TextCode("				a[b+1] = x;\n"));
		m.addCode(new TextCode("			}\n"));
		m.addCode(new TextCode("			b++;\n"));
		m.addCode(new TextCode("		}\n"));
	}
}
