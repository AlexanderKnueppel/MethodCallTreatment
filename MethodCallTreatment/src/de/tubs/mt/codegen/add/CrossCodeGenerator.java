package de.tubs.mt.codegen.add;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codegen.CodeGenerator;
import de.tubs.mt.codegen.add.methods.DummyMethodGenerator;
import de.tubs.mt.codegen.add.methods.LeafMethodGenerator;
import de.tubs.mt.codegen.methods.Method;
import de.tubs.mt.codegen.methods.MethodGenerator;

public class CrossCodeGenerator implements CodeGenerator {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4141558467481535897L;
	/* (non-Javadoc)
	 * @see de.carsten.codegen.CodeGen#generateCode(java.io.OutputStream, int, int)
	 */
	@Override
	public void generateCode(OutputStream stream, int depth, int width) throws IOException {
		generateCode(stream, depth, width, "Test");
	}
	/* (non-Javadoc)
	 * @see de.carsten.codegen.CodeGen#generateCode(java.io.OutputStream, int, int, java.lang.String)
	 */
	@Override
	public void generateCode(OutputStream stream, int depth, int width, String name) throws IOException {

		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream))) {
			bw.write("public class "+name+" {\n");
			List<Method> generatedMethods = new ArrayList<>();
			List<List<Method>> openMethods = new ArrayList<>();
			int number = 1;
			int currentDepth = 1;
			List<Method> methods = new ArrayList<>();

			openMethods.add(new ArrayList<Method>());
			openMethods.add(new ArrayList<Method>());

			Method blattMethod = new LeafMethodGenerator().getMethod(1, 0, depth, 0, null);
			generatedMethods.add(blattMethod);

			MethodGenerator firstMethodGenerator = new DummyMethodGenerator();
			Method firstMethod = firstMethodGenerator.getMethod(currentDepth, number, depth, width, blattMethod);
			openMethods.get(0).add(firstMethod);
			generatedMethods.add(firstMethod);
			while (currentDepth < depth) {
				List<Method> nextMethods = new ArrayList<>();
				for (int i = 0; i < width; i++) {
					MethodGenerator mg = firstMethodGenerator;
					Method m = mg.getMethod(currentDepth + 1, number, depth, width, blattMethod);
					generatedMethods.add(m);
					methods.add(m);
					openMethods.get(1).add(m);
					nextMethods.add(m);
					number++;
				}
				while (!openMethods.get(0).isEmpty()) {
					int next = 0;
					while (openMethods.get(0).get(0).getNumberOfOpenMethods() != 0) {
						openMethods.get(0).get(0).setMethodCall(nextMethods.get(next).getMethodCallCode());
						next++;
					}
					openMethods.get(0).remove(0);
				}
				openMethods.remove(0);
				openMethods.add(new ArrayList<Method>());
				currentDepth++;
				number = 1;
			}
			while (!openMethods.get(0).isEmpty()) {
				while (openMethods.get(0).get(0).getNumberOfOpenMethods() != 0) {
					openMethods.get(0).get(0).setMethodCall(blattMethod.getMethodCallCode());
				}
				openMethods.get(0).remove(0);
			}

			for (Method method : generatedMethods) {
				bw.write(method.getCode());
			}
			bw.write("}");
			bw.flush();
		}
	}
}
