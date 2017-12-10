package de.tubs.mt.codegen.bubble.methods;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import de.tubs.mt.codegen.methods.Method;
import de.tubs.mt.codegen.methods.MethodGenerator;
import de.tubs.mt.codegen.structure.TextCode;
import de.tubs.mt.codegen.structure.Variable;

public class BubbleLeafGenerator implements MethodGenerator {

	@Override
	public Method getMethod(int methodCall, int number, int depth, int width,
			Method blatt) {
		Method m = new Method(methodCall, number, "void",
				Arrays.asList(new Variable[] { new Variable("int[]", "a"),
						new Variable("int", "pos")}), 1);
		TextCode tc = new TextCode();
		StringBuffer code = new StringBuffer();
		try (InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("bubbleLeaf.txt");
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));) {
			String line;
			while ((line = br.readLine()) != null) {
				code.append(line);
				code.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tc.setCode(code.toString());
		m.addCode(tc);
		m.getJML().addRequires("pos >= 0 && pos < a.length");
		m.getJML()
				.addRequires(
						"(pos > 0 ==>(\\forall int y; 0 <= y && y < a.length - pos; a[y] <= a[a.length - pos]))");
		m.getJML()
				.addRequires(
						"(\\forall int z; a.length - pos <= z && z < a.length -1; a[z] <= a[z+1])");
		m.getJML()
				.addEnsures(
						"(\\forall int o; 0 <= o && o < a.length - (1+pos); a[o] <= a[a.length - (1+pos)])");
		m.getJML()
				.addEnsures(
						"(\\forall int p; a.length - (1+pos) <= p && p < a.length -1; a[p] <= a[p+1])");
		return m;
	}

}
